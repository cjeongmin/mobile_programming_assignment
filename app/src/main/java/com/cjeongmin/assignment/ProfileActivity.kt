package com.cjeongmin.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cjeongmin.assignment.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = Data.user?.name
        binding.tvPhoneNumber.text = Data.user?.phoneNumber
        binding.tvAddress.text = Data.user?.address
    }
}