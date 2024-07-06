package com.example.profsoft2024

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.profsoft2024.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySecondBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding.buttonToast.setOnClickListener {
            val text = intent.getStringExtra(KEY)
            if (text != null) {
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val KEY = "key"

        fun newInstance(context: Context, text: String) : Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(KEY, text)
            return intent
        }
    }
}