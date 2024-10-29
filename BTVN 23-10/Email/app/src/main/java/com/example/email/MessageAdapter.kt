package com.example.email

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    // ViewHolder để lưu trữ các thành phần của mỗi mục tin nhắn
    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSender: TextView = itemView.findViewById(R.id.tvSender)
        val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvAvatar: TextView = itemView.findViewById(R.id.tvAvatar)
    }

    // Tạo ViewHolder mới
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    // Gán dữ liệu cho ViewHolder
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.tvSender.text = message.sender         // Gán tên người gửi
        holder.tvMessage.text = message.message       // Gán nội dung tin nhắn
        holder.tvTime.text = message.time             // Gán thời gian gửi tin nhắn
        holder.tvAvatar.text = message.avatar         // Gán ký tự đại diện
    }

    // Trả về số lượng tin nhắn
    override fun getItemCount(): Int {
        return messages.size
    }
}
