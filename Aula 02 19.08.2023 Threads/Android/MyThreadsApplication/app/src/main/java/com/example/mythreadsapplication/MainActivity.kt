package com.example.mythreadsapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        findViewById<Button>(R.id.asyncButton).setOnClickListener {
            val task = MyAsyncTask()
            task.execute()
        }
        findViewById<Button>(R.id.executorButton).setOnClickListener {
            val executor = Executors.newSingleThreadExecutor()
            val mainThreadHandler = Handler(Looper.getMainLooper())
            textView.text = "Running Executor"
            executor.execute {
                Thread.sleep(3000)
                mainThreadHandler.post {
                    textView.text = "Executor Finnished"
                }
            }
        }
        findViewById<Button>(R.id.handlerButton).setOnClickListener {
            var thread = HandlerThread("Thread")
            thread.start()
            var threadHandler = Handler(thread.looper)
            val mainThreadHandler = Handler(Looper.getMainLooper())

            textView.text = "Running HandlerThread"
            threadHandler.post {
                Thread.sleep(3000)
                mainThreadHandler.post {
                    textView.text = "HandlerThread Finnished"
                    thread.quitSafely()
                }
            }
        }
        findViewById<Button>(R.id.coroutineButton).setOnClickListener {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                textView.text = "Running Coroutine"
                textView.text = fetchDataInBackground()
            }
        }
    }
    private suspend fun fetchDataInBackground():String{
        return withContext(Dispatchers.IO){
            delay(3000)
            "Coroutine Finnished"
        }
    }
    inner class MyAsyncTask : AsyncTask<Void, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            textView.text = "Running AsyncTask"
        }

        override fun doInBackground(vararg p0: Void?): String {
            Thread.sleep(3000)
            return "AsyncTask Finnished"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textView.text = result
        }
    }
}