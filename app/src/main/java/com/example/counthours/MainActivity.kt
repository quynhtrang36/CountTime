package com.example.counthours

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var timeTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private var seconds = 0
    private var running = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view
        timeTextView = findViewById(R.id.timeTextView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)

        startButton.setOnClickListener {
            running = true
            startButton.visibility = View.GONE
            stopButton.visibility = View.VISIBLE
            startTimer()
        }

        stopButton.setOnClickListener {
            running = false
            startButton.visibility = View.VISIBLE
            stopButton.visibility = View.GONE
        }
    }

    private fun startTimer() {
        Thread {
            while (running) {
                Thread.sleep(1000)
                seconds++
                handler.post {
                    timeTextView.text = "Thời gian: $seconds giây"
                }
            }
        }.start()
    }
}