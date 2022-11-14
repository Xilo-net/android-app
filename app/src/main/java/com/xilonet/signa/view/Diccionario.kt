package com.xilonet.signa.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xilonet.signa.R
import com.xilonet.signa.view.theme.*
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource


@Composable
fun DiccionarioUI(goToScreen: (MainActivity.Screens) -> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FullHeader(goToScreen)
    }
}

@Composable
private fun FullHeader(goToScreen: (MainActivity.Screens) -> Unit){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(SignaGreen)
    ) {
        Box() {
            HeaderTitle(stringResource(R.string.diccionario))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Spacer(Modifier.width(10.dp))
                BackButton({goToScreen(MainActivity.Screens.INICIO)})
            }
        }
        Spacer(Modifier.height(4.dp))
        SearchBar()
        Spacer(Modifier.height(12.dp))
        ButtonBelt()
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun ButtonBelt(){
    LazyRow(){
        // TODO: Items hardcodeados solo de momento
        item {
            CategoryButton("Animales")
        }
        item {
            CategoryButton("Colores")
        }
        item {
            CategoryButton("Comida")
        }
        item {
            CategoryButton("Hogar")
        }
        item {
            CategoryButton("Lugares")
        }
        item {
            CategoryButton("Meses")
        }
        item {
            CategoryButton("Pronombres")
        }
        item {
            CategoryButton("Hogar")
        }
        item {
            ButtonSpacer()
        }
    }
}

//TODO: Que si selecciono una categoría, se quiten las demás
@Composable
private fun CategoryButton(text: String){
    var selected by remember {mutableStateOf(false)}
    Row(){
        ButtonSpacer()
        Button(
            onClick = {selected = !selected},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if(selected) SignaDark else SignaLight
            ),
            modifier = Modifier
                .width(108.dp)
                .height(22.dp),
            shape = RoundedCornerShape(100),
            contentPadding = PaddingValues(0.dp)
        ){
            Text(text = text, style = MaterialTheme.typography.button,
                color = if(selected) SignaLight else SignaDark)
        }
    }
}

@Composable
private fun ButtonSpacer(){
    Spacer(Modifier.width(8.dp))
}

@Composable
private fun SearchBar(){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = text,
        onValueChange = {text = it},
        colors = TextFieldDefaults.textFieldColors(textColor = SignaDark,
            backgroundColor = SignaLight,
        ),
        modifier = Modifier.fillMaxWidth(0.95f).padding(0.dp),
        shape = RoundedCornerShape(100),
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        leadingIcon = {
            Image(
                painterResource(R.drawable.ic_baseline_search_24),
                null,
                modifier = Modifier.height(24.dp),
                alpha = 0.5f
            )
        }
    )
}