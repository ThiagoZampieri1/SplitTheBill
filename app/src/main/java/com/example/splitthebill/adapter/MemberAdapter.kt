package com.example.splitthebill.adapter

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
    private data class TilesMembersHolder(val memberName: TextView, val amontPaid: TextView, val item: TextView, val amountToReceive: TextView, val amountToPay: TextView)

    private lateinit var amd: ActivityMemberDetailBinding

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
            val tileIntegrantesHolder = TilesMembersHolder(amd.memberNameTextView, amd.amountPaidTextView, amd.itemBoughtTextView, amd.amountToReceiveTextView, amd.amountToPayTextView)
            convertView.tag = tileIntegrantesHolder
        }

        with (convertView.tag as TilesMembersHolder){
            memberName.text = "Nome: " + member.name
            amontPaid.text = "Valor pago: " + member.amountPaid
            item.text = "Item comprado: " + member.itemBought
            amountToReceive.text = "Valor a receber: " + member.amountToReceive
            amountToPay.text= "Valor a pagar: " + member.amountToPay
        }

        return convertView;
    }
}