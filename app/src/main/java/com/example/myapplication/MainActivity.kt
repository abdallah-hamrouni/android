package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// === Définition des destinations ===
class DestinationHome
class DestinationInscription
class DestinationPasInteresse
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val backStack = remember { mutableStateListOf<Any>(DestinationHome()) }

                when (backStack.lastOrNull()) {
                    is DestinationHome -> HomeScreen(
                        onNavigateToInscription = { backStack.add(DestinationInscription()) },
                        onNavigateToPasInteresse={backStack.add(DestinationPasInteresse())}
                    )
                    is DestinationInscription -> InscriptionScreen(
                        onBack = { backStack.removeLastOrNull() }
                    )
                    is DestinationPasInteresse->PasInteresseScreen(
                        onBack={backStack.removeLastOrNull()}
                    )
                }
            }
        }
    }
}

// === Ecran d'accueil (ancien code de MainActivity) ===
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToInscription: () -> Unit,
               onNavigateToPasInteresse: ()-> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text(text = "Evènements") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                }
            }
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFAFAFA)),
            contentAlignment = Alignment.Center
        ) {
            val image: Painter = painterResource(id = R.drawable.img)
            Image(
                painter = image,
                contentDescription = "Sample Image",
                modifier = Modifier
                    .size(500.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("ou ")
                    }
                    append(": école d'ingénieur ISIS")
                },
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 290.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Quand ")
                    }
                    append(": 24 octobre")
                },
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 390.dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Button(onClick = { onNavigateToInscription() }) {
                    Text("Inscription")
                }
                Button(
                    onClick = { onNavigateToPasInteresse()},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Pas intéressé")
                }
            }
        }
    }
}
