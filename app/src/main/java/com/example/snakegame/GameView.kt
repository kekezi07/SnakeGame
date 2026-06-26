package com.example.snakegame

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class GameView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val snake = mutableListOf<Pair<Int, Int>>()

    private var direction = ""

    private var lastDirection = ""

    private var touchStartX = 0f
    private var touchStartY = 0f
    private var offsetX = 0f
    private var offsetY = 0f

    private val boxSize = 60

    private val maxX
        get() = width / boxSize

    private val maxY
        get() = height / boxSize

    private var foodX = 5
    private var foodY = 5

    var score = 0

    var gameOver = false

    var gameStarted = false

    private var snakeColor = Color.parseColor("#2962FF")

    private val snakePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = snakeColor
        style = Paint.Style.FILL
    }

    private val foodPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val gridPaint = Paint()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        snake.clear()

        snake.add(
            Pair(
                maxX / 2,
                maxY / 2
            )
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        offsetX = ((width - (maxX * boxSize)) / 2).toFloat()
        offsetY = ((height - (maxY * boxSize)) / 2).toFloat()

        canvas.save()
        canvas.translate(offsetX, offsetY)

        drawGrid(canvas, maxX, maxY)

        // comida
        // maçã
        canvas.drawCircle(
            (foodX * boxSize + boxSize / 2).toFloat(),
            (foodY * boxSize + boxSize / 2).toFloat(),
            18f,
            foodPaint
        )

// brilho
        val shinePaint = Paint().apply {
            color = Color.WHITE
        }

        canvas.drawCircle(
            (foodX * boxSize + boxSize / 2 - 6).toFloat(),
            (foodY * boxSize + boxSize / 2 - 6).toFloat(),
            5f,
            shinePaint
        )

// cabinho
        val stemPaint = Paint().apply {
            color = Color.parseColor("#5D4037")
            strokeWidth = 5f
        }

        canvas.drawLine(
            (foodX * boxSize + boxSize / 2).toFloat(),
            (foodY * boxSize + 10).toFloat(),
            (foodX * boxSize + boxSize / 2).toFloat(),
            (foodY * boxSize + 20).toFloat(),
            stemPaint
        )

        // cobra
        for ((index, part) in snake.withIndex()) {

            val left = (part.first * boxSize).toFloat()
            val top = (part.second * boxSize).toFloat()
            val right = left + boxSize
            val bottom = top + boxSize

            // corpo
            canvas.drawRoundRect(
                left + 4,
                top + 4,
                right - 4,
                bottom - 4,
                30f,
                30f,
                snakePaint
            )

            // cabeça
            if (index == 0) {

                // olhos
                val eyePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.WHITE
                }

                // pupilas
                val pupilPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.BLACK
                }

                // brilho
                val shinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.WHITE
                }

                // olho esquerdo
                canvas.drawCircle(left + 22, top + 28, 10f, eyePaint)

                // olho direito
                canvas.drawCircle(left + 48, top + 28, 10f, eyePaint)

                // pupilas
                canvas.drawCircle(left + 22, top + 28, 4f, pupilPaint)

                canvas.drawCircle(left + 48, top + 28, 4f, pupilPaint)

                // brilhos
                canvas.drawCircle(left + 19, top + 25, 2f, shinePaint)

                canvas.drawCircle(left + 45, top + 25, 2f, shinePaint)
            }
        }

// AQUI FORA DO FOR
        canvas.restore()        


            // olhos

        // texto inicial
        if (!gameStarted && !gameOver) {




        }

        // game over
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

    private fun drawGrid(canvas: Canvas, maxX: Int, maxY: Int) {

        var toggle: Boolean

        for (x in 0 until maxX) {

            toggle = x % 2 == 0

            for (y in 0 until maxY) {

                gridPaint.color =
                    if (toggle)
                        Color.parseColor("#AAD751")
                    else
                        Color.parseColor("#A2D149")

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

        // colisão parede
        if (
            newX < 0 ||
            newY < 0 ||
            newX >= maxX ||
            newY >= maxY
        ) {

            gameOver = true

            lastDirection = direction

            invalidate()


            return
        }

        // colisão corpo
        for (part in snake) {

            if (part.first == newX &&
                part.second == newY) {

                gameOver = true

                invalidate()

                return
            }
        }

        snake.add(0, Pair(newX, newY))

        // comida
        if (newX == foodX && newY == foodY) {

            score++
            snakeColor = Color.rgb(
                Random.nextInt(50, 255),
                Random.nextInt(50, 255),
                Random.nextInt(50, 255)
            )

            snakePaint.color = snakeColor

            foodX = Random.nextInt(1, maxX - 1)

            foodY = Random.nextInt(1, maxY - 1)

        } else {

            snake.removeAt(snake.size - 1)
        }

        invalidate()
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

                if (kotlin.math.abs(deltaX) >
                    kotlin.math.abs(deltaY)) {


                    // movimento horizontal

                    if (deltaX > 0 && lastDirection != "LEFT") {

                        direction = "RIGHT"

                    } else if (deltaX < 0 && lastDirection != "RIGHT") {

                        direction = "LEFT"

                    }


                } else {


                    // movimento vertical

                    if (deltaY > 0 && lastDirection != "UP") {

                        direction = "DOWN"

                    } else if (deltaY < 0 && lastDirection != "DOWN") {

                        direction = "UP"

                    }

                }
            }
        }

        return true
    }
}