package com.example.kotlincoroutines_seminar._3_coroutine_scope

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines_seminar.R
import com.example.kotlincoroutines_seminar.databinding.ActivityCoroutineScopeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineScopeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutineScopeBinding
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoroutineScopeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpClick()

    }

    private fun setUpClick() = with(binding) {
        btnGlobal.setOnClickListener {
            GlobalScope.launch {
                for (item in 1..100) {
                    Log.e("LuanNT", "Global Item: $item")
                    delay(1000L)
                }
            }
        }
        btnLifecycle.setOnClickListener {
            lifecycleScope.launch {
                for (item in 1..100) {
                    Log.e("LuanNT", "Lifecycle Item: $item")
                    delay(1000L)
                }
            }
        }

        btnViewModel.setOnClickListener {
            viewModel.startCount()
        }
    }
}