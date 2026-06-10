package com.example.snakegame

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var txtScore: TextView
    private lateinit var txtBest: TextView

    private val handler = Handler(Looper.getMainLooper())

    private val gameLoop = object : Runnable {

        override fun run() {

            if (!gameView.gameOver) {

                gameView.moveSnake()

                txtScore.text = "🍎 ${gameView.score}"

                txtBest.text = "🏆 ${gameView.score}"
            }

            handler.postDelayed(this, 300)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        gameView = findViewById(R.id.gameView)
        txtScore = findViewById(R.id.txtScore)
        txtBest = findViewById(R.id.txtBest)



        handler.post(gameLoop)
    }
}