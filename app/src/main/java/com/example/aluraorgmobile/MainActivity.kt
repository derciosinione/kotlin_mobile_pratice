package com.example.aluraorgmobile

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.aluraorgmobile.ui.theme.AluraOrgMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluraOrgMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val names = listOf("Dercio", "Sinione", "Dernardo", "Domingos")
    val lazyNames: List<String> = List(40) { "$it" }
    
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(onClick = {
            openTell(context)
        }) {
            Text(text = "Enviar")
        }

        Text(text = "Open web", modifier = Modifier.clickable {
            openWeb(context)
        })

        Text(text = "Open Map", modifier = Modifier.clickable {
            openMap(context)
        })
        
        names.forEach { name -> Text(text = name) }

        LazyRow {
            items(names){
                name -> Text(text = name, modifier = modifier.padding(40.dp))
            }
        }

        LazyColumn {
            items(lazyNames){
                name -> Text(text = name, modifier = modifier.padding(30.dp))
            }
        }

    }
}


fun openTell(context: Context){

    val telIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+351 923 456 644"))

    //    val telIntent = Uri.parse("tel:+351 923 456 644").let {
    //        number -> Intent(Intent.ACTION_DIAL, number)
    //    }

    try {
        context.startActivity(telIntent)
    }
    catch (e: ActivityNotFoundException){
        Toast.makeText(context, e.message,Toast.LENGTH_SHORT).show()
    }
}


fun openWeb(context: Context){
    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ipcb.pt/"))

    try {
        context.startActivity(webIntent)
    }
    catch (e: ActivityNotFoundException){
        Toast.makeText(context, e.message,Toast.LENGTH_SHORT).show()
    }
}

fun openMap(context: Context){
    val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:q=Rua+doutor+Jaimes+Lopes+Dias+6000-214"))

    try {
        context.startActivity(mapIntent)
    }
    catch (e: ActivityNotFoundException){
        Toast.makeText(context, e.message,Toast.LENGTH_SHORT).show()
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AluraOrgMobileTheme {
        Greeting("Android")
    }
}