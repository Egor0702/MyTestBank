package marynych.rsue.mytestbank

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.*
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: QuestionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Egor", "мы в onCreate()")
        adapter = QuestionAdapter(this, Question.questionBank.toMutableList())
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter
    }
}