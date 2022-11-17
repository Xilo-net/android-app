package com.xilonet.signa.view

import android.content.Context
import android.provider.MediaStore.Video
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.xilonet.signa.R
import com.xilonet.signa.view.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.xilonet.signa.model.LSMVideo
import com.xilonet.signa.model.VideoFilesManager

@Composable
fun DiccionarioUI(context: Context, goToScreen: (MainActivity.Screens) -> Unit){
    val videoFilesManager = VideoFilesManager(context)

    //TODO: This shouldn't be hardcoded
    var category by remember {mutableStateOf("Abecedario")}
    var searchQuery by remember {mutableStateOf("")}

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FullHeader(goToScreen, videoFilesManager.getCategoryNames(), category,
            { category = it },
            { searchQuery = it})
        if(category != ""){
            VideoGrid(videoFilesManager.getVideosOfCategory(category))
        } else {
            VideoGrid(videoFilesManager.search(searchQuery))
        }
    }
}

@Composable
private fun FullHeader(goToScreen: (MainActivity.Screens) -> Unit,
                       categoryNames: List<String>,
                       currentCategory: String,
                       changeCategory: (String) -> Unit,
                       changeQuery: (String) -> Unit
){
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
        SearchBar(changeCategory, changeQuery)
        Spacer(Modifier.height(12.dp))
        ButtonBelt(categoryNames, currentCategory, changeCategory)
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun ButtonBelt(categoryNames: List<String>, currentCategory: String,
                       changeCategory: (String) -> Unit){
    LazyRow(){
        items(categoryNames) {
            category -> CategoryButton(category, category == currentCategory,
                            changeCategory)
        }
        item {
            ButtonSpacer()
        }
    }
}

//TODO: Que si selecciono una categoría, se quiten las demás
@Composable
private fun CategoryButton(text: String,
                           selected: Boolean = false,
                           changeCategory: (String) -> Unit){
    Row(){
        ButtonSpacer()
        Button(
            onClick = { changeCategory(text) },
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
private fun SearchBar(changeCategory: (String) -> Unit, changeQuery: (String) -> Unit){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = text,
        onValueChange = {
                            text = it
                            val textString = text.text
                            if(textString != ""){
                                changeCategory("")
                                changeQuery(textString)
                            } else {
                                changeQuery("")
                            }
                        },
        colors = TextFieldDefaults.textFieldColors(textColor = SignaDark,
            backgroundColor = SignaLight,
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(0.dp),
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

@Composable
private fun VideoGrid(videosToShow: List<LSMVideo>) {
    // TODO: Make the 30 a variable
    LazyColumn(){
        item {
            Spacer(Modifier.height(30.dp))
        }
        items(videosToShow){
            video1 -> VideoRow(video1, video1)
        }
    }
}

@Composable
private fun VideoRow(video1: LSMVideo, video2: LSMVideo?) {
    Column(Modifier.padding(horizontal = 30.dp)){
        Box(){
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()){
                Spacer(Modifier.width(30.dp))
                VideoButton(video1.name)
            }
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
                if(video2 != null) VideoButton(video2.name)
                Spacer(Modifier.width(30.dp))
            }
        }
        Spacer(Modifier.height(30.dp))
    }

}

@Composable
private fun VideoButton(videoName: String,
                        icon: Painter = painterResource(R.drawable.ic_baseline_play_arrow_24)
) {
    Button(
        onClick = {/* TODO */},
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = SignaLight),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.5.dp, SignaDark)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(icon, null, Modifier.size(80.dp))
            Text(text = videoName, style = MaterialTheme.typography.body1, fontSize = 16.sp)
        }
    }
}