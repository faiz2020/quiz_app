package com.myapp.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.myapp.quizapp.R
import kotlinx.android.synthetic.main.activity_spalsh_screen.*
import java.lang.Exception

class spalshScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)

        val auth =FirebaseAuth.getInstance()
        if (auth.currentUser!=null){
            Toast.makeText(this, "user already exist", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        btnsplashscreen.setOnClickListener {
            redirect("LOGIN")
        }

    }
    private fun redirect(name:String){
        val intent =when(name){
            "LOGIN" -> Intent(this, LoginPage::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else ->throw Exception("no path exist")
        }
        startActivity(intent)
        finish()
    }

}