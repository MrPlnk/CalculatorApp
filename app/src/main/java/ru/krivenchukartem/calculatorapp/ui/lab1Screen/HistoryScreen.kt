package ru.krivenchukartem.calculatorapp.ui.lab1Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.krivenchukartem.calculatorapp.R
import ru.krivenchukartem.calculatorapp.data.HistoryRepresentation

@Composable
fun HistoryScreen(
    historyItems: List<HistoryRepresentation>,
    onHistoryClearButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(modifier = modifier){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onHistoryClearButtonClicked,
            ) {
                Text(text = stringResource(R.string.clear_history))
            }
            HorizontalDivider(thickness = dimensionResource(R.dimen.thick_devider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium)))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(historyItems){
                    AnotherExpression(it)
                }
            }
            HorizontalDivider(thickness = dimensionResource(R.dimen.thick_devider),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium)))
        }
    }
}

@Composable
fun AnotherExpression(
    value: HistoryRepresentation,
    modifier: Modifier = Modifier,
){
    val small_padding = dimensionResource(R.dimen.padding_small)
    Row(
        modifier = modifier,
    ) {
        Text(text = value.currentNumber)
        Text(text = value.currentBase,
            fontSize = dimensionResource(R.dimen.fontSize_small).value.sp,
            modifier = Modifier.align(Alignment.Bottom))
        Text(text = stringResource(R.string.equal),
            modifier = Modifier.padding(horizontal = small_padding))
        Text(text = value.resultNumber)
        Text(text = value.newBase,
            fontSize = dimensionResource(R.dimen.fontSize_small).value.sp,
            modifier = Modifier.align(Alignment.Bottom))
    }
}

@Preview
@Composable
fun HistoryScreenPreview(){
    HistoryScreen(
        listOf(HistoryRepresentation("123", "4", "2345", "6")),
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .fillMaxSize())
}