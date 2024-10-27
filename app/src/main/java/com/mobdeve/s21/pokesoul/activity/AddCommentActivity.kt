package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.mobdeve.s21.pokesoul.R

class AddCommentActivity : AppCompatActivity(),  View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.add_comment)

    }
    override fun onClick(v: View?) {

    }
}