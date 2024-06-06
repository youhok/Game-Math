package com.example.gamemath

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var answerInput: EditText
    private lateinit var resultText: TextView
    private lateinit var startButton: Button
    private lateinit var submitButton: Button
    private lateinit var tryAgainButton: Button

    private var num1: Int = 0
    private var num2: Int = 0
    private lateinit var operator: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionText = findViewById(R.id.question_text)
        answerInput = findViewById(R.id.answer_input)
        resultText = findViewById(R.id.result_text)
        startButton = findViewById(R.id.start_button)
        submitButton = findViewById(R.id.submit_button)
        tryAgainButton = findViewById(R.id.try_again_button)

        startButton.setOnClickListener {
            generateQuestion()
            questionText.visibility = View.VISIBLE
            answerInput.visibility = View.VISIBLE
            submitButton.visibility = View.VISIBLE
        }

        submitButton.setOnClickListener {
            val answer = answerInput.text.toString().toDoubleOrNull()
            if (answer == result()) {
                resultText.text = "Correct!"
                resultText.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            } else {
                resultText.text = "Wrong!!!"
                resultText.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            }
            resultText.visibility = View.VISIBLE
            tryAgainButton.visibility = View.VISIBLE
        }

        tryAgainButton.setOnClickListener {
            generateQuestion()
            answerInput.text.clear()
            resultText.visibility = View.GONE
            tryAgainButton.visibility = View.GONE
        }
    }

    private fun generateQuestion() {
        num1 = Random.nextInt(1, 10)
        num2 = Random.nextInt(1, 10)
        operator = listOf("+", "-", "*", "/").random()

        questionText.text = when (operator) {
            "+" -> "$num1 + $num2"
            "-" -> "$num1 - $num2"
            "*" -> "$num1 * $num2"
            "/" -> {
                // Ensure num2 is not zero for division
                num2 = Random.nextInt(1, 10)
                "$num1 / $num2"
            }
            else -> ""
        }
    }

    private fun result(): Double {
        return when (operator) {
            "+" -> num1 + num2.toDouble()
            "-" -> num1 - num2.toDouble()
            "*" -> num1 * num2.toDouble()
            "/" -> num1.toDouble() / num2.toDouble()
            else -> 0.0
        }
    }
}
