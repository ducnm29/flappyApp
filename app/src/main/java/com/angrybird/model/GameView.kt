package com.angrybird.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.angrybird.Define
import com.angrybird.R

class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var bird:Bird
    private lateinit var pipeArr:ArrayList<Pipe>
    private  var totalPipe:Int = 0
    private var distance:Int = 0
    private var score:Int = 0
    init {
        initBirdView()
        initPipeView()
        manageBirdLive()
    }

    private fun initPipeView() {
        pipeArr = ArrayList()
        totalPipe = 12
        distance = ((1/9f)*Define.SCREEN_HEIGHT).toInt()
        for(i in 0 until totalPipe){
            if(i<totalPipe/2){
                val pipe = Pipe(i*((1/6f)*Define.SCREEN_WIDTH),
                    0f, ((1/8f)*Define.SCREEN_WIDTH).toInt(), Define.SCREEN_HEIGHT/2)
                pipe.bm = BitmapFactory.decodeResource(this.resources,R.drawable.pipe2)
                pipe.randomY()
                pipeArr.add(pipe)
            } else {
                val temp = pipeArr[i-totalPipe/2]
                val pipe = Pipe(temp.x, temp.y + temp.height +distance, ((1/8f)*Define.SCREEN_WIDTH).toInt(), Define.SCREEN_HEIGHT/2)
                pipe.bm = BitmapFactory.decodeResource(this.resources,R.drawable.pipe1)
                pipeArr.add(pipe)
            }
        }

    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        bird.draw(canvas)
        handler.postDelayed(runnable, 10)
        for(i in 0 until totalPipe ){
            if(pipeArr[i].x < -pipeArr[i].width){
                pipeArr[i].x = Define.SCREEN_WIDTH.toFloat()
                pipeArr[i].isScored = false
                if(i<totalPipe/2){
                    pipeArr[i].randomY()
                } else {
                    pipeArr[i].y = pipeArr[i-totalPipe/2].y +  pipeArr[i-totalPipe/2].height +distance
                }
            }
            if(pipeArr[i].x < bird.x && !pipeArr[i].isScored){
                score++
                Log.e("score",score.toString())
                pipeArr[i].isScored = true
            }
            pipeArr[i].draw(canvas)
        }
    }

    private fun initBirdView(){
        bird = Bird()
        bird.width = ((1/10f)*Define.SCREEN_WIDTH).toInt()
        bird.height = ((1/10f)*Define.SCREEN_WIDTH).toInt()
        bird.x = ((1/6f)*Define.SCREEN_WIDTH)
        bird.y = (Define.SCREEN_HEIGHT/2 - bird.height/2).toFloat()
        var bitmapArr = ArrayList<Bitmap>()
        bitmapArr.add(BitmapFactory.decodeResource(this.resources, R.drawable.bird1))
        bitmapArr.add(BitmapFactory.decodeResource(this.resources, R.drawable.bird2))
        bird.bitmapArr = bitmapArr
    }

    /**
     * Redraw view after change
     */
    private fun manageBirdLive() {
        handler = Handler()
        runnable = Runnable {
            run {
                invalidate()
            }
        }

    }

    /**
     * Increase height of bird when user touch screen
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            if(bird.dropState == 0f){
                bird.dropState = 5f
            }
            bird.dropState = -bird.dropState
        }
        return true
    }

}