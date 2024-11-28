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
    private var notifications: MutableList<Notification> = mutableListOf()
    private val db = FirebaseFirestore.getInstance() // Firestore instance


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


        fetchNotifications()

        return view
    }

    private fun fetchNotifications() {
        db.collection("Notification")
            .get() // Fetch the documents in the collection
            .addOnSuccessListener { documents ->
                // Clear the list in the fragment before adding new notifications
                notifications.clear()

                // Add notifications from Firestore to the list
                for (document in documents) {
                    // Create a Notification object from each document
                    val notification = document.toObject(Notification::class.java)
                    notifications.add(notification)
                }


                // Update the adapter with the new notifications
                notificationAdapter.updateNotifications()

                // Log the notifications to verify

            }
            .addOnFailureListener { exception ->
                // Log any errors
                Log.w("Notification", "Error getting documents: ", exception)
            }
    }


}
