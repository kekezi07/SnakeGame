package com.example.snakegame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class GameView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()

    private val snake = mutableListOf<Pair<Int, Int>>()

    private var direction = "RIGHT"

    private val boxSize = 50

    private var foodX = 5
    private var foodY = 5

    init {
        snake.add(Pair(10, 10))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.BLACK)

        paint.color = Color.GREEN

        for (part in snake) {
            canvas.drawRect(
                (part.first * boxSize).toFloat(),
                (part.second * boxSize).toFloat(),
                ((part.first + 1) * boxSize).toFloat(),
                ((part.second + 1) * boxSize).toFloat(),
                paint
            )
        }

        paint.color = Color.RED

        canvas.drawRect(
            (foodX * boxSize).toFloat(),
            (foodY * boxSize).toFloat(),
            ((foodX + 1) * boxSize).toFloat(),
            ((foodY + 1) * boxSize).toFloat(),
            paint
        )
    }

    fun moveSnake() {

        val head = snake.first()

        var newX = head.first
        var newY = head.second

        when(direction) {
            "UP" -> newY--
            "DOWN" -> newY++
            "LEFT" -> newX--
            "RIGHT" -> newX++
        }

        snake.add(0, Pair(newX, newY))

        if (newX == foodX && newY == foodY) {
            foodX = Random.nextInt(0, 15)
            foodY = Random.nextInt(0, 20)
        } else {
            snake.removeAt(snake.size - 1)
        }

        invalidate()
    }

    fun setDirection(dir: String) {
        direction = dir
    }
}