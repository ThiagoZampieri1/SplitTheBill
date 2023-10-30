package com.example.splitthebill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

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
        }
        fun addMember(view: View) {
        }

        fun settleDebts(view: View) {
        }
    }

    data class GroupMember(val name: String, var amountPaid: Double, var itemBought: String)

}