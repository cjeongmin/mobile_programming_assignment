package com.cjeongmin.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cjeongmin.assignment.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private var binding: ActivitySignInBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Data.fetchUserList(this)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnSignin?.setOnClickListener {
            Toast.makeText(
                this, Data.signIn(
                    binding?.etId?.text.toString(), binding?.etPassword?.text.toString()
                ), Toast.LENGTH_SHORT
            ).show()

            if (Data.user != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding?.btnSignup?.setOnClickListener { signUp() }

        binding?.btnHome?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (Data.user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}