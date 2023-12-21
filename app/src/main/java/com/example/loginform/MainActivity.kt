package com.example.loginform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fname = findViewById<TextInputEditText>(R.id.fname)
        val lname = findViewById<TextInputEditText>(R.id.lname)
        val uname = findViewById<TextInputEditText>(R.id.uname)
        val emails = findViewById<TextInputEditText>(R.id.email)
        val pwd = findViewById<TextInputEditText>(R.id.pwd)
        val sighUp = findViewById<Button>(R.id.btnsighUp)
        sighUp.setOnClickListener {
            val firstName = fname.text.toString()
            val lastName = lname.text.toString()
            val userName = uname.text.toString()
            val email = emails.text.toString()
            val password = pwd.text.toString()

            val data = Users(firstName,lastName,userName,email,password)
            val database = Firebase.database
            val myRef = database.getReference("Users")

            if(firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this, "Enter a value", Toast.LENGTH_SHORT).show()
            }
            else {

                myRef.child(userName).setValue(data)
                    .addOnSuccessListener {
                        fname.text?.clear()
                        lname.text?.clear()
                        uname.text?.clear()
                        emails.text?.clear()
                        pwd.text?.clear()

                        Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener() {
                        Toast.makeText(this, "Failure ", Toast.LENGTH_SHORT).show()
                    }

            }
        }
        val login = findViewById<TextView>(R.id.signIn)
        login.setOnClickListener{
            val sIntent = Intent(this,signIn::class.java)
            startActivity(sIntent)
        }

    }
}