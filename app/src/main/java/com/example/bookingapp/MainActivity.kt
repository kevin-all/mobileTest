package com.example.bookingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookingapp.ui.theme.BookingAppTheme
import com.example.bookingapp.viewmodels.DemoViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: DemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as BookingApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoList(
                        vm = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DemoList(vm: DemoViewModel, modifier: Modifier = Modifier) {
    val data = vm.bookings.collectAsState()
    Column {
        data.value.forEach { item ->
            Text(
                text = item
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookingAppTheme {
        Greeting("Android")
    }
}