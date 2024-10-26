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

class ForgotPasswordActivity: AppCompatActivity(), View.OnClickListener{

    lateinit var sendBtn : Button
    lateinit var emailInput : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.forgot_password)

        sendBtn = findViewById(R.id.sendBtn)
        emailInput = findViewById(R.id.emailEt)

        sendBtn.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.sendBtn -> {
                val email = emailInput.text.toString()
                if(!email.isEmpty()){
                    val intent = Intent(this, LoginUserActivity::class.java)
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