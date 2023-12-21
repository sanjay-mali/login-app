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

class signIn : AppCompatActivity() {

    var database = Firebase.database
//    var myRef = database.getReference("Users")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val uname = findViewById<TextInputEditText>(R.id.uname)
        val pwd = findViewById<TextInputEditText>(R.id.pwd)
        val btnLogin = findViewById<Button>(R.id.btnsighIn)



        btnLogin.setOnClickListener {
            val userName = uname.text.toString()
            val password = pwd.text.toString()

            if (userName.isNotEmpty() || password.isNotEmpty()) {
                checkUser(userName, password)
                uname.text?.clear()
                pwd.text?.clear()
            } else {
                Toast.makeText(this, "Enter a values", Toast.LENGTH_SHORT).show()
            }
        }
        val sighUpbtn = findViewById<TextView>(R.id.sighUp)
        sighUpbtn.setOnClickListener {
            val sIntent = Intent(this, MainActivity::class.java)
            startActivity(sIntent)
        }
    }

    private fun checkUser(userName: String, password: String) {
        val myRef = database.getReference("Users")
        myRef.child(userName).get().addOnSuccessListener {
            if (it.exists()) {
                val user = it.child("uname").value
                myRef.child(userName).child("password").get().addOnSuccessListener {
                    if(it.exists())
                    {
                        val pwd = it.value
                        if(pwd == password)
                        {
                            val sIntent = Intent(this,welcomeScreen::class.java)
                            sIntent.putExtra(welcomeScreen.name,user.toString())
                            startActivity(sIntent)
                        }
                        else
                        {
                            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "Password not found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failure to load ", Toast.LENGTH_SHORT).show()
        }

}
}