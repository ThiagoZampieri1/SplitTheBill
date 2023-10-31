package com.example.splitthebill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private var groupMembers = mutableListOf<GroupMember>()
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)

        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedMember = groupMembers[position]

                // Crie uma Intent para abrir a tela de detalhes (MemberDetailActivity)
                val intent = Intent(this, MemberDetailActivity::class.java)

                // Passe os dados do integrante como extras na Intent
                intent.putExtra("memberName", selectedMember.name)
                intent.putExtra("amountPaid", selectedMember.amountPaid)
                intent.putExtra("itemBought", selectedMember.itemBought)

                // Inicie a nova atividade
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

                // Adicione o novo integrante à lista
                val newMember = GroupMember(name, amountPaid, itemBought)
                groupMembers.add(newMember)

                // Atualize a ListView
                adapter.add(name)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }
    data class GroupMember(val name: String, var amountPaid: Double, var itemBought: String)

}