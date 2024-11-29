package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TelaPrincipal : AppCompatActivity() {

    lateinit var home: ImageButton
    lateinit var addButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_principal)

        home = findViewById(R.id.home)
        addButton = findViewById(R.id.add)

        val itemList = listOf("pizza","hamburguer","strogonoff","sadlksajd","sadlksajd","sadlksajd")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(itemList)




        home.setOnClickListener {
            val intent = Intent(applicationContext, TelaPrincipal::class.java)
            startActivity(intent)
        }

        addButton.setOnClickListener {
            val intent = Intent(applicationContext, CriarReceitas::class.java)
            startActivity(intent)
        }
    }


}
