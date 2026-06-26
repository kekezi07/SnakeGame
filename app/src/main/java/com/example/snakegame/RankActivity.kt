package com.example.snakegame


import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity


class RankActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_rank)



        // Pega a lista do XML

        val lista = findViewById<LinearLayout>(R.id.listRank)



        // Jogadores de teste

        val jogadores = arrayListOf(

            "🏆  1º  Késia     500",
            "🥈  2º  João      420",
            "🥉  3º  Maria     350",
            "🍎  4º  Pedro     200",
            "🍎  5º  Ana       150"

        )



        // Criar os textos na tela

        for (jogador in jogadores) {


            val texto = TextView(this)


            texto.text = jogador


            texto.textSize = 25f


            texto.setTextColor(
                android.graphics.Color.WHITE
            )


            // Fonte pixel



            texto.gravity = android.view.Gravity.CENTER


            texto.setPadding(
                0,
                18,
                0,
                18
            )

            texto.setShadowLayer(
                8f,
                3f,
                3f,
                android.graphics.Color.BLACK
            )

            lista.addView(texto)

        }


    }

}