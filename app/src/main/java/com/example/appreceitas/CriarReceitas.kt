package com.example.appreceitas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.appreceitas.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Recipe(
    val name: String = "",
    val ingredients: String = "",
    val preparationMethod: String = "",
    val preparationTime: String = ""
)

class CriarReceitas: AppCompatActivity() {

    private lateinit var database: DatabaseReference
    lateinit var sairbutton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_receita)

        // Inicialize a referência ao Firebase Realtime Database
        database = FirebaseDatabase.getInstance().reference

        sairbutton = findViewById(R.id.sairbutton)
        val etRecipeName = findViewById<EditText>(R.id.etRecipeName)
        val etIngredients = findViewById<EditText>(R.id.etIngredients)
        val etPreparationMethod = findViewById<EditText>(R.id.etPreparationMethod)
        val etPreparationTime = findViewById<EditText>(R.id.etPreparationTime)
        val btnSaveRecipe = findViewById<Button>(R.id.btnSaveRecipe)

        btnSaveRecipe.setOnClickListener {
            val recipeName = etRecipeName.text.toString()
            val ingredients = etIngredients.text.toString()
            val preparationMethod = etPreparationMethod.text.toString()
            val preparationTime = etPreparationTime.text.toString()

            if (recipeName.isEmpty() || ingredients.isEmpty() || preparationMethod.isEmpty() || preparationTime.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show()
            } else {
                saveRecipe(recipeName, ingredients, preparationMethod, preparationTime)
            }
        }
        sairbutton.setOnClickListener {
            val intent = Intent(applicationContext, TelaPrincipal::class.java)
            startActivity(intent)
        }
    }



    private fun saveRecipe(
        name: String,
        ingredients: String,
        preparationMethod: String,
        preparationTime: String
    ) {
        val recipeId = database.child("recipes").push().key

        if (recipeId != null) {
            val recipe = Recipe(name, ingredients, preparationMethod, preparationTime)

            database.child("recipes").child(recipeId).setValue(recipe)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Receita salva com sucesso!", Toast.LENGTH_SHORT).show()
                        clearFields()
                    } else {
                        Toast.makeText(this, "Erro ao salvar: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun clearFields() {
        findViewById<EditText>(R.id.etRecipeName).text.clear()
        findViewById<EditText>(R.id.etIngredients).text.clear()
        findViewById<EditText>(R.id.etPreparationMethod).text.clear()
        findViewById<EditText>(R.id.etPreparationTime).text.clear()
    }


}
