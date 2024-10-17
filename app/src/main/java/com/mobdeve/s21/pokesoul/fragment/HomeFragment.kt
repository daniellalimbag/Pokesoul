package com.mobdeve.s21.pokesoul.fragment

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.AddPostActivity
import com.mobdeve.s21.pokesoul.adapter.PostAdapter
import com.mobdeve.s21.pokesoul.helper.DataHelper
import com.mobdeve.s21.pokesoul.model.Post

class HomeFragment : Fragment() {

    private lateinit var postRv: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private var posts: MutableList<Post> = mutableListOf()
    private lateinit var addFab: FloatingActionButton

    private val addPostResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK && result.data != null) {
            val post = result.data?.getSerializableExtra("POST") as? Post
            post?.let {
                posts.add(0, it)
                postAdapter.notifyItemInserted(0)
                postRv.scrollToPosition(0)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        postRv = view.findViewById(R.id.postsRv)
        postRv.layoutManager = LinearLayoutManager(requireContext())
        posts = DataHelper.loadPostData().toMutableList()
        postAdapter = PostAdapter(posts)
        postRv.adapter = postAdapter

        addFab = view.findViewById(R.id.addFab)
        addFab.setOnClickListener {
            val intent = Intent(requireContext(), AddPostActivity::class.java)
            addPostResultLauncher.launch(intent)
        }

        return view
    }
}
