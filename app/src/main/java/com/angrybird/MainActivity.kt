package com.angrybird

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        initView()
        setContentView(R.layout.activity_main)
    }

    private fun initView() {
        val dm = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(dm)
        Define.SCREEN_WIDTH = dm.widthPixels
        Define.SCREEN_HEIGHT = dm.heightPixels
    }
}