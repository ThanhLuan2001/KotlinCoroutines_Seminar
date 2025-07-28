package com.example.kotlincoroutines_seminar._4_coroutine_builder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines_seminar.R
import com.example.kotlincoroutines_seminar._1_what_are_coroutine.CoroutineDemoActivity
import com.example.kotlincoroutines_seminar._2_suspend_function.SuspendActivity
import com.example.kotlincoroutines_seminar._3_coroutine_scope.CoroutineScopeActivity
import com.example.kotlincoroutines_seminar.databinding.ActivityCoroutineBuilderBinding
import com.example.kotlincoroutines_seminar.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineBuilderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutineBuilderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBuilderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setUpClick()
        timeCount()
    }



    private fun setUpClick() = with(binding) {
        btn1.setOnClickListener {
            lifecycleScope.launch {
                Log.e("LuanNT", "Bắt đầu")

                val deferred1 = async { apiCall1() }
                val deferred2 = async { apiCall2() }

                val result1 = deferred1.await()
                val result2 = deferred2.await()

                Log.e("LuanNT", "Kết quả: $result1 & $result2")
            }
        }

        btn2.setOnClickListener {
            startProgramme()
        }

    }

    private suspend fun apiCall1(): String {
        delay(2000) // giả lập delay
        return "Dữ liệu 1"
    }

    private suspend fun apiCall2(): String {
        delay(7000) // giả lập delay
        return "Dữ liệu 2"
    }



    private fun startProgramme() {
        Log.e("LuanNT", "🔷 Bắt đầu chương trình")

        val a = runBlocking {
            delay(5000L)
        }

        Log.e("LuanNT","🔶 Kết thúc chương trình")
    }

    private fun timeCount() {
        lifecycleScope.launch {
            for (i in 1..100) {
                Log.e("LuanNT", "Item: $i")
                delay(1000L)
            }
        }
    }
}