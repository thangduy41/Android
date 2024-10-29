package com.example.moneyexchange

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private lateinit var etAmount: EditText
    private lateinit var btnFromCurrency: Button
    private lateinit var btnToCurrency: Button
    private lateinit var tvResult: TextView

    private var fromCurrency: String = "VND"
    private var toCurrency: String = "USD"
    private val exchangeRates = mapOf(
        "VND" to 1.0,
        "USD" to 24000.0,
        "YEN" to 180.0,
        "EURO" to 28000.0,
        "GBP" to 32000.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etAmount = findViewById(R.id.etAmount)
        btnFromCurrency = findViewById(R.id.btnFromCurrency)
        btnToCurrency = findViewById(R.id.btnToCurrency)
        tvResult = findViewById(R.id.tvResult)

        // Thêm TextWatcher để lắng nghe thay đổi số tiền
        etAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertCurrency()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnFromCurrency.setOnClickListener {
            showCurrencyDialog(true)
        }

        btnToCurrency.setOnClickListener {
            showCurrencyDialog(false)
        }
    }

    private fun showCurrencyDialog(isFromCurrency: Boolean) {
        val currencies = exchangeRates.keys.toTypedArray()
        AlertDialog.Builder(this)
            .setTitle("Chọn đơn vị tiền tệ")
            .setItems(currencies) { _, which ->
                if (isFromCurrency) {
                    fromCurrency = currencies[which]
                    btnFromCurrency.text = "Từ: $fromCurrency"
                } else {
                    toCurrency = currencies[which]
                    btnToCurrency.text = "Đến: $toCurrency"
                }
                // Cập nhật kết quả ngay khi đơn vị tiền tệ thay đổi
                convertCurrency()
            }
            .show()
    }

    private fun convertCurrency() {
        val amount = etAmount.text.toString().toDoubleOrNull()
        if (amount != null) {
            val fromRate = exchangeRates[fromCurrency] ?: 1.0
            val toRate = exchangeRates[toCurrency] ?: 1.0
            val result = amount * (fromRate / toRate)
            val decimalFormat = DecimalFormat("#,##0.00")
            tvResult.text = "Kết quả: ${decimalFormat.format(result)} $toCurrency"
        } else {
            tvResult.text = "Kết quả sẽ hiển thị ở đây"
        }
    }
}