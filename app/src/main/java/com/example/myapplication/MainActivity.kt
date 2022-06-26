package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

//  referencje
    lateinit var uncheckedRecyclerView : RecyclerView
    lateinit var checkedActivityButton : Button
    lateinit var addItemActivityButton : Button
    lateinit var dataBaseReference : DatabaseReference
    lateinit var itemList : ArrayList<ItemClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Inicjalizacje
        uncheckedRecyclerView = findViewById(R.id.recycler_view_unchecked)
        checkedActivityButton = findViewById(R.id.set_checked_list_button)
        addItemActivityButton = findViewById(R.id.add_new_item_button)
        itemList = ArrayList()
        dataBaseReference = FirebaseDatabase.getInstance().getReference("ArrayOfItems")
        uncheckedRecyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
        uncheckedRecyclerView.adapter = UncheckedAdapter(itemList, dataBaseReference)
    }

    override fun onStart() {
        super.onStart()

        //  Słuchacz z pobieraniem danych
        dataBaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                for (item in snapshot.children){
                    val food = item.getValue() as MutableMap<String, Any>
                    val id: String = food.getValue("id") as String
                    val name: String =food.getValue("name") as String
                    val location: String = food.getValue("location") as String
                    val type: String = food.getValue("type") as String
                    val ischecked: Boolean = food.getValue("ischecked") as Boolean
                    if (ischecked == false){
                        itemList.add(ItemClass(id, name, location, type, ischecked))
                    }
                }
                uncheckedRecyclerView.adapter = UncheckedAdapter(itemList, dataBaseReference)
            }
            override fun onCancelled(error: DatabaseError){}
        })

        //  Przejście do dodawania elementu
        addItemActivityButton.setOnClickListener {
            val intent1 = Intent(this, AddItemActivity::class.java)
            startActivity(intent1)
        }

        //  Przejście do wyświetlania elementów sprawdzonych
        checkedActivityButton.setOnClickListener {
            val intent2 = Intent(this, CheckedItemsActivity::class.java)
            startActivity(intent2)
        }
    }
    public fun xxx(){
        uncheckedRecyclerView.id
    }
}