package com.angrybird.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Handler
import android.util.AttributeSet
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
    init {
        initBirdView()
        initPipeView()
        manageBirdLive()
    }

    private fun initPipeView() {
        pipeArr = ArrayList()
        totalPipe = 6
        distance = 300*Define.SCREEN_HEIGHT/1920
        for(i in 0 until totalPipe){
            if(i<totalPipe/2){
                val pipe = Pipe(Define.SCREEN_WIDTH+i*((Define.SCREEN_WIDTH+200*Define.SCREEN_WIDTH/1080)/(totalPipe/2)).toFloat(),
                    0f, 200*Define.SCREEN_WIDTH/1080, Define.SCREEN_HEIGHT/2)
                pipe.bm = BitmapFactory.decodeResource(this.resources,R.drawable.pipe2)
                pipe.randomY()
                pipeArr.add(pipe)
            } else {
                val temp = pipeArr[i-totalPipe/2]
                val pipe = Pipe(temp.x, temp.y + temp.height +distance, 200*Define.SCREEN_WIDTH/1080, Define.SCREEN_HEIGHT/2)
                pipe.bm = BitmapFactory.decodeResource(this.resources,R.drawable.pipe1)
                pipeArr.add(pipe)
            }
        }

    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        bird.draw(canvas)
        for(i in 0 until totalPipe ){
            if(pipeArr[i].x < -pipeArr[i].width){
                pipeArr[i].x = Define.SCREEN_WIDTH.toFloat()
                if(i<totalPipe/2){
                    pipeArr[i].randomY()
                } else {
                    pipeArr[i].y = pipeArr[i-totalPipe/2].y +  pipeArr[i-totalPipe/2].height +distance
                }
            }
            pipeArr[i].draw(canvas)
        }
        handler.postDelayed(runnable, 1)
    }

    private fun initBirdView(){
        bird = Bird()
        bird.width = 100*Define.SCREEN_WIDTH/1080
        bird.height = 100*Define.SCREEN_HEIGHT/1920
        bird.x = (100*Define.SCREEN_WIDTH/1080).toFloat()
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
            bird.dropState = -15f
        }
        return true
    }

}