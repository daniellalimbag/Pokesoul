package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.ProfileAdapter
import com.mobdeve.s21.pokesoul.helper.DataHelper

class ProfileDetailsActivity : AppCompatActivity() {
    private lateinit var backBtn: ImageButton
    private lateinit var editBtn: Button
    private lateinit var userFriends: RecyclerView
    private lateinit var userPosts: RecyclerView
    private var friendItems: MutableList<Any> = mutableListOf()
    private var postItems: MutableList<Any> = mutableListOf() // Can hold User and filtered Post types

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_details)

        backBtn = findViewById(R.id.backIbtn)
        editBtn = findViewById(R.id.editBtn)

        userFriends = findViewById(R.id.friendsRv)
        userFriends.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        userPosts = findViewById(R.id.postsRv)
        userPosts.layoutManager = LinearLayoutManager(this)

        val profilePicture = findViewById<ShapeableImageView>(R.id.userImageIv)
        profilePicture.setImageResource(DataHelper.user1.image)

        val userName = findViewById<TextView>(R.id.usernameTv)
        userName.setText(DataHelper.user1.username)

        val userBio = findViewById<TextView>(R.id.bioTv)
        userBio.setText(DataHelper.user1.bio)

        // Load friends data and add only unique friends to friendItems
        DataHelper.loadFriendsData()
        DataHelper.user1.friends.forEach { friend ->
            if (friend !in friendItems) {
                friendItems.add(friend)
            }
        }
        userFriends.adapter = ProfileAdapter(friendItems, showNames = true)

        // Filter the posts for the current profile user
        val posts = DataHelper.loadPostData().filter { post -> post.creator.username == "Player 1" }
        postItems.addAll(posts) // Add the filtered posts to the list
        userPosts.adapter = ProfileAdapter(postItems, showNames = true)

        // Set click listener for the back button
        backBtn.setOnClickListener {
            finish()
        }

        // Set click listener for the edit button
        editBtn.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
