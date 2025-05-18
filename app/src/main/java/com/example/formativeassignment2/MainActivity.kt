package com.example.formativeassignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.utilities.Score

class QuizActivity : AppCompatActivity() {

    // Questions and their corresponding True/False answers
    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994.",
        "The Berlin Wall fell in 1999.",
        "The Roman Empire fell in 476 AD.",
        "The Great Fire of London happened in 1666.",
        "World War II ended in 1945."
    )

    private val answers = booleanArrayOf(true, false, true, true, true)

    private var index = 0
    private var score = 0
    private var answered = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionText = findViewById<TextView>(R.id.questionText)
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val nextButton = findViewById<Button>(R.id.nextButton)

        // Display the first question
        fun loadQuestion() {
            questionText.text = questions[index]
            feedbackText.text = ""
            nextButton.isEnabled = false
            answered = false
        }

        // Check if the answer is correct and show feedback
        fun checkAnswer(userAnswer: Boolean) {
            if (answered) return  // prevent double answering

            val correct = answers[index]
            if (userAnswer == correct) {
                feedbackText.text = "Correct!"
                score++
            } else {
                feedbackText.text = "Incorrect."
            }
            answered = true
            nextButton.isEnabled = true
        }

        // Set click listeners
        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            index++
            if (index < questions.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, Score::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }

        loadQuestion()
    }
}



