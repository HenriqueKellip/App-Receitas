package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp;

class MainActivity : AppCompatActivity() {

    lateinit var user: EditText
    lateinit var pass: EditText
    lateinit var loginButton: Button
    lateinit var cadastroButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        user = findViewById(R.id.userid)
        pass = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        cadastroButton = findViewById(R.id.buttonSignUp)

        loginButton.setOnClickListener {
            if (user.text.toString().equals("henrique") &&
                pass.text.toString().equals("123")
            ) {
                Toast.makeText(this, "foi", Toast.LENGTH_LONG).show()
                intent = Intent(applicationContext, TelaPrincipal::class.java)
                startActivity(intent)
            }
        }
        cadastroButton.setOnClickListener {
            intent = Intent(applicationContext, TelaCadastro::class.java)
            startActivity(intent)
        }



    }
}
