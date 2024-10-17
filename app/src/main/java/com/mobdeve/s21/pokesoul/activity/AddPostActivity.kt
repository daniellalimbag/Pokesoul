package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Post
import com.mobdeve.s21.pokesoul.model.User

class AddPostActivity : AppCompatActivity() {
    private lateinit var backBtn: ImageButton
    private lateinit var postBtn: Button
    private lateinit var postContentEt: EditText
    private lateinit var titleEt: EditText
    private lateinit var userImageIv: ImageView

    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_post)

        this.backBtn = findViewById(R.id.backIbtn)
        this.postBtn = findViewById(R.id.postBtn)
        this.postContentEt = findViewById(R.id.postEt)
        this.titleEt = findViewById(R.id.titleEt)
        this.userImageIv = findViewById(R.id.userImageIv)

        currentUser = User("Austin", R.drawable.player1)

        this.userImageIv.setImageResource(currentUser.image)

        this.backBtn.setOnClickListener {
            finish()
        }

        this.postBtn.setOnClickListener {
            val postContent = postContentEt.text.toString().trim()
            val postTitle = titleEt.text.toString().trim()

            if (postContent.isNotEmpty() && postTitle.isNotEmpty()) {
                val post = Post(
                    title = postTitle,
                    creator = currentUser,
                    time = "1 min ago",
                    content = postContent,
                    commentCount = 0,
                    likeCount = 0,
                    dislikeCount = 0
                )

                val intent = Intent()
                intent.putExtra("POST", post)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Please provide both a title and content.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
