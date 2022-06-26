package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class UncheckedAdapter(var itemList : ArrayList<ItemClass>, val dataBaseReference : DatabaseReference) : RecyclerView.Adapter<UncheckedAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.single_uchecked_row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.nameFoodText.text = itemList[position].name
        holder.locationFoodText.text = itemList[position].location
        holder.typeFoodText.text = itemList[position].type

        holder.deleteFoodButton.setOnClickListener {
            dataBaseReference.child(itemList[holder.adapterPosition].id).removeValue()
        }
        holder.checkedFoodButton.setOnClickListener {
            dataBaseReference.child(itemList[holder.adapterPosition].id).child("ischecked").setValue(true)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class Holder(view : View) : RecyclerView.ViewHolder(view) {
        val nameFoodText : TextView = view.findViewById(R.id.item_name_textview)
        val locationFoodText : TextView = view.findViewById(R.id.item_location_textview)
        val typeFoodText : TextView = view.findViewById(R.id.item_type_textview)
        val checkedFoodButton : Button = view.findViewById(R.id.add_to_checked_button)
        val deleteFoodButton : Button = view.findViewById(R.id.delete_button)
    }
}