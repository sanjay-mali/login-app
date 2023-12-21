package com.example.loginform

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class welcomeScreen : AppCompatActivity() {

    companion object{
        val name = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        val name = intent.getStringExtra(name)

        val welCome = findViewById<TextView>(R.id.welCome)

        welCome.text = "Welcome $name"
    }
}