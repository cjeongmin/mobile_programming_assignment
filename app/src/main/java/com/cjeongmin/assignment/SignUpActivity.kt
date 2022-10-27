package com.cjeongmin.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.cjeongmin.assignment.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnSignup?.setOnClickListener { signUp() }
    }

    private fun signUp() {
        val id = binding?.etId?.text.toString()
        val password = binding?.etPassword?.text.toString()
        val name = binding?.etName?.text.toString()
        val phoneNumber = binding?.etPhoneNumber?.text.toString()
        val address = binding?.etAddress?.text.toString()

        if (id.trim().isEmpty() || password.trim().isEmpty() || name.trim()
                .isEmpty() || phoneNumber.trim().isEmpty() || address.trim().isEmpty()
        ) {
            Toast.makeText(this, "모든 항목을 입력하셔야 합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidId(id)) {
            Toast.makeText(this, "ID는 영어 대소문자, 숫자만 입력할 수 있습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (Data.findUser(id) != null) {
            Toast.makeText(this, "중복된 ID가 있습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length > 20) {
            Toast.makeText(this, "비밀번호는 20글자 이하만 가능합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidPassword(password)) {
            Toast.makeText(
                this,
                "비밀번호는 영어 대소문자, 숫자, 특수기호(~, !, @)만 입력할 수 있습니다.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (name.length > 30 || address.length > 50) {
            Toast.makeText(this, "이름 또는 주소의 입력 길이가 너무 깁니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            Toast.makeText(this, "전화번호의 형식은 000-0000-0000 이여야 합니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidText(name) || !isValidText(address)) {
            Toast.makeText(
                this, "이름, 주소는 영어 대소문자, 숫자, 한글, 특수기호(~, !)만 입력할 수 있습니다.", Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (binding?.rbDecline?.isChecked == true) {
            Toast.makeText(this, "개인정보 수집/이용에 비동의하셨습니다.", Toast.LENGTH_SHORT).show()
        } else if (binding?.rbAccept?.isChecked == true) {
            Toast.makeText(this, "개인정보 수집/이용에 동의하셨습니다.", Toast.LENGTH_SHORT).show()
        }

        Data.addUser(User(id, password, name, phoneNumber, address))
        Data.storeUserList(this)
        finish()
    }

    private fun isValidId(id: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9]+\$")
        return regex.matches(id)
    }

    private fun isValidPassword(password: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9~!@]+\$")
        return regex.matches(password)
    }

    private fun isValidText(str: String): Boolean {
        val regex = Regex("^[ ~!a-zA-Z0-9ㄱ-ㅎ가-힣]+$")
        return regex.matches(str)
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val regex = Regex("\\d{3}-\\d{4}-\\d{4}")
        return regex.matches(phoneNumber)
    }
}