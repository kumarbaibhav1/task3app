package com.example.task3app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.task3app.viewmodel.QuoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteScreen()
        }
    }
}

@Composable
fun QuoteScreen(viewModel: QuoteViewModel = viewModel()) {
    val quote by viewModel.quote.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchQuote()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = quote?.q ?: "Loading...",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = quote?.a ?: "",
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { viewModel.fetchQuote() }) {
            Text("Get New Quote")
        }
    }
}
