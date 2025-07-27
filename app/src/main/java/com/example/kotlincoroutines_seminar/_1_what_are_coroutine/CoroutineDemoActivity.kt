package com.example.kotlincoroutines_seminar._1_what_are_coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines_seminar.databinding.ActivityCoroutineDemoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutineDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     binding = ActivityCoroutineDemoBinding.inflate(layoutInflater)
     setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.Main) {
            val result = fetchData()
            //Cập nhật UI trên Main Thread
            binding.tvContent.text = result
        }

    }


    private suspend fun fetchData(): String {
        return withContext(Dispatchers.IO){
            ///Giả lập tác vụ tốn nhiều thời gian ( Ví dụ : Call Api, Load data from database)
            delay(5000L)
            "Dữ liệu lấy được"
        }
    }

}