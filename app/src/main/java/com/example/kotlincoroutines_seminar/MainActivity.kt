package com.example.kotlincoroutines_seminar

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlincoroutines_seminar._1_what_are_coroutine.CoroutineDemoActivity
import com.example.kotlincoroutines_seminar._2_suspend_function.SuspendActivity
import com.example.kotlincoroutines_seminar._3_CoroutineScope.CoroutineScopeActivity
import com.example.kotlincoroutines_seminar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setUpClick()
    }

    private fun setUpClick() = with(binding) {
        btn1.setOnClickListener {
            startActivity(Intent(this@MainActivity, CoroutineDemoActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this@MainActivity, SuspendActivity::class.java))
        }

        btn3.setOnClickListener {
            startActivity(Intent(this@MainActivity, CoroutineScopeActivity::class.java))
        }


    }
}