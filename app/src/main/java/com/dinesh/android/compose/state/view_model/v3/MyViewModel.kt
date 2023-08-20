package com.dinesh.android.compose.state.view_model.v3

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.*
import androidx.compose.ui.unit.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.*
import androidx.compose.material.*
import androidx.compose.ui.text.input.KeyboardType

private val TAG = "log_" + MyViewModel::class.java.name.split(MyViewModel::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

class MyViewModel : ViewModel() {
    private val _textFieldValue: MutableStateFlow<String> = MutableStateFlow("")
    val textFieldValue: StateFlow<String> = _textFieldValue.asStateFlow()

    fun updateTextFieldValue(newValue: String) {
        viewModelScope.launch { _textFieldValue.emit(newValue) }
    }

    fun onSubmitBtnClicked() {
        Log.e(TAG, "onSubmitBtnClicked: ${textFieldValue.value}")
    }
}

@Preview(showBackground = true)
@Composable
fun MyLayoutView() {
    MyUI()
}

@Composable
fun MyUI(viewModel: MyViewModel = viewModel<MyViewModel>()) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        MyTextField(value = viewModel.textFieldValue.collectAsState().value, onValueChanged = { viewModel.updateTextFieldValue(it) })
        MyButtonView(onClick = { viewModel.onSubmitBtnClicked() }) { Text(text = "Submit") }
    }
}

@Composable
private fun MyTextField(modifier: Modifier = Modifier, value: String, onValueChanged: (String) -> Unit, label: String = "Name", hintPlaceHolder: String = "Enter the name") {
    OutlinedTextField(value = value, onValueChange = onValueChanged, label = { Text(label) },
        placeholder = { Text(hintPlaceHolder) }, modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .then(modifier), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
}

@Composable
fun MyButtonView(modifier: Modifier = Modifier, onClick: () -> Unit, content: @Composable RowScope.() -> Unit = { Text(text = "Button") }) {
    Button(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .then(modifier), onClick = onClick, content = content)
}