package com.example.email

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Dữ liệu mẫu
        val messages = listOf(
            Message("Edurila.com", "Hello world", "12:34 PM", "E"),
            Message("Chris Abad", "Xin chao the gioi", "11:22 AM", "C"),
            Message("Tuto.com", "Toi nay di an khong", "11:04 AM", "T"),
            Message("support", "he lo", "10:26 AM", "S"),
            Message("Matt from Ionic", "ngon ", "10:00 AM", "M") ,
            Message("Chris Abad", "Xin chao the gioi", "11:22 AM", "C"),
            Message("Tuto.com", "Toi nay di an khong", "11:04 AM", "T"),
            Message("support", "he lo", "10:26 AM", "S"),
            Message("Matt from Ionic", "ngon", "10:00 AM", "M")
        )

        // Khởi tạo RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MessageAdapter(messages)
    }
}