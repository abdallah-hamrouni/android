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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        title = { Text(text = "Evènements") },
                        navigationIcon = {
                            IconButton(onClick = { /* Action du menu */ }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* Action du cœur */ }) {
                                Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                            }
                        }
                    )
                }) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(Color(0xFAFAFA)), // Ajout de la couleur de fond gris
                        contentAlignment = Alignment.Center // Centrage de l'image
                    ) {
                        // Affichage de l'image au centre avec taille réduite
                        val image: Painter = painterResource(id = R.drawable.img)
                        Image(
                            painter = image,
                            contentDescription = "Sample Image",
                            modifier = Modifier
                                .size(500.dp)
                                .align(Alignment.TopCenter)
                                .padding(top = 100.dp) // Déplace l'image vers le bas
                        )

                        // Premier texte: "ou: école d'ingénieur ISIS"
                        Text(
                            text = buildAnnotatedString {
                                // Mettre "ou" en gras
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("ou ")
                                }
                                // Ajouter le texte normal suivant
                                append(": école d'ingénieur ISIS")
                            },
                            style = TextStyle(fontSize = 20.sp),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 290.dp)
                        )

                        Text(
                            text = buildAnnotatedString {
                                // Mettre "Quand" en gras
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Quand ")
                                }
                                // Ajouter le texte normal suivant
                                append(": 24 octobre")
                            },
                            style = TextStyle(fontSize = 20.sp),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 390.dp)
                        )

                        // Ajout de deux boutons horizontaux en bas
                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomCenter) // Aligne la Row en bas de la Box
                                .padding(16.dp), // Espacement autour de la Row
                            horizontalArrangement = Arrangement.spacedBy(25.dp) // Espacement entre les boutons
                        ) {
                            // Premier bouton
                            Button(onClick = { /* Action du bouton 1 */ }) {
                                Text("Inscription")
                            }

                            // Deuxième bouton
                            Button(
                                onClick = { /* Action du bouton 2 */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White, // Fond blanc
                                    contentColor = MaterialTheme.colorScheme.primary // Texte couleur primaire du thème (comme le premier bouton)
                                )
                            ) {
                                Text("Pas intéressé")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Vous pouvez ajouter du texte ou autre ici si vous souhaitez
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}
