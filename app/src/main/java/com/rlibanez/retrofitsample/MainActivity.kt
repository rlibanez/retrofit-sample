package com.rlibanez.retrofitsample

import android.os.Bundle
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
import com.rlibanez.retrofitsample.data.RetrofitServiceFactory
import com.rlibanez.retrofitsample.data.model.Section
import com.rlibanez.retrofitsample.ui.theme.RetrofitSampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = RetrofitServiceFactory.makeRetrofitService()
        var section0: Section?
        var texto = ""

        lifecycleScope.launch {
            try {
                val sections = service.getSections()
                section0 = sections[0]
                texto = section0.toString()
                println(section0)
            } catch (e: Exception) {
                println("Error con section0")
                section0 = null
                texto = "Fallo castastrófico"
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