package dev.luischang.dpafirebase2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtCorreo: EditText = findViewById(R.id.txtEmail)
        val txtClave: EditText = findViewById(R.id.txtPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val db = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            var correo: String = txtCorreo.text.toString()
            var clave: String = txtClave.text.toString()

            db.signInWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Inicio Satisfactorio", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, PrincipalActivity::class.java))
                    } else {
                        Toast.makeText(this, "El correo y/o clave es incorrecto", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }


    }
}