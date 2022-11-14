package com.xilonet.signa.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.xilonet.signa.view.theme.SignaBackground
import com.xilonet.signa.view.theme.SignaGreen
import com.xilonet.signa.view.theme.SignaTheme

// TODO: MVM: Organize this thing well, MODEL/UI -> AS I SAW WITH JEFF.
// TODO: Put the controller elsewhere (the screens stuff), remember what I did at Twitter
// TODO: Implement Room (for the database), but see how to implement Security
class MainActivity : ComponentActivity() {

    enum class Screens {
        LOGIN, INICIO, DICCIONARIO
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf(Screens.LOGIN) }
            SignaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = if(currentScreen == Screens.LOGIN) SignaGreen else SignaBackground,
                ) {
                    when(currentScreen){
                        Screens.LOGIN -> LoginUI() {currentScreen = it}
                        Screens.INICIO -> InicioUI() {currentScreen = it}
                        Screens.DICCIONARIO -> DiccionarioUI() {currentScreen = it}
                    }
                }
            }
        }
    }

}