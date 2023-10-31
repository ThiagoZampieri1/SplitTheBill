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
                intent.putExtra("amountPaid", (selectedMember.itemPrice1 + selectedMember.itemPrice2 + selectedMember.itemPrice3))
                intent.putExtra("item1", selectedMember.itemBought1)
                intent.putExtra("item2", selectedMember.itemBought2)
                intent.putExtra("item3", selectedMember.itemBought3)
                intent.putExtra("itemPrice1", selectedMember.itemPrice1)
                intent.putExtra("itemPrice2", selectedMember.itemPrice2)
                intent.putExtra("itemPrice3", selectedMember.itemPrice3)
                intent.putExtra("amountToReceive", selectedMember.amountToReceive)

                startActivity(intent)
            }

        amb.listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->

            val selectedMember = groupMembers[position]

            val dialog = AlertDialog.Builder(this)
                .setTitle("Excluir membro")
                .setMessage("Tem certeza de que deseja excluir esse membro?")
                .setPositiveButton("Sim") { _, _ ->

                    groupMembers.remove(selectedMember)

                    memberAdapter.notifyDataSetChanged()

                }
                .setNegativeButton("Cancelar", null)
                .create()

            dialog.show()

            true
        }
    }
    fun showAddMemberDialog(view: View) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_member, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val itemBought1EditText = dialogView.findViewById<EditText>(R.id.itemBought1EditText)
        val itemBought2EditText = dialogView.findViewById<EditText>(R.id.itemBought2EditText)
        val itemBought3EditText = dialogView.findViewById<EditText>(R.id.itemBought3EditText)
        val itemPrice1EditText = dialogView.findViewById<EditText>(R.id.Item1EditText)
        val itemPrice2EditText = dialogView.findViewById<EditText>(R.id.Item2EditText)
        val itemPrice3EditText = dialogView.findViewById<EditText>(R.id.Item3EditText)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Adicionar Membro")
            .setView(dialogView)
            .setPositiveButton("Adicionar") { _, _ ->
                val name = nameEditText.text.toString()
                val itemBought1 = itemBought1EditText.text.toString()
                val itemBought2 = itemBought2EditText.text.toString()
                val itemBought3 = itemBought3EditText.text.toString()
                val itemPrice1 = itemPrice1EditText.text.toString().toDouble()
                val itemPrice2 = itemPrice2EditText.text.toString().toDouble()
                val itemPrice3 = itemPrice3EditText.text.toString().toDouble()
                val amountPaid = itemPrice1 + itemPrice2 + itemPrice3

                val newMember = Member(name, amountPaid, itemPrice1, itemPrice2, itemPrice3, itemBought1, itemBought2, itemBought3,  0.0, 0.0 )
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

    private fun CalculateAmountToReceive(groupNumber: Int, total: Double, amountPaid: Double): Double{
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

    private fun CalculateAmountToPay(groupNumber: Int, total: Double, amountPaid: Double): Double{
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


