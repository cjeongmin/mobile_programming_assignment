package com.cjeongmin.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjeongmin.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.rvProductList?.adapter = ProductListAdapter(Data.productList)
        binding?.rvProductList?.layoutManager =
            LinearLayoutManager(binding?.root?.context, LinearLayoutManager.VERTICAL, false)

        binding?.btnUser?.setOnClickListener {
            if (Data.user == null) {
                // 비로그인 상태이므로 모달창 띄워서 회원가입 권유하기
                val builder = AlertDialog.Builder(this)
                builder.setTitle("알림").setMessage("회원 정보 페이지입니다. 가입하시겠습니까?")
                    .setPositiveButton("확인") { _, _ ->
                        val intent = Intent(this, SignUpActivity::class.java)
                        startActivity(intent)
                    }.setNegativeButton("취소") { _, _ ->
                        Toast.makeText(this, "취소하셨습니다.", Toast.LENGTH_SHORT).show()
                    }
                builder.show()
            } else {
                // 로그인 상태이므로 회원 정보 보여주기
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }
}