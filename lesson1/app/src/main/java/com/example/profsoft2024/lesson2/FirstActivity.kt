package com.example.profsoft2024.lesson2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.profsoft2024.R
import com.example.profsoft2024.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityFirstBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        binding.buttonNavigate.setOnClickListener {
            startActivity(SecondActivity.newInstance(this, "Hello world!"))
        }
    }
}