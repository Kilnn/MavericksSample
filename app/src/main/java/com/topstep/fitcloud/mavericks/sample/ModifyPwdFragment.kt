package com.topstep.fitcloud.mavericks.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import com.topstep.fitcloud.mavericks.sample.databinding.FragmentModifyPwdBinding
import com.topstep.fitcloud.mavericks.sample.di.mavericks.AssistedViewModelFactory
import com.topstep.fitcloud.mavericks.sample.di.mavericks.hiltMavericksViewModelFactory
import com.topstep.fitcloud.mavericks.sample.viewbinding.viewBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ModifyPwdFragment : Fragment(R.layout.fragment_modify_pwd), MavericksView {

    private val viewBind: FragmentModifyPwdBinding by viewBinding()
    private val viewModel: ModifyPwdViewModel by fragmentViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBind.btnCommit.setOnClickListener {
            viewModel.modifyPwd()
        }
    }

    override fun invalidate() {
        withState(viewModel) {
            if (it.complete) {
                viewBind.tvState.text = "State Completed"
            } else if (it.async is Loading) {
                viewBind.tvState.text = "State Executing"
            }
        }
    }

}

data class SingleAsyncState(
    val async: Async<Unit> = Uninitialized,
    @PersistState val complete: Boolean = false
) : MavericksState

class ModifyPwdViewModel @AssistedInject constructor(
    @Assisted initState: SingleAsyncState
) : MavericksViewModel<SingleAsyncState>(initState) {

    init {
        viewModelScope.launch {
            //collect state to let me know that modifyPwd is done
            stateFlow.collect {
                Log.e("Kilnn", "collect state:" + it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        //print a log let me know that ViewModel destroyed
        Log.e("Kilnn", "ModifyPwdViewModel onCleared")
    }

    fun modifyPwd() {
        suspend {
            delay(5000)
        }.execute {
            copy(async = it, complete = it is Success)
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ModifyPwdViewModel, SingleAsyncState> {
        override fun create(state: SingleAsyncState): ModifyPwdViewModel
    }

    companion object : MavericksViewModelFactory<ModifyPwdViewModel, SingleAsyncState> by hiltMavericksViewModelFactory()
}
