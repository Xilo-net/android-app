package com.xilonet.signa.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xilonet.signa.R
import com.xilonet.signa.view.MainActivity

@Composable
fun HeaderTitle(text: String){
    Column() {
        Spacer(Modifier.height(8.dp))
        Text(
            text = text, style = MaterialTheme.typography.body1, fontSize = 28.sp,
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
        )
    }
}

@Composable
//TODO: This button must do something lol
fun BackButton(goBack: () -> Unit, downDp: Dp = 16.dp){
    Column() {
        Spacer(Modifier.height(downDp))
        Image(
            painterResource(R.drawable.ic_baseline_arrow_back_24),
            contentDescription = null,
            modifier = Modifier.size(24.dp).clickable { goBack() }
        )
    }
}