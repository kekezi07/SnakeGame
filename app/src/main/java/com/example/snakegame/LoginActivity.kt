package com.example.snakegame

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        MusicManager.play(
            this,
            R.raw.menu_music
        )


        val semLogin = findViewById<TextView>(R.id.btnSemLogin)


        semLogin.setOnClickListener {

            val intent = Intent(
                this,
                MainActivity::class.java
            )

            startActivity(intent)

        }


    }
}