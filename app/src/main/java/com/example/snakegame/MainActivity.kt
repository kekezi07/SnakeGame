package com.example.snakegame

import android.os.Bundle
import android.os.Handler
import android.os.Looper
<<<<<<< HEAD
import android.widget.TextView
=======
import android.widget.Button
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
<<<<<<< HEAD
    private lateinit var txtScore: TextView
    private lateinit var txtBest: TextView
=======
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28

    private val handler = Handler(Looper.getMainLooper())

    private val gameLoop = object : Runnable {
<<<<<<< HEAD

        override fun run() {

            if (!gameView.gameOver) {

                gameView.moveSnake()

                txtScore.text = "🍎 ${gameView.score}"

                txtBest.text = "🏆 ${gameView.score}"
            }
=======
        override fun run() {

            gameView.moveSnake()
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28

            handler.postDelayed(this, 300)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        gameView = findViewById(R.id.gameView)
<<<<<<< HEAD
        txtScore = findViewById(R.id.txtScore)
        txtBest = findViewById(R.id.txtBest)


=======

        findViewById<Button>(R.id.btnUp).setOnClickListener {
            gameView.setDirection("UP")
        }

        findViewById<Button>(R.id.btnDown).setOnClickListener {
            gameView.setDirection("DOWN")
        }

        findViewById<Button>(R.id.btnLeft).setOnClickListener {
            gameView.setDirection("LEFT")
        }

        findViewById<Button>(R.id.btnRight).setOnClickListener {
            gameView.setDirection("RIGHT")
        }
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28

        handler.post(gameLoop)
    }
}