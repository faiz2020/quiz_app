package com.myapp.quizapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.myapp.quizapp.R
import com.myapp.quizapp.adapters.Optionadapter
import com.myapp.quizapp.models.Questions
import com.myapp.quizapp.models.Quiz
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.option_item.*


class QuestionActivity : AppCompatActivity() {

    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Questions>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        setUpfirestore()
        setupEventlistener()
    }

    private fun setupEventlistener() {
        btnprevious.setOnClickListener {
            index--
            bindView()
        }
        btnnext.setOnClickListener {
            index++
            bindView()
        }
        btnsubmit.setOnClickListener {

        }
    }

    private fun setUpfirestore() {
        val firestore = FirebaseFirestore.getInstance()
        val date = intent.getStringExtra("DATE")
        if (date != null) {
            val collectionReference =
                firestore.collection("quizzes").whereEqualTo("title", date)
                    .get()
                    .addOnSuccessListener {
                        if (it != null && !it.isEmpty) {

                            Log.d("DATA", it.toObjects(Quiz::class.java).toString())
                            quizzes = it.toObjects(Quiz::class.java)
                            questions = quizzes!![0].questions
                            bindView()
                        }

                    }
        }


    }

    private fun bindView() {

        btnnext.visibility= View.GONE
        btnprevious.visibility=View.GONE
        btnsubmit.visibility=View.GONE

        if(index==1){
            btnnext.visibility=View.VISIBLE
        }
        else if(index == questions!!.size){
            btnsubmit.visibility=View.VISIBLE
            btnprevious.visibility=View.VISIBLE
        }
        else{//middle of the questions
            btnprevious.visibility=View.VISIBLE
            btnnext.visibility=View.VISIBLE

        }

        val question=questions!!["question$index"]

        question?.let {
            description.text = it.description
            val optionadapter = Optionadapter(this, it)
            optionlist.layoutManager = LinearLayoutManager(this)
            optionlist.adapter = optionadapter
            optionlist.setHasFixedSize(true)
        }

    }

}