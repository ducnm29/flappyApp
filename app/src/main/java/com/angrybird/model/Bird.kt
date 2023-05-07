package com.angrybird.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import com.angrybird.Define

class Bird: BaseObject {
    private var status:Int = 0
    private var vFlap:Int = 5
    private var currentBitmapId: Int =0
    var dropState:Float = 0.0f
    var bitmapArr:ArrayList<Bitmap> = ArrayList()
        set(value) {
            field = ArrayList()
            for(bitmap in value){
                field.add(Bitmap.createScaledBitmap(bitmap,this.width, this.height, true))
            }
        }
    constructor()
    constructor(x:Float, y:Float, width: Int, height: Int):
        super(x,y,width,height)

    fun draw(canvas: Canvas){
        dropBird()
        canvas.drawBitmap(getBitmap(),this.x , this.y , null)
    }
    private fun getBitmap(): Bitmap {
        status++
        if(this.status == this.vFlap){
            if(this.currentBitmapId == 0){
                this. currentBitmapId =1
            } else {
                this.currentBitmapId =0
            }
            status = 0
        }
        val matrix = Matrix()
        if(this.dropState < 0){
            matrix.postRotate(-25f)
            return Bitmap.createBitmap(bitmapArr[currentBitmapId], 0, 0,
                bitmapArr[currentBitmapId].width,  bitmapArr[currentBitmapId].height,
                matrix, true)
        } else if(dropState > 0){
            if(dropState<60){
                matrix.postRotate(-25f + dropState*2)
            } else {
                matrix.postRotate(45f)
            }
            return Bitmap.createBitmap(bitmapArr[currentBitmapId], 0, 0,
                bitmapArr[currentBitmapId].width,  bitmapArr[currentBitmapId].height,
                matrix, true)
        }
        return this.bitmapArr[currentBitmapId]
    }

    /**
     * Auto drop bird
     */
    private fun dropBird(){
        this.dropState += 0.6f
        this.y += dropState
        if (this.y >Define.SCREEN_HEIGHT-100){
            this.y = Define.SCREEN_HEIGHT.toFloat() - 100
        } else if(this.y < 15 ){
            this.y = 15f
        }
    }
}