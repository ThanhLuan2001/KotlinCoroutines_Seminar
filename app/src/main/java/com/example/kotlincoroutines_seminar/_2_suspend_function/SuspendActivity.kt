package com.example.kotlincoroutines_seminar._2_suspend_function

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines_seminar.databinding.ActivitySuspendBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuspendActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuspendBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuspendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpClick()
    }

    private fun setUpClick() = with(binding) {
        btnRequest.setOnClickListener{
            fetchData()
        }
        btnClick.setOnClickListener {
            Toast.makeText(this@SuspendActivity, "🟢 Vẫn bấm được!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fetchData() {
        lifecycleScope.launch {
            Log.e("LuanNT", "Name: ${Thread.currentThread().name}")
            delay(5000L)
            Log.e("LuanNT", "End")
        }
    }
}