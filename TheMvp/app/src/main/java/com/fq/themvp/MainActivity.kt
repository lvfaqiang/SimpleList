package com.fq.themvp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fq.themvp.simples.demo1.SimpleActivity
import com.fq.themvp.simples.demo2.DemoActivity
import com.fq.themvp.simples.demo5.Demo5Activity
import com.fq.themvp.simples.demo6.Demo6Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            startTo<SimpleActivity>()
        }
        button2.setOnClickListener {
            startTo<DemoActivity>()
        }
        button3.setOnClickListener {
            startTo<com.fq.themvp.simples.demo3.ShellActivity>()
        }
        button4.setOnClickListener {
            startTo<com.fq.themvp.simples.demo4.ShellActivity>()
        }
        button5.setOnClickListener {
            startTo<Demo5Activity>()
        }
        button6.setOnClickListener {
            startTo<Demo6Activity>()
        }
    }


    inline fun <reified T : Activity> startTo() {
        startActivity(Intent(this, T::class.java))
    }
}
