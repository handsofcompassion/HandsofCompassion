package com.example.handsofcompassion.Repository

import com.example.handsofcompassion.Data.Auth.Auth
import com.example.handsofcompassion.Listneers.AuthListneers
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryAuth @Inject constructor(private val auth: Auth){

    fun createUserAttendant(
        name: String,
        email: String,
        password: String,
        listneers: AuthListneers
    ) {
        auth.createUserAttendant(name, email, password, listneers)

    }

    fun loginAttendant(email: String, password: String, listneers: AuthListneers) {

        auth.loginAttendant(email, password, listneers)

    }

    fun forgotPassword(email: String, listneers: AuthListneers) {

        auth.forgotPassword(email, listneers)

    }

    }