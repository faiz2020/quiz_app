package com.myapp.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.myapp.quizapp.R
import com.myapp.quizapp.adapters.QuizAdapter
import com.myapp.quizapp.models.Quiz
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }



    private fun setupView() {
        setUpFirestore()
        setUpDrawerLayout()
        setupRecycleView()
        setupdatepicker()
    }



    private fun setUpFirestore() {
        firestore= FirebaseFirestore.getInstance()
        val collectionReference=firestore.collection("quizzes")
        collectionReference.addSnapshotListener{ value, error ->
            if(value==null || error !=null){
                Toast.makeText(this, "Error ffecting data", Toast.LENGTH_SHORT).show()

                return@addSnapshotListener
            }
            Log.d("Data",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()

        }
    }

    private fun setupdatepicker() {
        btndateicker.setOnClickListener {
            val datepicker= MaterialDatePicker.Builder.datePicker().build()
            datepicker.show(supportFragmentManager,"Datepicker")

            datepicker.addOnPositiveButtonClickListener {
                Log.d("datepicker",datepicker.headerText)
                val dateformater=SimpleDateFormat("dd-mm-yyyy")
                val date=dateformater.format(Date(it))
                val intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)

            }
            datepicker.addOnNegativeButtonClickListener {
                Log.d("datepicker",datepicker.headerText)
            }
            datepicker.addOnCancelListener {
                Log.d("datepicker",datepicker.headerText)
            }
        }
    }

    private fun setupRecycleView() {
        adapter = QuizAdapter(this,quizList)
        recycleview.layoutManager=GridLayoutManager(this ,2)
        recycleview.adapter=adapter
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, mainDrawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}