package com.mobdeve.s21.pokesoul.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Post
import com.mobdeve.s21.pokesoul.model.User

class AddCommentActivity : AppCompatActivity(),  View.OnClickListener{

    private lateinit var backImgBtn : ImageButton
    private lateinit var commentBtn : Button

    private lateinit var contentTv : TextView

    lateinit var ref_post : Post
    lateinit var ref_user : User

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.add_comment)

        ref_user = User("Player 1", R.drawable.player1, "I like men and women", mutableListOf(), mutableListOf())

        contentTv = findViewById(R.id.contentTv)

        backImgBtn = findViewById(R.id.backImgBtn)
        backImgBtn.setOnClickListener(this)

        ref_post = (intent.getSerializableExtra("post_reference") as? Post)!!

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
                intent.putExtra("post_title", ref_post.title)
                intent.putExtra("post_creator", ref_post.creator.username)
                intent.putExtra("post_pfp",ref_post.creator.image)
                intent.putExtra("post_time", ref_post.time)
                intent.putExtra("post_content", ref_post.content)
                intent.putExtra("post_commentCount", ref_post.commentCount)
                intent.putExtra("post_likeCount", ref_post.likeCount)
                intent.putExtra("post_dislikeCount", ref_post.dislikeCount)

                intent.putExtra("comment_user", ref_user.username)
                intent.putExtra("comment_content", contentTv.text.toString())
                intent.putExtra("comment_time", "1 Min ago")
                startActivity(intent)
            }
        }
    }
}