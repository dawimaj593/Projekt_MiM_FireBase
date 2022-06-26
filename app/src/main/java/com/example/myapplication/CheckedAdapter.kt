package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class CheckedAdapter(var itemList : ArrayList<ItemClass>, val dataBaseReference : DatabaseReference) : RecyclerView.Adapter<CheckedAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.single_checked_row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.nameFoodText.text = itemList[position].name
        holder.locationFoodText.text = itemList[position].location
        holder.typeFoodText.text = itemList[position].type
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class Holder(view : View) : RecyclerView.ViewHolder(view) {
        val nameFoodText : TextView = view.findViewById(R.id.food_name_checked_textview)
        val locationFoodText : TextView = view.findViewById(R.id.food_location_checked_textview)
        val typeFoodText : TextView = view.findViewById(R.id.food_type_checked_textview)
    }
}