package com.angrybird.model

import android.graphics.Bitmap
import android.graphics.Rect

open class BaseObject{
    var x: Float = 0.0f
    var y: Float = 0.0f
    var width: Int = 0
    var height: Int = 0
    var bm: Bitmap? = null
    protected var rect:Rect? = null
        get() = Rect(this.x as Int, this.y as Int,this.x as Int + this.width,
            this.y as Int +this.height)

    constructor(){
    }
    constructor(x:Float, y:Float, width: Int, height: Int){
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }
//    open fun getBm(): Bitmap{
//        return this.bm!!
//    }

}