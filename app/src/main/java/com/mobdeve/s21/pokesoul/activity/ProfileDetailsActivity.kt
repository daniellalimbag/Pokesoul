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
import com.mobdeve.s21.pokesoul.model.User

class ProfileDetailsActivity : AppCompatActivity() {
    private lateinit var backBtn: ImageButton
    private lateinit var editBtn: Button
    private lateinit var userFriends: RecyclerView
    private lateinit var userPosts: RecyclerView
    private var friendItems: MutableList<Any> = mutableListOf()
    private var postItems: MutableList<Any> = mutableListOf() // Can hold User and filtered Post types
    private lateinit var currentUser: User // Variable to hold the passed user data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_details)

        // Retrieve the User object passed from the previous activity
        currentUser = intent.getSerializableExtra("USER_INSTANCE") as? User ?: DataHelper.user1

        // Initialize views
        backBtn = findViewById(R.id.backIbtn)
        editBtn = findViewById(R.id.editBtn)
        userFriends = findViewById(R.id.friendsRv)
        userFriends.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        userPosts = findViewById(R.id.postsRv)
        userPosts.layoutManager = LinearLayoutManager(this)

        val profilePicture = findViewById<ShapeableImageView>(R.id.userImageIv)
        profilePicture.setImageResource(currentUser.image) // Display the current user's image

        val userName = findViewById<TextView>(R.id.usernameTv)
        userName.text = currentUser.username // Display the current user's username

        val userBio = findViewById<TextView>(R.id.bioTv)
        userBio.text = currentUser.bio // Display the current user's bio

        DataHelper.loadFriendsData()
        // Load friends data and add only unique friends to friendItems
        currentUser.friends.forEach { friend ->
            if (friend !in friendItems) {
                friendItems.add(friend)
            }
        }
        userFriends.adapter = ProfileAdapter(friendItems, showNames = true)

        // Filter the posts for the current profile user
        val posts = DataHelper.loadPostData().filter { post -> post.creator.username == currentUser.username }
        postItems.addAll(posts)
        userPosts.adapter = ProfileAdapter(postItems, showNames = true)

        // Set click listener for the back button
        backBtn.setOnClickListener {
            finish()
        }

        // Set click listener for the edit button
        editBtn.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("USER_INSTANCE", currentUser) // Pass the current user to the edit activity
            startActivity(intent)
        }
    }
}
