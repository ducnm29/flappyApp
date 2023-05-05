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
    init {
        initBirdView()
        manageBirdLive()
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        bird.draw(canvas)
        handler.postDelayed(runnable, 10)
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
    private fun manageBirdLive() {
        handler = Handler()
        runnable = Runnable {
            run {
                invalidate()
            }
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            bird.dropState = -15f
        }
        return true
    }

}