package com.example.movieverse

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

import com.parse.LogInCallback




class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Check if there's a user logged in
        // if there is, then take them to MainActivity
        //if(ParseUser.getCurrentUser() != null)
        //{
           // goToMainActivity()
       // }

        findViewById<Button>(R.id.login_button).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username, password)
        }

        findViewById<Button>(R.id.signUpBtn).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signupUser(username, password)
        }
    }

    // Signup method

    private fun signupUser(username: String, password: String)
    {
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
               goToMainActivity()
            } else {
                // Sign up didn't succeed. Look at the ParseException
                e.printStackTrace()

            }
        }
    }

    // Login method
    private fun loginUser(username: String, password: String)
    {

        ParseUser.logInInBackground(username, password) { user, e ->
            if (user != null) {
                // Hooray! The user is logged in.
                Log.i(TAG, "Successfully logged in user ")
                goToMainActivity()
            } else {

                e.printStackTrace()
                // Signup failed. Look at the ParseException to see what happened.
                Toast.makeText(this, "Error Logging in", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToMainActivity()
    {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    companion object {
        const val TAG = "LoginActivity"
    }
}