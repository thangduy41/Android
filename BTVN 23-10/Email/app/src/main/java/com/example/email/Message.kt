package com.example.email
data class Message(
    val sender: String,  // Tên người gửi
    val message: String, // Nội dung tin nhắn
    val time: String,    // Thời gian tin nhắn
    val avatar: String   // Ký tự đại diện (chữ cái đầu của tên người gửi)
)
