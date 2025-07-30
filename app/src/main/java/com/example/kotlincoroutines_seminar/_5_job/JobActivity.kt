package com.example.kotlincoroutines_seminar._5_job

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines_seminar.R
import com.example.kotlincoroutines_seminar.databinding.ActivityJobBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class JobActivity : AppCompatActivity() {
    private lateinit var binding : ActivityJobBinding
    private val sharedJob = Job()

    private val scope1 = CoroutineScope(Dispatchers.Default + sharedJob)
    private val scope2 = CoroutineScope(Dispatchers.IO + sharedJob)
    private val scope3 = CoroutineScope(Dispatchers.Main + sharedJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJobBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpClick()
    }

    private fun setUpClick() = with(binding) {
        btnStart.setOnClickListener { startTasks() }
        btnCancel.setOnClickListener { sharedJob.cancel() }
        btnJoin.setOnClickListener { joinTasks() }
        btnSupervisionJob.setOnClickListener { supervisionExample() }
    }



    private fun startTasks() {
        scope1.launch {
            repeat(10) { i ->
                delay(1000)
                Log.d("Scope", "Task 1 $i on ${Thread.currentThread().name}")
            }
        }

        scope2.launch {
            repeat(10) { i ->
                delay(1000)
                Log.d("Scope", "Task 2 $i on ${Thread.currentThread().name}")
            }
        }

        scope3.launch {
            repeat(10) { i ->
                delay(1000)
                Log.d("Scope", "Task 3 $i on ${Thread.currentThread().name}")
            }
        }
    }

    private fun joinTasks() {
        lifecycleScope.launch {
            Log.e("LuanNT", "▶ Bắt đầu")
            val job = launch {
                delay(2000)
                Log.e("LuanNT","✅ Công việc trong coroutine hoàn thành")
            }
            Log.e("LuanNT", "🕓 Đang đợi job hoàn thành...")
            job.join()
            Log.e("LuanNT", "🏁 Kết thúc chương trình")
        }
    }

    private fun supervisionExample() {
        val scope = CoroutineScope(Dispatchers.Default + Job())

        Log.e("LuanNT","Start")

        scope.launch {
            delay(1000)
            throw RuntimeException("❌ Job 1 failed!")
        }

        scope.launch {
            delay(2000)
            Log.e("LuanNT","✅ Job 2 finished successfully!")
        }

        scope.launch {
            delay(3000)
            Log.e("LuanNT","🏁 Done supervision example")
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        sharedJob.cancel()
    }

}

//coroutineScope--------------
/*fun main() = runBlocking {
    println("▶ Start coroutineScope example")

    try {
        coroutineScope {
            launch {
                delay(500)
                println("✅ Job 1 is running")
            }

            launch {
                delay(1000)
                throw RuntimeException("❌ Job 2 failed")
            }

            launch {
                delay(1500)
                println("✅ Job 3 should not run (coroutineScope)")
            }
        }
    } catch (e: Exception) {
        println("⚠ Caught exception in coroutineScope: ${e.message}")
    }

    println("⏹ End coroutineScope example\n")
}*/

//supervisorScope--------------
/*fun main() = runBlocking {
    println("▶ Start supervisorScope example")

    try {
        supervisorScope {
            launch {
                delay(500)
                println("✅ Job 1 is running")
            }

            launch {
                delay(1000)
                throw RuntimeException("❌ Job 2 failed")
            }

            launch {
                delay(1500)
                println("✅ Job 3 still runs (supervisorScope)")
            }
        }
    } catch (e: Exception) {
        println("⚠ Caught exception in supervisorScope: ${e.message}")
    }

    println("⏹ End supervisorScope example")
}*/


