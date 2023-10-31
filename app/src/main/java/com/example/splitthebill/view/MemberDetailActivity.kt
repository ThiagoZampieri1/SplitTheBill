package com.example.splitthebill.view

import android.os.Bundle
import android.widget.TextView
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
        val itemBought1TextView = findViewById<TextView>(R.id.itemBought1TextView)
        val itemBought2TextView = findViewById<TextView>(R.id.itemBought2TextView)
        val itemBought3TextView = findViewById<TextView>(R.id.itemBought3TextView)
        val itemPrice1TextView = findViewById<TextView>(R.id.itemPrice1TextView)
        val itemPrice2TextView = findViewById<TextView>(R.id.itemPrice2TextView)
        val itemPrice3TextView = findViewById<TextView>(R.id.itemPrice3TextView)
        val amountToReceiveTextView = findViewById<TextView>(R.id.amountToReceiveTextView)
        val amountToPayTextView = findViewById<TextView>(R.id.amountToPayTextView)

        val memberName = intent.getStringExtra("memberName")
        val amountPaid = intent.getDoubleExtra("amountPaid", 0.0)
        val itemBought1 = intent.getStringExtra("item1")
        val itemBought2 = intent.getStringExtra("item2")
        val itemBought3 = intent.getStringExtra("item3")
        val itemPrice1 = intent.getDoubleExtra("itemPrice1", 0.0)
        val itemPrice2 = intent.getDoubleExtra("itemPrice2", 0.0)
        val itemPrice3 = intent.getDoubleExtra("itemPrice3", 0.0)
        val amountToReceive = intent.getDoubleExtra("amountToReceive", 0.0)
        val amountToPay = intent.getDoubleExtra("amountToPay", 0.0)

        memberNameTextView.text = memberName
        amountPaidTextView.text = "Valor pago: R$ $amountPaid"
        itemBought1TextView.text = "Primeiro Item comprado: $itemBought1"
        itemBought2TextView.text = "Segundo Item comprado: $itemBought2"
        itemBought3TextView.text = "Terceiro Item comprado: $itemBought3"
        itemPrice1TextView.text = "Preço do primeiro Item comprado: $itemPrice1"
        itemPrice2TextView.text = "Preço do segundo Item comprado: $itemPrice2"
        itemPrice3TextView.text = "Preço do terceiro Item comprado: $itemPrice3"
        amountToReceiveTextView.text = "Valor a receber: R$ $amountToReceive"
        amountToPayTextView.text = "Valor a ser pago: R$ $amountToPay"

}
}
