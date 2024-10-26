package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.CommentAdapter
import com.mobdeve.s21.pokesoul.adapter.NotificationAdapter
import com.mobdeve.s21.pokesoul.helper.DataHelper
import com.mobdeve.s21.pokesoul.model.Comment

class ViewCommentsActivity: AppCompatActivity(), View.OnClickListener{
    lateinit var backIv  : ImageView

    private lateinit var commentsRv : RecyclerView
    private var commentList : ArrayList<Comment> = DataHelper.loadCommentData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.view_comment)

        backIv = findViewById(R.id.backIv)

        commentList = DataHelper.loadCommentData()

        backIv.setOnClickListener(this)


        commentsRv = findViewById<RecyclerView>(R.id.commentRv)
        commentsRv.adapter = CommentAdapter(this.commentList)
        commentsRv.layoutManager = LinearLayoutManager(this)
        (commentsRv.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.VERTICAL
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backIv ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}