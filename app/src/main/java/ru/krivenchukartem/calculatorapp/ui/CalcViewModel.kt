package ru.krivenchukartem.calculatorapp.ui

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
import ru.krivenchukartem.calculatorapp.data.HistoryRepresentation

private val TAG = "CalcViewModel"

class CalcViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalcUiState())
    val uiState: StateFlow<CalcUiState> = _uiState.asStateFlow()

    fun updateCurrentSlider(newValue: String){
        _uiState.update { currentState ->
            currentState.copy(currentBase = newValue)
        }
        updateResult()
    }

    fun updateNewSlider(newValue: String){
        _uiState.update { currentState ->
            currentState.copy(newBase = newValue)
        }
        updateResult()
    }

    fun updateExpressionBar(newValue: String){
        /*TODO: Исправить преобразование в большую СС*/
        val codeValue: Int
        val offset: Int
        val newBase: Int
        if (newValue in "A".."F"){
            codeValue = newValue[0].code - "7"[0].code
            offset = "7"[0].code
        }
        else{
            codeValue = newValue[0].code - "0"[0].code
            offset = "0"[0].code
        }
        val currentBase = _uiState.value.currentBase.toInt()

        newBase = codeValue + offset + 1
        if (codeValue >= currentBase){
            updateCurrentSlider(newBase.toChar().toString())
        }

        if (newValue == "."){
            if (!_uiState.value.isFloat){
                _uiState.update { currentState ->
                    currentState.copy(currentNumber = _uiState.value.currentNumber + newValue,
                        isFloat = true)
                }
            }
        }
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
        else {
            _uiState.update { currentState ->
                currentState.copy(currentNumber = _uiState.value.currentNumber + newValue)
            }
        }
        updateResult()
    }

    fun updateResult(){
        /*TODO: сделать вычисление результата*/
    }

    fun clearExpressionBar(){
        _uiState.update { currentState ->
            currentState.copy(currentNumber = DEFAULT_CURRENT_NUMBER,
                isFloat = false)
        }
    }

    fun backSpace(){
        val expression: String = _uiState.value.currentNumber
        if (expression.length == 0){
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