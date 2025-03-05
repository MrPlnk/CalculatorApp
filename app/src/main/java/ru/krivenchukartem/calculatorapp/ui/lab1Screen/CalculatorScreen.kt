package ru.krivenchukartem.calculatorapp.ui.lab1Screen

import android.content.Context
import android.transition.Slide
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.krivenchukartem.calculatorapp.R
import ru.krivenchukartem.calculatorapp.data.CalcUiState

@Composable
fun CalculatorScreen(
    calcUiState: CalcUiState,
    modifier: Modifier = Modifier,
    onChangeBaseSlider: (Float) -> Unit = {value: Float ->},
    onChangeNewSlider: (Float) -> Unit = {value: Float ->},
    onTextFieldValueChanged: (String) -> Unit = {value: String ->},
    onDigitButtonClicked: (String) -> Unit = {value: String ->},
    onBackSpaceButtonClicked: () -> Unit = {},
    onDotButtonClicked: (String) -> Unit = {value: String ->},
    onClearButtonClicked: () -> Unit = {},
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
        Column(){
            Column{
                Text(stringResource(R.string.current_base, calcUiState.currentBase))
                Slider(
                    value = calcUiState.currentBase.toFloat(),
                    onValueChange = onChangeBaseSlider,
                    valueRange = 2f..16f,
                    steps = 14,
                )
            }
            Column{
                Text(stringResource(R.string.new_base, calcUiState.newBase))
                Slider(
                    value = calcUiState.newBase.toFloat(),
                    onValueChange = onChangeNewSlider,
                    valueRange = 2f..16f,
                    steps = 14,
                )
            }
        }

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = calcUiState.currentNumber,
                    fontSize = dimensionResource(R.dimen.fontSize_medium).value.sp
                )
                Text(
                    text = calcUiState.currentBase,
                    fontSize = dimensionResource(R.dimen.fontSize_small).value.sp,
                    modifier = Modifier.align(Alignment.Bottom)
                )
                Text(
                    text = "=",
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

        Column{
            Row(horizontalArrangement = Arrangement.SpaceBetween){
                IconButton(
                    onClick = {},

                ) { Icon(
                    Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.history)
                ) }
                IconButton(
                    onClick = {}
                ){ Icon(
                    Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.backspace)
                ) }
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth(),
                thickness = dimensionResource(R.dimen.thick_devider)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(DIGITS_PER_ROW),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
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
                        text = stringResource(R.string.enter_number),
                        onClick = {},
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