package com.example.restapicustomwork

import Model.LoginRequest
import Network.RetrofitInstance
import Model.UserResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginTextView: TextView
    private lateinit var signupButton: Button
    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEditText = findViewById(R.id.user_email)
        passwordEditText = findViewById(R.id.user_password)
        loginTextView = findViewById(R.id.textView)
        signupButton = findViewById(R.id.signup)
        loginButton=findViewById(R.id.user_login_signin)

        // Handle login click
        loginButton.setOnClickListener {
            performLogin()
        }

        // Handle signup click
        signupButton.setOnClickListener {
            Toast.makeText(this, "Signup button clicked!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
    private fun performLogin() {

        val email=emailEditText.text.toString().trim();
        val password=passwordEditText.text.toString().trim();
        if(!email.isEmpty()){
            if(!password.isEmpty()){
                // Create an instance of UserRequest
                val userRequest = LoginRequest(email = email, password = password)

                // Make the network call using Retrofit
                val retrofitInstance = RetrofitInstance()
                val call = retrofitInstance.service.signIn(userRequest)
                call.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        if (response.isSuccessful) {
                            val userResponse = response.body()
                            // Handle successful response
                            // You can access the token and user details from userResponse
                            Toast.makeText(this@MainActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                            val intent=Intent(this@MainActivity,HomeActivity::class.java)
                            startActivity(intent)
                            // Navigate to another activity or store the token as needed
                        } else {
                            // Handle error response
                            Toast.makeText(this@MainActivity, "Login Failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        // Handle failure
                        Toast.makeText(this@MainActivity, "Login Failed: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })

                Toast.makeText(this, "Login Successfully........", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "password must not be empty", Toast.LENGTH_SHORT).show()
                passwordEditText.setError("Password must not be empty");
                return
            }

        }
        else{
            Toast.makeText(this, "Email must not be empty", Toast.LENGTH_SHORT).show()
            emailEditText.setError("Email must not be empty");
            return
        }
    }


}