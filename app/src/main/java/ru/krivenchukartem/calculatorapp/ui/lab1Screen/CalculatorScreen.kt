package ru.krivenchukartem.calculatorapp.ui.lab1Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.krivenchukartem.calculatorapp.R
import ru.krivenchukartem.calculatorapp.data.CalcUiState

@Composable
fun CalculatorScreen(
    calcUiState: CalcUiState,
    modifier: Modifier = Modifier,
    onChangeCurrentSlider: (Float) -> Unit = { },
    onChangeNewSlider: (Float) -> Unit = { },
    onDigitButtonClicked: (String) -> Unit = { },
    onBackSpaceButtonClicked: () -> Unit = {},
    onClearButtonClicked: () -> Unit = {},
    onEqualButtonClicked: () -> Unit = {},
    onHistoryButtonClicked: () -> Unit = {},
    onInfoButtonClicked: () -> Unit = {}
){
    val context = LocalContext.current
    val DIGITS_PER_ROW = 4
    val digits = listOf(
        R.string.zero,
        R.string.one,
        R.string.two,
        R.string.three,
        R.string.four,
        R.string.five,
        R.string.six,
        R.string.seven,
        R.string.eight,
        R.string.nine,
        R.string.A,
        R.string.B,
        R.string.C,
        R.string.D,
        R.string.E,
        R.string.F
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ){
            Column{
                Text(stringResource(R.string.current_base, calcUiState.currentBase.toIntOrNull() ?: 2))
                Slider(
                    value = calcUiState.currentBase.toFloatOrNull() ?: 2f,
                    onValueChange = onChangeCurrentSlider,
                    valueRange = 2f..16f,
                    steps = 13,
                )
            }
            Column{
                Text(stringResource(R.string.new_base, calcUiState.newBase.toIntOrNull() ?: 2))
                Slider(
                    value = calcUiState.newBase.toFloatOrNull() ?: 2f,
                    onValueChange = onChangeNewSlider,
                    valueRange = 2f..16f,
                    steps = 14,
                )
            }
        }

        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    /*TODO: сделать более элегантную проверку на тип числа (int, float)*/
                    text = calcUiState.currentNumber,
                    fontSize = dimensionResource(R.dimen.fontSize_medium).value.sp
                )
                Text(
                    text = calcUiState.currentBase,
                    fontSize = dimensionResource(R.dimen.fontSize_small).value.sp,
                    modifier = Modifier.align(Alignment.Bottom)
                )
                Text(
                    text = stringResource(R.string.equal),
                    fontSize = dimensionResource(R.dimen.fontSize_medium).value.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = calcUiState.resultNumber,
                    fontSize = dimensionResource(R.dimen.fontSize_medium).value.sp
                )
                Text(
                    text = calcUiState.newBase,
                    fontSize = dimensionResource(R.dimen.fontSize_small).value.sp,
                    modifier = Modifier.align(Alignment.Bottom)
                )
            }
        }

        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {
                    IconButton(
                        onClick = onHistoryButtonClicked,

                        ) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = stringResource(R.string.history)
                        )
                    }
                    IconButton(
                        onClick = onInfoButtonClicked
                    ) {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = stringResource(R.string.backspace)
                        )
                    }
                }
                IconButton(
                    onClick = onBackSpaceButtonClicked,
                ){ Icon(
                    Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.backspace)
                ) }
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth(),
                thickness = dimensionResource(R.dimen.thick_devider)
            )

            LazyHorizontalGrid(
                rows = GridCells.Fixed(DIGITS_PER_ROW),
                modifier = Modifier.size(1000.dp, 300.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Равные отступы между элементами
                verticalArrangement = Arrangement.Center // Равные отступы между строками
            ) {
                items(digits) { item ->
                    FormatedButton(
                        text = stringResource(item),
                        onClick = { onDigitButtonClicked(context.getString(item)) },
                        contentPadding = PaddingValues(8.dp), // Добавляем внутренние отступы
                        shape = CircleShape,
                        modifier = Modifier
                            .size(56.dp) // Увеличиваем размер кнопки
                            .aspectRatio(1f) // Держим пропорции круга
                    )
                }
                item {
                    FormatedButton(
                        text = stringResource(R.string.equal),
                        onClick = onEqualButtonClicked,
                        contentPadding = PaddingValues(8.dp),
                        shape = CircleShape,
                        modifier = Modifier
                            .size(56.dp)
                            .aspectRatio(1f)
                    )
                }
                item {
                    FormatedButton(
                        text = stringResource(R.string.dot),
                        onClick = {onDigitButtonClicked(context.getString(R.string.dot))},
                        contentPadding = PaddingValues(8.dp),
                        shape = CircleShape,
                        modifier = Modifier
                            .size(56.dp)
                            .aspectRatio(1f)
                    )
                }
                item {
                    FormatedButton(
                        text = stringResource(R.string.clear),
                        onClick = onClearButtonClicked,
                        contentPadding = PaddingValues(8.dp),
                        shape = CircleShape,
                        modifier = Modifier
                            .size(56.dp)
                            .aspectRatio(1f)
                    )
                }

            }
        }
    }
}

@Composable
fun FormatedButton(
    text: String,
    onClick: () -> Unit,
    contentPadding: PaddingValues,
    shape: Shape,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = contentPadding,
        shape = shape, // Используем переданную форму
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue, // Цвет кнопки
            contentColor = Color.White // Цвет текста
        ),
        elevation = ButtonDefaults.buttonElevation(8.dp) // Добавляем тень
    ) {
        Text(text, fontSize = 18.sp)
    }
}

@Preview
@Composable
fun CalculatorPreview(){
    CalculatorScreen(
        calcUiState = CalcUiState("123", "2", "3", "6")
    )
}