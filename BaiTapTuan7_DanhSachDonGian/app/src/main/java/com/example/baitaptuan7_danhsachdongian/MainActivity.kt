package com.example.baitaptuan7_danhsachdongian

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNumber = findViewById<EditText>(R.id.edtNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val tvError = findViewById<TextView>(R.id.tvError)
        val listView = findViewById<ListView>(R.id.listView)

        btnShow.setOnClickListener {
            val input = edtNumber.text.toString()
            tvError.visibility = TextView.GONE  // Ẩn thông báo lỗi

            // Kiểm tra nếu input không phải là số nguyên dương
            val n = input.toIntOrNull()
            if (n == null || n <= 0 || input.contains(".")) {
                tvError.text = "Vui lòng nhập một số nguyên dương hợp lệ."
                tvError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val numberList = mutableListOf<Int>()

            when {
                radioEven.isChecked -> {
                    // Lấy danh sách số chẵn từ 0 đến n
                    for (i in 0..n step 2) {
                        numberList.add(i)
                    }
                }
                radioOdd.isChecked -> {
                    // Lấy danh sách số lẻ từ 1 đến n
                    for (i in 1..n step 2) {
                        numberList.add(i)
                    }
                }
                radioSquare.isChecked -> {
                    // Lấy danh sách số chính phương từ 0 đến n
                    var i = 0
                    while (i * i <= n) {
                        numberList.add(i * i)
                        i++
                    }
                }
                else -> {
                    tvError.text = "Vui lòng chọn loại số."
                    tvError.visibility = TextView.VISIBLE
                    return@setOnClickListener
                }
            }

            // Hiển thị danh sách số trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList)
            listView.adapter = adapter
        }
    }
}