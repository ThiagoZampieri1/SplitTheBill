package com.example.splitthebill.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.databinding.ActivityMemberDetailBinding
import com.example.splitthebill.model.Member

class MemberAdapter (
    context: Context,
    private val groupMembers: MutableList<Member>
): ArrayAdapter<Member>(context, R.layout.activity_member_detail, groupMembers) {
    private data class TilesMembersHolder(val memberName: TextView, val itemPrice1: TextView, val item1: TextView, val itemPrice2: TextView, val item2: TextView, val itemPrice3: TextView, val item3: TextView,val total: TextView,  val amountToReceive: TextView, val amountToPay: TextView)

    private lateinit var amd: ActivityMemberDetailBinding

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var member = groupMembers[position]
        var convertView = convertView

        if (convertView == null) {
            amd = ActivityMemberDetailBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            convertView = amd.root;
            val tileIntegrantesHolder = TilesMembersHolder(amd.memberNameTextView, amd.itemBought1TextView, amd.itemPrice1TextView, amd.itemBought2TextView, amd.itemPrice2TextView, amd.itemBought3TextView, amd.itemPrice3TextView, amd.amountPaidTextView, amd.amountToReceiveTextView, amd.amountToPayTextView)
            convertView.tag = tileIntegrantesHolder
        }

        with (convertView.tag as TilesMembersHolder){
            memberName.text = "Nome: " + member.name
            item1.text = "Primeiro item comprado: " + member.itemBought1
            item2.text = "Segundo item comprado: " + member.itemBought2
            item3.text = "Terceiro item comprado: " + member.itemBought3
            itemPrice1.text = "Preço do primeiro item: " + member.itemPrice1
            itemPrice2.text = "Preço do segundo item: " + member.itemPrice2
            itemPrice3.text = "Preço do terceiro item: " + member.itemPrice3
            total.text = "Valor pago: " + (member.itemPrice1 + member.itemPrice2 + member.itemPrice3)
            amountToReceive.text = "Valor a receber: " + member.amountToReceive
            amountToPay.text= "Valor a pagar: " + member.amountToPay
        }

        return convertView;
    }
}