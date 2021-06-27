package com.myapp.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.myapp.quizapp.R
import kotlinx.android.synthetic.main.activity_singup_page.*

class singupPage : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup_page)

        firebaseAuth = FirebaseAuth.getInstance()

        signupbutton.setOnClickListener() {
            signUpUser()
        }


        btnlogin.setOnClickListener() {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpUser() {

        val email = etemailaddress.text.toString()
        val password = etpass.text.toString()
        val confirmPass = etconfirmpass.text.toString()


        if (email.isBlank() || password.isBlank() || confirmPass.isBlank()) {
            Toast.makeText(this, "email and password can't be blank", Toast.LENGTH_SHORT).show()

            return
        }
        if (password != confirmPass) {
            Toast.makeText(this, "password and confirm password do not match", Toast.LENGTH_SHORT).show()

            return
        }


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "error creating user", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}