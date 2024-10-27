package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Post

class AddCommentActivity : AppCompatActivity(),  View.OnClickListener{

    private lateinit var backImgBtn : ImageButton
    private lateinit var commentBtn : Button
    lateinit var ref_post : Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.add_comment)

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
                startActivity(intent)
            }
        }
    }
}