package com.mobdeve.s21.pokesoul.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.PostAdapter
import com.mobdeve.s21.pokesoul.helper.DataHelper
import com.mobdeve.s21.pokesoul.model.Post

class HomeFragment : Fragment() {

    private lateinit var postRv: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private var posts: List<Post> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        postRv = view.findViewById(R.id.postsRv)
        postRv.layoutManager = LinearLayoutManager(requireContext())

        posts = DataHelper.loadPostData()

        postAdapter = PostAdapter(posts)
        postRv.adapter = postAdapter

        return view
    }
}
