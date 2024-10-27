package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.mobdeve.s21.pokesoul.R

class AddCommentActivity : AppCompatActivity(),  View.OnClickListener{

    private lateinit var backImgBtn : ImageButton
    private lateinit var commentBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.add_comment)

        backImgBtn = findViewById(R.id.backImgBtn)
        backImgBtn.setOnClickListener(this)

        val referencePost = intent.getSerializableExtra("post_reference")


        commentBtn = findViewById(R.id.commentBtn)
        commentBtn.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backImgBtn -> {
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            R.id.commentBtn -> {
                var intent = Intent(this, ViewPostActivity::class.java)
                startActivity(intent)
            }
        }
    }
}