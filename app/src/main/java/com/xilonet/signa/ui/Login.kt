package com.xilonet.signa.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xilonet.signa.R
import com.xilonet.signa.ui.theme.SignaDark
import com.xilonet.signa.ui.theme.SignaLight

@Composable
fun LoginUI(){
    // From top to bottom:
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.fillMaxHeight(0.15f))
        AppNameBanner()
    }

    // From bottom to top:
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginScreenButton(
            text = stringResource(R.string.login),
            onClick = { /*TODO*/ },
        )
        Spacer(Modifier.height(20.dp))
        LoginScreenButton(
            text = stringResource(R.string.register),
            onClick = { /*TODO*/ },
        )
        Spacer(Modifier.height(20.dp))
        LoginScreenButton(
            text = stringResource(R.string.continue_as_guest),
            onClick = { /*TODO*/ },
            transparent = true,
            fontSize = 16.sp
        )
        Spacer(Modifier.fillMaxHeight(0.3f))
    }
}

@Composable
fun AppNameBanner(){
    Image(
        painter = painterResource(R.drawable.login_screen_logo),
        contentDescription = stringResource(R.string.login_screen_logo),
        colorFilter = ColorFilter.tint(color = SignaDark),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth(0.7f)
    )
}

@Composable
fun LoginScreenButton(
    text: String,
    onClick: () -> Unit,
    transparent: Boolean = false,
    fontSize: TextUnit = 20.sp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(transparent) Color.Transparent else SignaLight,
        ),
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(50.dp),
        shape = RoundedCornerShape(50.dp),
        border = if(transparent) BorderStroke(2.dp, SignaDark) else null
    ) {
        Text(text = text, style = MaterialTheme.typography.body1, fontSize = fontSize)
    }
}