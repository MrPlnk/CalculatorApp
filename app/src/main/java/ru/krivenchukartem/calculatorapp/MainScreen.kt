package ru.krivenchukartem.calculatorapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.krivenchukartem.calculatorapp.ui.CalcViewModel
import ru.krivenchukartem.calculatorapp.ui.lab1Screen.CalculatorScreen
import ru.krivenchukartem.calculatorapp.ui.lab1Screen.HistoryScreen
import ru.krivenchukartem.calculatorapp.ui.lab1Screen.InfoScreen

enum class MainScreen {
    Start,
    History,
    Info,
}

@Composable
fun CalculatorApp(
    viewModel: CalcViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold(
        topBar = { CalculatorAppBar() },
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MainScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = MainScreen.Start.name){
                CalculatorScreen(
                    calcUiState = uiState,
                    onChangeCurrentSlider = {
                        newValue: Float ->
                        val value = newValue.toInt().toString()
                        viewModel.updateCurrentSlider(value)
                    },
                    onChangeNewSlider = {
                        newValue: Float ->
                        val value = newValue.toInt().toString()
                        viewModel.updateNewSlider(value)
                    },
                    onDigitButtonClicked = {
                        newValue ->
                        viewModel.updateExpressionBar(newValue)
                   },
                    onClearButtonClicked = {viewModel.clearExpressionBar()},
                    onBackSpaceButtonClicked = {viewModel.backSpace()},
                    onEqualButtonClicked = {viewModel.updateHistory()},
                    onInfoButtonClicked = {
                        navController.navigate(MainScreen.Info.name)
                    },
                    onHistoryButtonClicked = {
                        navController.navigate(MainScreen.History.name)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = MainScreen.History.name){
                HistoryScreen(
                    historyItems = uiState.history,
                    onHistoryClearButtonClicked = {viewModel.clearHistory()},
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .fillMaxSize()
                )
            }
            composable(route = MainScreen.Info.name){
                InfoScreen(
                    modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .fillMaxSize())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorAppBar(){
    TopAppBar(
        title = {
            Text(stringResource(R.string.topAppBar_calculator))
        }
    )
}
