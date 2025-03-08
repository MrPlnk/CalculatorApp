package ru.krivenchukartem.calculatorapp.ui.lab1Screen

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.krivenchukartem.calculatorapp.R

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
){
    val infoList = listOf(
        R.string.about_app_4,
        R.string.about_app_1,
        R.string.about_app_2,
        R.string.about_app_3
    )
    Column(
        modifier = modifier,
        ){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        ) {
            items(infoList){
                CardText(stringResource(it))
            }
        }
    }
}

@Composable
fun CardText(
    text: String,
    modifier: Modifier = Modifier,
){
    Card(
        modifier = modifier,
    ){
        Text(text = text,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .fillMaxWidth())
    }
}

@Preview
@Composable
fun InfoScreenPreview(){
    InfoScreen(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .fillMaxSize()
    )
}