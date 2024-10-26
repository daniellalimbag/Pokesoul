package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.mobdeve.s21.pokesoul.R

class RegisterUserActivity: AppCompatActivity(), View.OnClickListener{
    lateinit var registerBtn : Button

    lateinit var usernameInput : EditText
    lateinit var  emailInput : EditText
    lateinit var  passwordInput : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.register_user)

        registerBtn = findViewById(R.id.registerBtn)
        usernameInput = findViewById(R.id.usernameEt)
        emailInput = findViewById(R.id.emailEt)
        passwordInput = findViewById(R.id.passwordEt)

        registerBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.registerBtn ->{
                val username = usernameInput.text.toString()
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()

                if(!(username.isEmpty()) && !(email.isEmpty()) && !(password.isEmpty())){
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