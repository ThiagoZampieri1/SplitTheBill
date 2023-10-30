package com.example.splitthebill

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MemberDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)

        val memberNameTextView = findViewById<TextView>(R.id.memberNameTextView)
        val amountPaidTextView = findViewById<TextView>(R.id.amountPaidTextView)
        val itemBoughtTextView = findViewById<TextView>(R.id.itemBoughtTextView)

        val memberName = intent.getStringExtra("memberName")
        val amountPaid = intent.getDoubleExtra("amountPaid", 0.0)
        val itemBought = intent.getStringExtra("itemBought")

        memberNameTextView.text = memberName
        amountPaidTextView.text = "Valor pago: R$ $amountPaid"
        itemBoughtTextView.text = "Item comprado: $itemBought"
    }
}
