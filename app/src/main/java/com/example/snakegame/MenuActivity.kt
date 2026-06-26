package com.example.snakegame

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)

        MusicManager.play(
            this,
            R.raw.menu_music
        )


        // Botões da tela inicial
        val play = findViewById<ImageButton>(R.id.btnPlay)
        val rank = findViewById<ImageButton>(R.id.btnRank)
        val credits = findViewById<ImageButton>(R.id.btnCredits)
        val buttonAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.button_enter
        )

        // Animação do logo
        val logo = findViewById<ImageView>(R.id.logoSnake)

        logo.animate()
            .alpha(1f)
            .setDuration(1000)
            .start()


        play.startAnimation(buttonAnimation)
        rank.startAnimation(buttonAnimation)
        credits.startAnimation(buttonAnimation)


        // Animação dos botões
        val anim = AnimationUtils.loadAnimation(
            this,
            R.anim.button_click
        )


        // PLAY
        play.setOnClickListener {



            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            startActivity(intent)
        }



        // RANK
        rank.setOnClickListener {

            rank.startAnimation(anim)

            // Futuramente abre a tela de ranking

            val intent = Intent(
                this,
                RankActivity::class.java
            )

            startActivity(intent)

        }



        // CREDITS
        credits.setOnClickListener {

            credits.startAnimation(anim)

            // Futuramente abre a tela de créditos

        }

    }
}