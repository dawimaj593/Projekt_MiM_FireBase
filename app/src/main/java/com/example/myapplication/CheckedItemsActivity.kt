package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class CheckedItemsActivity : AppCompatActivity() {
    //  referencje
    lateinit var checkedRecyclerView : RecyclerView
    lateinit var dataBaseReference : DatabaseReference
    lateinit var itemList : ArrayList<ItemClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checked_items_activity)

        checkedRecyclerView = findViewById(R.id.checked_recyclerview)
        itemList = ArrayList()
        dataBaseReference = FirebaseDatabase.getInstance().getReference("ArrayOfItems")
        checkedRecyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
        checkedRecyclerView.adapter = CheckedAdapter(itemList, dataBaseReference)

    }

    override fun onStart() {
        super.onStart()

        dataBaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                for (item in snapshot.children){
                    val food = item.getValue() as MutableMap<String, Any>
                    val id: String = food.getValue("id") as String
                    val name: String =food.getValue("name") as String
                    val location: String = food.getValue("location") as String
                    val type: String = food.getValue("type") as String
                    val ischecked: Boolean = food.getValue("ischecked") as Boolean
                    if (ischecked == true){
                        itemList.add(ItemClass(id, name, location, type, ischecked))
                    }
                }
                checkedRecyclerView.adapter = CheckedAdapter(itemList, dataBaseReference)
            }
            override fun onCancelled(error: DatabaseError){}
        })
    }
}