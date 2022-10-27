package com.cjeongmin.assignment

import android.content.Context
import org.json.JSONArray
import org.json.JSONException

object Data {
    private const val USER_LIST = "USER_LIST"
    private val userList = arrayListOf<User>()
    var user: User? = null

    val productList = arrayListOf<Product>(
        Product("오버사이즈 스웨이드 라인 무스탕 자켓", R.drawable.pic1),
        Product("하프 집업 카라 니트", R.drawable.pic2),
        Product("슬레이크 후드 네이비", R.drawable.pic3),
        Product("헤비 코튼 오버 럭비 맨투맨", R.drawable.pic4),
        Product("코튼 워셔블 하찌 하프집업 니트", R.drawable.pic5),
    )

    fun fetchUserList(context: Context?) {
        val prefs = context?.getSharedPreferences(USER_LIST, Context.MODE_PRIVATE)
        val json = prefs?.getString(USER_LIST, null) ?: return

        try {
            val array = JSONArray(json)
            for (i in 0 until array.length()) {
                val raw = array.optString(i)
                val data = raw.slice(5 until raw.length - 1)
                val tokens = data.split(',')
                val id = tokens[0].split('=')[1]
                val password = tokens[1].split('=')[1]
                val name = tokens[2].split('=')[1]
                val phoneNumber = tokens[3].split('=')[1]
                val address = tokens[4].split('=')[1]
                userList.add(User(id, password, name, phoneNumber, address))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun storeUserList(context: Context?) {
        val prefs = context?.getSharedPreferences(USER_LIST, Context.MODE_PRIVATE)
        val editor = prefs?.edit()

        val array = JSONArray()
        for (i in 0 until userList.size) {
            array.put(userList[i])
        }

        editor?.remove(USER_LIST)
        if (userList.isEmpty()) {
            editor?.putString(USER_LIST, null);
        } else {
            editor?.putString(USER_LIST, array.toString())
        }
        editor?.apply()
    }

    fun addUser(user: User) {
        userList.add(user)
    }

    fun findUser(id: String): User? {
        for (user in userList) {
            if (user.id == id) {
                return user
            }
        }
        return null
    }

    fun signIn(id: String, password: String): String {
        if (id.trim().isEmpty() || password.trim().isEmpty()) {
            return "모든 항목을 입력해야합니다."
        }

        val user = findUser(id) ?: return "존재하지 않는 아이디입니다."
        if (user.password != password) {
            return "비밀번호가 일치하지 않습니다."
        }

        this.user = user
        return "로그인 되었습니다."
    }
}