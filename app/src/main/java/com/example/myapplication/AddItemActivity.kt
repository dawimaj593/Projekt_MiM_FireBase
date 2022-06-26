package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AddItemActivity : AppCompatActivity() {
    lateinit var nameEditText : EditText
    lateinit var locationEditText : EditText
    lateinit var typeEditText : EditText
    lateinit var addButton : Button
    lateinit var dataBaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item_activity)

        nameEditText = findViewById(R.id.name_textview)
        locationEditText = findViewById(R.id.location_textview)
        typeEditText = findViewById(R.id.type_textview)
        addButton = findViewById(R.id.add_item_to_list_button)
        dataBaseReference = FirebaseDatabase.getInstance().getReference("ArrayOfItems")

        addButton.setOnClickListener {
            val name : String = nameEditText.text.toString()
            val type : String = typeEditText.text.toString()
            val location : String = locationEditText.text.toString()
            val id : String = Date().time.toString()
            val ischecked  = false
            val itemClass = ItemClass(id, name, location, type, ischecked)

            dataBaseReference.child(id).setValue(itemClass)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}