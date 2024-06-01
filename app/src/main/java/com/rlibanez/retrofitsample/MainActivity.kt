package com.rlibanez.retrofitsample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.rlibanez.retrofitsample.data.RetrofitClient
import com.rlibanez.retrofitsample.data.model.Section
import com.rlibanez.retrofitsample.ui.theme.RetrofitSampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = RetrofitClient.makeRetrofitService()
        var section0: Section?
        var texto = ""

        lifecycleScope.launch {
            try {
                val sections = service.getSections()
                section0 = sections[0]
                texto = section0.toString()
                Log.d("MainActivity", texto)
            } catch (e: Exception) {
                section0 = null
                texto = "Fallo castastrófico"
                Log.d("MainActivity", "Fallo catastrófico")
            }

            enableEdgeToEdge()
            setContent {
                RetrofitSampleTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            texto,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, texto: String, modifier: Modifier = Modifier) {
    Text(
        text = texto,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitSampleTheme {
        Greeting("Android", "Pollita")
    }
}