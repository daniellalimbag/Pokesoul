package com.mobdeve.s21.pokesoul.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.NotificationAdapter
import com.mobdeve.s21.pokesoul.helper.DataHelper
import com.mobdeve.s21.pokesoul.model.Notification

class NotificationFragment : Fragment() {
    private lateinit var notificationRv: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var notifications : ArrayList<Notification>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        notificationRv = view.findViewById(R.id.notificationRv)
        notificationRv.layoutManager = LinearLayoutManager(requireContext())
        notifications = DataHelper.loadNotificationData()
        notificationAdapter = NotificationAdapter(notifications)
        notificationRv.adapter = notificationAdapter

        return view
    }


}