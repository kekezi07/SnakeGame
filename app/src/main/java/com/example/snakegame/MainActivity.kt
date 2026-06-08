package com.example.snakegame

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView

    private val handler = Handler(Looper.getMainLooper())

    private val gameLoop = object : Runnable {
        override fun run() {

            gameView.moveSnake()

            handler.postDelayed(this, 300)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        gameView = findViewById(R.id.gameView)

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

        handler.post(gameLoop)
    }
}