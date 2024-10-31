package com.example.firebaseauthapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseauthapp.databinding.ActivityLoginBinding
import com.example.firebaseauthapp.databinding.ActivitySignupBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signuptext.setOnClickListener{
            val intent = Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(intent)
        }

        binding.loginbtn.setOnClickListener{
            val email=binding.loginmail.text.toString().trim()
            val password=binding.loginpw.text.toString().trim()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this@LoginActivity,HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }

                }

        }


    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(intent)
        }
    }
}