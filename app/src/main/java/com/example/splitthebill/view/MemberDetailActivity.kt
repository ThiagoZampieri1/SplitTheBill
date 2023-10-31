package com.example.splitthebill.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.splitthebill.R
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Member

class MemberDetailActivity : AppCompatActivity() {
    private val mda: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var member: Member
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)

        val memberNameTextView = findViewById<TextView>(R.id.memberNameTextView)
        val amountPaidTextView = findViewById<TextView>(R.id.amountPaidTextView)
        val itemBoughtTextView = findViewById<TextView>(R.id.itemBoughtTextView)
        val amountToReceiveTextView = findViewById<TextView>(R.id.amountToReceiveTextView)
        val amountToPayTextView = findViewById<TextView>(R.id.amountToPayTextView)

        val memberName = intent.getStringExtra("memberName")
        val amountPaid = intent.getDoubleExtra("amountPaid", 0.0)
        val itemBought = intent.getStringExtra("itemBought")
        val amountToReceive = intent.getDoubleExtra("amountToReceive", 0.0)
        val amountToPay = intent.getDoubleExtra("amountToPay", 0.0)

        memberNameTextView.text = memberName
        amountPaidTextView.text = "Valor pago: R$ $amountPaid"
        itemBoughtTextView.text = "Item comprado: $itemBought"
        amountToReceiveTextView.text = "Valor a receber: R$ $amountToReceive"
        amountToPayTextView.text = "Valor a ser pago: R$ $amountToPay"

}
}
