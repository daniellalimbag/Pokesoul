package com.mobdeve.s21.pokesoul.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.NotificationAdapter
import com.mobdeve.s21.pokesoul.model.Notification

class NotificationFragment : Fragment() {
    private lateinit var notificationRv: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private var notifications: MutableList<Notification> = mutableListOf() // Initialize here

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        // Set up RecyclerView
        notificationRv = view.findViewById(R.id.notificationRv)
        notificationRv.layoutManager = LinearLayoutManager(requireContext())

        // Set up the adapter with an empty list initially
        notificationAdapter = NotificationAdapter(notifications)
        notificationRv.adapter = notificationAdapter

        // Fetch notifications from Firestore
        fetchNotificationsFromFirestore()

        return view
    }

    // Fetch notifications from Firestore and update the RecyclerView
    private fun fetchNotificationsFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Notification")
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    // Handle the notifications data
                    for (document in documents) {
                        val notification = document.toObject(Notification::class.java)
                        // Process notification
                    }
                } else {
                    // No notifications found
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }
    }

    }
