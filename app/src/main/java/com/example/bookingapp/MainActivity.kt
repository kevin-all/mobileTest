package com.example.bookingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bookingapp.data.Item
import com.example.bookingapp.ui.theme.BookingAppTheme
import com.example.bookingapp.viewmodels.DemoViewModel
import kotlinx.coroutines.flow.Flow
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
                    BookingList(viewModel.items)
                }
            }
        }
    }
}

fun Item.toText(): String = this.originAndDestinationPair.toString()

@Composable
fun BookingList(
    data: Flow<PagingData<Item>>
) {
    val lazyData = data.collectAsLazyPagingItems()
    LazyColumn {

        items(count = lazyData.itemCount) { index ->
            Text(text = lazyData[index]?.toText() ?: "")
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