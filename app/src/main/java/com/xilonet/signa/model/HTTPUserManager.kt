package com.xilonet.signa.model

import android.util.Log
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import ru.gildor.coroutines.okhttp.await

const val URL = "https://xilonet.herokuapp.com/auth/login"

//TODO: Mandar errores específicos dependiendo de si hay un error del servidor, el login es incorrecto, etc.
object HTTPUserManager {
    private val client = OkHttpClient()
    private var user: UserInfo? = null

    init {
        Log.d("LOGIN", "created new")
    }

    // Returns null if the attempt is not successful
    suspend fun tryLogIn(email: String, password: String) : UserInfo? {
        val formBody = FormBody.Builder()
            .add("email", email)
            .add("password", password)
            .build()
        val request = Request.Builder()
            .url(URL)
            .post(formBody)
            .build()
        val result = client.newCall(request).await()
        return if(result.isSuccessful){
            Log.d("LOGIN", "Reached here")
            val body = result.body()
            val jsonString = body?.string()
            val userInfo = jsonString?.let { jsonToUserInfo(it) }
            body?.close()
            user = userInfo
            userInfo
        } else {
            null
        }
    }

    private fun jsonToUserInfo(json: String): UserInfo {
        val jsonObject = JSONObject(json)
        val token = jsonObject.getString("token")
        val userInfoObj = jsonObject.getJSONObject("user")
        val id = userInfoObj.getString("id")
        val firstName = userInfoObj.getString("first_name")
        val lastName = userInfoObj.getString("last_name")
        val email = userInfoObj.getString("email")
        val progressedCategories =
            jsonArrayToStringArray(userInfoObj.getJSONArray("progress"))
        val accumScore = userInfoObj.getInt("points")
        return UserInfo(id, token, firstName, lastName, email, progressedCategories, accumScore)
    }

    fun getUserInfo() = user
    fun nullUserInfo(){
        user = null
    }
}

fun jsonArrayToStringArray(jsonArray: JSONArray) : Array<String> {
    val result = Array<String>(jsonArray.length()) {_ -> ""}
    for(i in 0 until jsonArray.length()){
        result[i] = jsonArray.getString(i)
    }
    return result
}

data class UserInfo(
    val id: String,
    val token: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val progressedCategories: Array<String>,
    val accumScore: Int
)
