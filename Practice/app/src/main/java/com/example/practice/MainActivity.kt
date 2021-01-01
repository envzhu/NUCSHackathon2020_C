package com.example.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button)
        var clicker: View.OnClickListener = View.OnClickListener {
            Log.e("Button", "Clicked")
            val intent = Intent(this, RecordActivity::class.java).apply{}

            startActivity(intent)

        }
        button.setOnClickListener(clicker)
    }
}