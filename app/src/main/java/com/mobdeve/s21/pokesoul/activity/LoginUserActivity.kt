package com.mobdeve.s21.pokesoul.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.mobdeve.s21.pokesoul.R
import kotlin.math.log

class LoginUserActivity: AppCompatActivity(), View.OnClickListener {
    lateinit var loginBtn : Button
    lateinit var registerTv : TextView

    lateinit var usernameInput : EditText
    lateinit var passwordInput : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.login_user)

        loginBtn = findViewById(R.id.loginBtn)
        registerTv = findViewById(R.id.registerTv)

        usernameInput = findViewById(R.id.usernameEt)
        passwordInput = findViewById(R.id.passwordEt)

        loginBtn.setOnClickListener(this)
        registerTv.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
    when(v?.id){
        R.id.registerTv ->{
            val intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(intent)
        }
        R.id.loginBtn ->{
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if(!(username.isEmpty()) && !(password.isEmpty())){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(applicationContext,"Fill in all the required information", Toast.LENGTH_LONG).show()
            }
        }
        }
    }
    private fun EditText.isEmpty(): Boolean{
        return this.toString().trim().isEmpty()
    }
}