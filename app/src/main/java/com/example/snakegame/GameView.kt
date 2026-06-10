package com.example.snakegame

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.MotionEvent
import kotlin.random.Random

class GameView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val snake = mutableListOf<Pair<Int, Int>>()

    private var direction = ""
    private var touchStartX = 0f
    private var touchStartY = 0f

    private val boxSize = 55
    private val maxX
        get() = width / boxSize

    private val maxY
        get() = height / boxSize

    private var foodX = 5
    private var foodY = 5

    var score = 0
    var gameOver = false
    var gameStarted = false

    private val snakePaint = Paint().apply {
        color = Color.parseColor("#2962FF")
        isAntiAlias = true
    }

    private val foodPaint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
    }

    private val gridPaint = Paint().apply {
        color = Color.parseColor("#A7D948")
    }

    init {
        snake.add(Pair(10, 10))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawGrid(canvas)

        // comida
        canvas.drawCircle(
            (foodX * boxSize + boxSize / 2).toFloat(),
            (foodY * boxSize + boxSize / 2).toFloat(),
            22f,
            foodPaint
        )

        // cobra
        for ((index, part) in snake.withIndex()) {

            val left = (part.first * boxSize).toFloat()
            val top = (part.second * boxSize).toFloat()
            val right = left + boxSize
            val bottom = top + boxSize

            canvas.drawRoundRect(
                left,
                top,
                right,
                bottom,
                25f,
                25f,
                snakePaint
            )

            // olhos na cabeça
            if (index == 0) {

                val eyePaint = Paint().apply {
                    color = Color.WHITE
                }

                canvas.drawCircle(left + 18, top + 18, 5f, eyePaint)
                canvas.drawCircle(left + 38, top + 18, 5f, eyePaint)
            }
        }
        if (gameOver) {

            val textPaint = Paint().apply {
                color = Color.WHITE
                textSize = 100f
                textAlign = Paint.Align.CENTER
                isFakeBoldText = true
            }

            canvas.drawText(
                "GAME OVER",
                width / 2f,
                height / 2f,
                textPaint
            )
        }
    }

    private fun drawGrid(canvas: Canvas) {

        var toggle = false

        for (x in 0..20) {
            toggle = !toggle

            for (y in 0..35) {

                gridPaint.color =
                    if (toggle) Color.parseColor("#AAD751")
                    else Color.parseColor("#A2D149")

                canvas.drawRect(
                    (x * boxSize).toFloat(),
                    (y * boxSize).toFloat(),
                    ((x + 1) * boxSize).toFloat(),
                    ((y + 1) * boxSize).toFloat(),
                    gridPaint
                )

                toggle = !toggle
            }
        }
    }

    fun moveSnake() {
        if (!gameStarted) return

        val head = snake.first()

        var newX = head.first
        var newY = head.second

        when(direction) {
            "UP" -> newY--
            "DOWN" -> newY++
            "LEFT" -> newX--
            "RIGHT" -> newX++
        }
        if (newX < 0 || newY < 0 || newX >= maxX || newY >= maxY) {

            gameOver = true

            invalidate()

            return
        }

        snake.add(0, Pair(newX, newY))
        for (i in 1 until snake.size - 1) {

            if (snake[i].first == newX &&
                snake[i].second == newY) {

                gameOver = true

                invalidate()

                return
            }
        }

        if (newX == foodX && newY == foodY) {

            score++

            foodX = Random.nextInt(0, maxX)

            foodY = Random.nextInt(0, maxY)

        } else {
            snake.removeAt(snake.size - 1)
        }

        invalidate()
    }

    fun setDirection(dir: String) {
        direction = dir
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action) {

            MotionEvent.ACTION_DOWN -> {

                touchStartX = event.x
                touchStartY = event.y
            }

            MotionEvent.ACTION_UP -> {

                gameStarted = true

                val deltaX = event.x - touchStartX
                val deltaY = event.y - touchStartY

                if (kotlin.math.abs(deltaX) > kotlin.math.abs(deltaY)) {

                    if (deltaX > 0) {
                        direction = "RIGHT"
                    } else {
                        direction = "LEFT"
                    }

                } else {

                    if (deltaY > 0) {
                        direction = "DOWN"
                    } else {
                        direction = "UP"
                    }
                }
            }
        }

        return true
    }
}