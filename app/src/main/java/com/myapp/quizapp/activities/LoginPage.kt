package com.myapp.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.myapp.quizapp.R
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        firebaseAuth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        loginbtn.setOnClickListener() {
            login()

        }
        btnsignup.setOnClickListener() {
            val intent = Intent(this, singupPage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {
        val email = etemail.text.toString()
        val password = etpass.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "field is blank", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {

                    if (it.isSuccessful) {
                        Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, "do not have an account signup", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}