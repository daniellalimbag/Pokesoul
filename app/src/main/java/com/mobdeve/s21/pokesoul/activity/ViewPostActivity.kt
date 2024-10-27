package com.mobdeve.s21.pokesoul.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.CommentAdapter
import com.mobdeve.s21.pokesoul.helper.DataHelper
import com.mobdeve.s21.pokesoul.model.Comment

class ViewPostActivity: AppCompatActivity(), View.OnClickListener{
    lateinit var backIv  : ImageView

    private lateinit var commentsRv : RecyclerView
    private var commentList : ArrayList<Comment> = DataHelper.loadCommentData()

    private lateinit var titleTextView : TextView
    private lateinit var  creatorTextView : TextView
    private lateinit var userSIv : ImageView
    private lateinit var timeTv : TextView
    private lateinit var contentTv : TextView
    private lateinit var commentCountTv : TextView
    private lateinit var likeCountTv : TextView
    private lateinit var dislikeCountTv : TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.view_post)

        backIv = findViewById(R.id.backIv)

        commentList = DataHelper.loadCommentData()

        titleTextView = findViewById(R.id.titleTv)
        creatorTextView = findViewById(R.id.usernameTv)
        userSIv = findViewById(R.id.userSiv)
        timeTv = findViewById(R.id.timeTv)
        contentTv = findViewById(R.id.contentTv)
        commentCountTv = findViewById(R.id.commentCountTv)
        likeCountTv = findViewById(R.id.likeCountTv)
        dislikeCountTv = findViewById(R.id.dislikeCountTv)

        val intent = intent

        titleTextView.text = intent.getStringExtra("post_title")
        creatorTextView.text = intent.getStringExtra("post_creator")
        userSIv.setImageResource(intent.getIntExtra("post_pfp",R.drawable.death_icon))
        timeTv.text = intent.getStringExtra("post_time")
        contentTv.text = intent.getStringExtra("post_content")
        commentCountTv.text = intent.getIntExtra("post_commentCount", 0).toString()
        likeCountTv.text = intent.getIntExtra("post_likeCount", 0).toString()
        dislikeCountTv.text = intent.getIntExtra("post_dislikeCount", 0).toString()

        backIv.setOnClickListener(this)

        val user = intent.getStringExtra("comment_user")
        val content = intent.getStringExtra("comment_content")
        val time = intent.getStringExtra("comment_time")

        val newComment = user?.let { u ->
            if (content != null && time != null) {
                Comment(u, content, time)
            } else {
                null
            }
        }




        commentsRv = findViewById<RecyclerView>(R.id.commentRv)
        commentsRv.adapter = CommentAdapter(this.commentList)

        var commentsAdapter = CommentAdapter(commentList)



        commentsRv.layoutManager = LinearLayoutManager(this)
        (commentsRv.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.VERTICAL

        if (newComment != null) {
            commentsAdapter.addComment(newComment)
        }
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