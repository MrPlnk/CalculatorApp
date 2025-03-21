package ru.krivenchukartem.calculatorapp.ui

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.krivenchukartem.calculatorapp.R
import ru.krivenchukartem.calculatorapp.data.CalcUiState
import ru.krivenchukartem.calculatorapp.data.DEFAULT_CURRENT_NUMBER
import ru.krivenchukartem.calculatorapp.data.DataSource
import ru.krivenchukartem.calculatorapp.data.HistoryRepresentation
import ru.krivenchukartem.calculatorapp.data.NUMBER_OF_DECIMAL_PLACES
import ru.krivenchukartem.calculatorapp.data.NUMBER_OF_DIGITS_IN_EXPRESSION
import ru.krivenchukartem.calculatorapp.logic.Convert_10_p1
import ru.krivenchukartem.calculatorapp.logic.Convert_p1_10

private val TAG = "CalcViewModel"

class CalcViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalcUiState())
    val uiState: StateFlow<CalcUiState> = _uiState.asStateFlow()

    private fun calcMinNumberSystem(): String{
        var maxValue = 0
        val sequence = _uiState.value.currentNumber
        sequence.forEach { symbol ->
            val digit = DataSource.stringToDigit[symbol.toString()] ?: 0
            if (digit > maxValue){
                maxValue = digit
            }
        }
        return (maxValue + 1).toString()
    }

    fun updateCurrentSlider(newValue: String){

        val minNumberSystem = calcMinNumberSystem().toIntOrNull() ?: 0
        val currentNumberSystem = newValue.toIntOrNull() ?: 0

        if (minNumberSystem <= currentNumberSystem){
            _uiState.update { currentState ->
                currentState.copy(currentBase = newValue)
            }
        }
        else{
            _uiState.update { currentState ->
                currentState.copy(currentBase = minNumberSystem.toString())
            }
        }

        updateResult()
    }

    fun updateNewSlider(newValue: String){
        _uiState.update { currentState ->
            currentState.copy(newBase = newValue)
        }
        updateResult()
        Log.d(TAG, "UpdateNewSlider:\t $newValue")
    }

    fun updateExpressionBar(newValue: String){
        if (_uiState.value.currentNumber.length >= NUMBER_OF_DIGITS_IN_EXPRESSION){
            return
        }
         // Проверка: есть ли уже в числе точка
        if (newValue == "."){
            if (!_uiState.value.isFloat){
                _uiState.update { currentState ->
                    currentState.copy(currentNumber = _uiState.value.currentNumber + newValue,
                        isFloat = true)
                }
            }
        }
        // Проверка: Один ли ноль в начале
        else if (_uiState.value.currentNumber == "0") {
            if (newValue == "0"){
                return
            }
            else{
                _uiState.update { currentState ->
                    currentState.copy(currentNumber = newValue)
                }
            }
        }
        // Все остальное
        else {
            _uiState.update { currentState ->
                currentState.copy(currentNumber = _uiState.value.currentNumber + newValue)
            }
        }

        if (newValue in "0".."9" || newValue in "A".."F"){
            val minNumberSystem = calcMinNumberSystem().toIntOrNull() ?: 0
            val currentNumberSystem = _uiState.value.currentBase.toIntOrNull() ?: 0

            if (currentNumberSystem < minNumberSystem){
                _uiState.update{ currentState ->
                    currentState.copy(currentBase = minNumberSystem.toString())
                }
            }
        }

        updateResult()
    }

    private fun updateResult(){
        var intermediateResult: Double
        var result: String
        if (_uiState.value.currentBase.toInt() != 10){
            intermediateResult = Convert_p1_10.solve(_uiState.value.currentNumber, _uiState.value.currentBase.toInt())
            result = Convert_10_p1.solve(intermediateResult, _uiState.value.newBase.toInt(), NUMBER_OF_DECIMAL_PLACES)
        }
        else{
            result = Convert_10_p1.solve(_uiState.value.currentNumber.toDouble(), _uiState.value.newBase.toInt(), NUMBER_OF_DECIMAL_PLACES)
            Log.d(TAG, "updateResult\t $result")
        }
        _uiState.update{ currentState ->
            currentState.copy(resultNumber = result)
        }
    }

    fun clearExpressionBar(){
        _uiState.update { currentState ->
            currentState.copy(currentNumber = DEFAULT_CURRENT_NUMBER,
                resultNumber = DEFAULT_CURRENT_NUMBER,
                isFloat = false)
        }
    }

    fun backSpace(){
//        Log.d(TAG, "Enter to BackSpace")
        val expression: String = _uiState.value.currentNumber
        if (expression == DEFAULT_CURRENT_NUMBER){
//            Log.d(TAG, expression)
            return
        }

        if (expression.length == 1){
            _uiState.update{ currentState ->
                currentState.copy(currentNumber = DEFAULT_CURRENT_NUMBER,
                    resultNumber = DEFAULT_CURRENT_NUMBER)
            }
//            Log.d(TAG, _uiState.value.toString())
            return
        }

        val lastSymbol: String = expression.takeLast(1)
        /*TODO: Сделать проверку на точку в стиле R.string.dot*/
        if (lastSymbol == "."){
            _uiState.update { currentState ->
                currentState.copy(currentNumber = expression.dropLast(1),
                    isFloat = false)
            }
        }
        else{
            _uiState.update { currentState ->
                currentState.copy(currentNumber = expression.dropLast(1))
            }
        }
        updateResult()
        Log.d(TAG, "leaved From Backspace\n")
    }

    fun updateHistory(){
        val sample = HistoryRepresentation(
            currentNumber = _uiState.value.currentNumber,
            resultNumber = _uiState.value.resultNumber,
            currentBase = _uiState.value.currentBase,
            newBase = _uiState.value.newBase
        )
        if (_uiState.value.history.size > 0){
            val previosElem = _uiState.value.history.last()

            if (previosElem == sample){
                return
            }
        }

        _uiState.update { currentState ->
            currentState.copy(history = _uiState.value.history.plus(sample))
        }

        Log.d(TAG, _uiState.value.history.toString())
    }

    fun clearHistory(){
        _uiState.update{ currentState ->
            currentState.copy(history = listOf())
        }
    }
}