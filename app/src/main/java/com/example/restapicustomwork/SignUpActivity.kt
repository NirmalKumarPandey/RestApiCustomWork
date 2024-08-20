package com.example.restapicustomwork

import Network.RetrofitInstance
import Model.UserRequest
import Model.UserResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var userText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var buttonSignUp: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        emailEditText=findViewById(R.id.user_email)
        userText=findViewById(R.id.user_name)
        passwordEditText=findViewById(R.id.user_password)
        buttonSignUp=findViewById(R.id.button_signup)

        buttonSignUp.setOnClickListener{
            performSignUp()
        }

    }
    private fun performSignUp() {
        val userName = userText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validate input fields
        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create an instance of UserRequest
        val userRequest = UserRequest(email = email, password = password, userName = userName)

        // Make the network call using Retrofit
        val retrofitInstance = RetrofitInstance()
        val call = retrofitInstance.service.signUp(userRequest)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    Toast.makeText(this@SignUpActivity, "SignUp Successful!", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@SignUpActivity,MainActivity::class.java)
                    startActivity(intent)

                    // Handle success, e.g., navigate to another activity
                } else {
                    Toast.makeText(this@SignUpActivity, "SignUp Failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "SignUp Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}