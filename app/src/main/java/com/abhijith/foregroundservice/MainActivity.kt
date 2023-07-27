package com.abhijith.foregroundservice

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.abhijith.foregroundservice.ui.theme.ForegroundServiceTheme
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        setContent {
            ForegroundServiceTheme {
                Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                    Button(onClick = {
                        Intent(applicationContext,RunningService::class.java).also {
                            it.action = RunningService.Actions.START.toString()
                            startService(it)
                        }
                    }) {
                         Text(text = "Start run")
                    }


                    Button(onClick = {
                        Intent(applicationContext,RunningService::class.java).also {
                            it.action = RunningService.Actions.STOP.toString()
                            startService(it)
                        }
                    }) {
                        Text(text = "Stop run")
                    }

                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ForegroundServiceTheme {
        Greeting("Android")
    }
}