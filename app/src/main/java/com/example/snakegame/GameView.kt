package com.example.snakegame

import android.content.Context
<<<<<<< HEAD
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.MotionEvent
=======
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28
import kotlin.random.Random

class GameView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

<<<<<<< HEAD
    private val snake = mutableListOf<Pair<Int, Int>>()

    private var direction = ""
    private var touchStartX = 0f
    private var touchStartY = 0f

    private val boxSize = 55
    private val maxX
        get() = width / boxSize

    private val maxY
        get() = height / boxSize
=======
    private val paint = Paint()

    private val snake = mutableListOf<Pair<Int, Int>>()

    private var direction = "RIGHT"

    private val boxSize = 50
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28

    private var foodX = 5
    private var foodY = 5

<<<<<<< HEAD
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

=======
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28
    init {
        snake.add(Pair(10, 10))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

<<<<<<< HEAD
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
=======
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
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28

        val head = snake.first()

        var newX = head.first
        var newY = head.second

        when(direction) {
            "UP" -> newY--
            "DOWN" -> newY++
            "LEFT" -> newX--
            "RIGHT" -> newX++
        }
<<<<<<< HEAD
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

=======

        snake.add(0, Pair(newX, newY))

        if (newX == foodX && newY == foodY) {
            foodX = Random.nextInt(0, 15)
            foodY = Random.nextInt(0, 20)
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28
        } else {
            snake.removeAt(snake.size - 1)
        }

        invalidate()
    }

    fun setDirection(dir: String) {
        direction = dir
    }
<<<<<<< HEAD
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
=======
>>>>>>> 75a32d5baa9b7c8d0280d6d59323c02d9714fd28
}