package com.example.handsofcompassion.Data.Auth


import com.example.handsofcompassion.Listneers.AuthListneers
import com.example.handsofcompassion.R
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

import javax.inject.Inject


class Auth @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {



    fun createUserAttendant(
        name: String,
        email: String,
        password: String,
        listneers: AuthListneers
    ) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            listneers.onFailure(R.string.preencha.toString())

        } else {

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {

                    val id = UUID.randomUUID().toString()
                    val userMap = hashMapOf(
                        "id" to id,
                        "name" to name,
                        "email" to email,
                        "password" to password
                    )

             val userReference = firestore.collection("Users").document(id)

             userReference.set(userMap).addOnCompleteListener {

                        listneers.onSucess(R.string.sucessoUser.toString())

                    }.addOnFailureListener {

                        listneers.onFailure(R.string.erroserver.toString())

                    }
                }
            }.addOnFailureListener {


                val errorMensage = when (it) {

                    is FirebaseAuthWeakPasswordException -> R.string.senha6digitos.toString()
                    is FirebaseAuthInvalidCredentialsException -> R.string.emailvalido.toString()
                    is FirebaseAuthUserCollisionException -> R.string.emailemuso.toString()
                    is FirebaseNetworkException -> R.string.semconexao.toString()
                    else -> R.string.errocadastar.toString()

                }
                listneers.onFailure(errorMensage)
            }
        }
    }

    fun loginAttendant(email: String, password: String, listneers: AuthListneers) {

        if (email.isEmpty() || password.isEmpty()) {

            listneers.onFailure(R.string.preencha.toString())
        } else {

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    listneers.onSucess(R.string.loginRealizado.toString())
                }

            }.addOnFailureListener {

                val errorMensage = when (it) {

                    is FirebaseAuthInvalidCredentialsException -> R.string.emailousenha.toString()
                    is FirebaseAuthInvalidUserException -> R.string.emailousenha.toString()
                    is FirebaseNetworkException -> R.string.semconexao.toString()
                    else -> R.string.sucessoUser.toString()

                }

                listneers.onFailure(errorMensage)
            }
        }
    }

    fun forgotPassword(email: String, listneers: AuthListneers) {

        if (email.isEmpty()) {

            listneers.onFailure(R.string.digiteseuemail.toString())

        } else {

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {

                listneers.onSucess(R.string.verificaremailoulixo.toString())

            }.addOnFailureListener {

                listneers.onFailure(R.string.sucessoUser.toString())

            }
        }
    }
}

