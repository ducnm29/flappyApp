package com.angrybird.model

import android.graphics.Bitmap
import android.graphics.Canvas
import com.angrybird.Define
import java.util.Random

class Pipe: BaseObject {
    var isScored:Boolean = false

    companion object{
        var speed:Int = 20
    }
    constructor()
    constructor(x:Float, y:Float, width: Int, height: Int):
            super(x,y,width,height)

    fun draw(canvas: Canvas){
        this.x -= speed
        canvas.drawBitmap(getBitmap(),x,y,null)
    }
    fun randomY(){
        val random = Random()
        this.y = (random.nextInt(this.height/4+1)-this.height/4).toFloat()
    }
    fun getBitmap():Bitmap{
        return Bitmap.createScaledBitmap(bm!!,width,height,true)
    }
}