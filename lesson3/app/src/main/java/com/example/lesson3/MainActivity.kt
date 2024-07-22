package com.example.lesson3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.twoTextsCity.twoTextsTitle.text = getString(R.string.city)
        binding.twoTextsCity.twoTextsDescription.text = getString(R.string.city_value)

        binding.twoTextsAboutMe.twoTextsTitle.text = getString(R.string.about_me)
        binding.twoTextsAboutMe.twoTextsDescription.text = getString(R.string.about_me_value)
    }
}