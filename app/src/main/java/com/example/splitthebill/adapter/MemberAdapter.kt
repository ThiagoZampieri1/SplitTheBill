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
    private data class TilesMembersHolder(val memberName: TextView, val item: TextView,val total: TextView,  val amountToReceive: TextView, val amountToPay: TextView)

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
            val tileIntegrantesHolder = TilesMembersHolder(amd.memberNameTextView, amd.itemBoughtTextView, amd.amountPaidTextView, amd.amountToReceiveTextView, amd.amountToPayTextView)
            convertView.tag = tileIntegrantesHolder
        }

        with (convertView.tag as TilesMembersHolder){
            memberName.text = "Nome: " + member.name
            item.text = "Itens comprados: ${member.itemBought1}, ${member.itemBought2} , ${member.itemBought3}"
            total.text = "Valor pago: " + (member.itemPrice1 + member.itemPrice2 + member.itemPrice3)
            amountToReceive.text = "Valor a receber: " + member.amountToReceive
            amountToPay.text= "Valor a pagar: " + member.amountToPay
        }

        return convertView;
    }
}