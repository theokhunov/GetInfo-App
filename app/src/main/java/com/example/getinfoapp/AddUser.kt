package com.example.getinfoapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.getinfoapp.databinding.ActivityAddUserBinding

class AddUser : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        binding.Savebtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enterbutton))
        binding.nameEt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_left))
        binding.SurnameEt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_right))
        binding.AgeEt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_left))

        binding.Savebtn.setOnClickListener {
            val name = binding.nameEt.text.toString()
            val surname = binding.SurnameEt.text.toString()
            val age = binding.AgeEt.text.toString()
            if (name.isNotBlank() && surname.isNotBlank() && age.isNotBlank()) {
                sharedPreferences.edit().putString("name", name).apply()
                sharedPreferences.edit().putString("surname", surname).apply()
                sharedPreferences.edit().putString("age", age).apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Enter your data!", Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun onStart() {
        if (sharedPreferences.getString("name", "") != "") {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }

        }
        super.onStart()

    }
}