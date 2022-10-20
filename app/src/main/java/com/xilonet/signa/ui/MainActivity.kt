package com.xilonet.signa.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.xilonet.signa.ui.theme.SignaGreen
import com.xilonet.signa.ui.theme.SignaTheme

// TODO: MVM: Organize this thing well, MODEL/UI -> AS I SAW WITH JEFF.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SignaGreen
                ) {
                    LoginUI()
                }
            }
        }
    }

}