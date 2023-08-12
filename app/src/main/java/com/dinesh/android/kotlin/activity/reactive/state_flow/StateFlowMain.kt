package com.dinesh.android.kotlin.activity.reactive.state_flow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.dinesh.android.compose.scaffold_layout.MyScaffoldLayout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private val TAG = "log_" + StateFlowMain::class.java.name.split(StateFlowMain::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

class StateFlowMain : AppCompatActivity() {
    private lateinit var stateFlowViewModel: StateFlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateFlowViewModel = ViewModelProvider(this)[StateFlowViewModel::class.java]
        setContent {
            /*
            stateFlowViewModel.stateFlow.collectAsStateWithLifecycle()
            stateFlowViewModel.liveData.observeAsState()
             */

            MyScaffoldLayout()
        }

        stateFlowViewModel.liveData.observe(this) { value ->

        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlowViewModel.stateFlow.collect { value ->

                }
            }
        }

    }

}


class StateFlowViewModel : ViewModel() {
    private val _liveData: MutableLiveData<String> = MutableLiveData()
    val liveData: LiveData<String> = _liveData

    fun updateLiveData(newValue: String) {
        _liveData.value = newValue
    }


    private val _stateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val stateFlow: StateFlow<String> = _stateFlow.asStateFlow()

    fun updateStateFlow(newValue: String) {
        viewModelScope.launch {
            _stateFlow.emit(newValue)
        }
    }
}