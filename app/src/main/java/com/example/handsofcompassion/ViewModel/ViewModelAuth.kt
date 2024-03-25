package com.example.handsofcompassion.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.Repository.RepositoryAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelAuth @Inject constructor(private val authRepositoryAuth: RepositoryAuth) : ViewModel() {

    fun createUserAttendant(
        name: String,
        email: String,
        password: String,
        listneers: AuthListneers
    ) {
        viewModelScope.launch {

            authRepositoryAuth.createUserAttendant(name, email, password, listneers)

        }
    }

    fun loginAttendant(email: String, password: String, listneers: AuthListneers) {

        viewModelScope.launch {

            authRepositoryAuth.loginAttendant(email, password, listneers)

        }
    }

    fun forgotPassword(email: String, listneers: AuthListneers) {

       viewModelScope.launch{

           authRepositoryAuth.forgotPassword(email, listneers)

       }
    }
}