package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.splitthebill.R
import com.example.splitthebill.adapter.MemberAdapter
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Member

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var groupMembers = mutableListOf<Member>()

    private lateinit var memberAdapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        memberAdapter = MemberAdapter(this, groupMembers)
        amb.listView.adapter = memberAdapter

        amb.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedMember = groupMembers[position]

                val intent = Intent(this@MainActivity, MemberDetailActivity::class.java)

                intent.putExtra("memberName", selectedMember.name)
                intent.putExtra("amountPaid", selectedMember.amountPaid)
                intent.putExtra("itemBought", selectedMember.itemBought)
                intent.putExtra("amountToReceive", selectedMember.amountToReceive)

                startActivity(intent)
            }
    }
    fun showAddMemberDialog(view: View) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_member, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val amountPaidEditText = dialogView.findViewById<EditText>(R.id.amountPaidEditText)
        val itemBoughtEditText = dialogView.findViewById<EditText>(R.id.itemBoughtEditText)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Adicionar Membro")
            .setView(dialogView)
            .setPositiveButton("Adicionar") { _, _ ->
                val name = nameEditText.text.toString()
                val amountPaid = amountPaidEditText.text.toString().toDouble()
                val itemBought = itemBoughtEditText.text.toString()

                val newMember = Member(name, amountPaid, itemBought,  0.0, 0.0 )
                groupMembers.add(newMember)

                memberAdapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }

    fun shareTheBill(view: View){
        var total: Double =  0.0
        for (member in groupMembers) {
            total+=member.amountPaid;
        }
        for (member in groupMembers) {
            member.amountToReceive = CalculateAmountToReceive(groupMembers.size, total, member.amountPaid)
            member.amountToPay = CalculateAmountToPay(groupMembers.size, total, member.amountPaid)
        }
        memberAdapter.notifyDataSetChanged()
        println(groupMembers)
    }

    fun CalculateAmountToReceive(groupNumber: Int, total: Double, amountPaid: Double): Double{
        val amountToReceive: Double;
        if (groupNumber == 1){
            amountToReceive = 0.0;
        }  else if (amountPaid > (total / groupNumber)) {
            amountToReceive = ((total / groupNumber) - amountPaid) * (-1);
        } else{
            amountToReceive = 0.0
        }
        return amountToReceive;
    }

    fun CalculateAmountToPay(groupNumber: Int, total: Double, amountPaid: Double): Double{
        val AmountToPay: Double;
        if (groupNumber == 1){
            AmountToPay = 0.0;
        } else if (amountPaid == 0.0) {
            AmountToPay = total / groupNumber;
        }
        else if (amountPaid < (total / groupNumber)) {
            AmountToPay = (total / groupNumber) - amountPaid;
        } else {
            AmountToPay = 0.0
        }
        return AmountToPay;
    }

}


