package com.example.kotlincoroutines_seminar._6_dispatcher

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines_seminar.R
import com.example.kotlincoroutines_seminar.databinding.ActivityDispatcherBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DispatcherActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDispatcherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDispatcherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val data = readFile()
            withContext(Dispatchers.Main) {
                binding.tvContent.text = data
            }
        }

        lifecycleScope.launch(Dispatchers.Default) {
            val result = heavyComputation()
            withContext(Dispatchers.Main) {
                binding.tvContent.text = result.toString()
            }
        }

/*        Dispatchers.Unconfined là một loại Dispatcher trong Kotlin Coroutines không ràng buộc coroutine với bất kỳ thread nào.
        Nó sẽ chạy ngay lập tức trên thread hiện tại, nhưng có thể chuyển sang thread khác sau khi gặp suspend như delay().*/

        lifecycleScope.launch(Dispatchers.Unconfined) {
            Log.e("LuanNT","🔹 Trước delay: ${Thread.currentThread().name}")
            delay(100)
            Log.e("LuanNT","🔸 Sau delay: ${Thread.currentThread().name}")
        }
    }

    private suspend fun readFile(): String {
        delay(500)
        return "FileContent123"
    }

    private fun heavyComputation(): Int {
        var sum = 0
        for (i in 1..1_000_000) {
            sum += i
        }
        return sum
    }
}