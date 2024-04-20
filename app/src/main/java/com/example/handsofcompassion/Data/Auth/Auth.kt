package com.example.handsofcompassion.Data.Auth

import com.example.handsofcompassion.Adapter.EmpplyeesAdapter
import com.example.handsofcompassion.Data.Employees
import com.example.handsofcompassion.Listneers.AuthListneers
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

            listneers.onFailure("Erro ao Cadastrar Funcionário")

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

                        listneers.onSucess("Sucesso ao Cadastrar Usuário.")

                    }.addOnFailureListener {

                        listneers.onFailure("Server Error.")

                    }
                }
            }.addOnFailureListener {


                val errorMensage = when (it) {

                    is FirebaseAuthWeakPasswordException -> "Digite uma Senha com no Mínimo 6 Caracteres."
                    is FirebaseAuthInvalidCredentialsException -> "Digite um E-mail válido."
                    is FirebaseAuthUserCollisionException -> "Esse E-mail já está em Uso."
                    is FirebaseNetworkException -> "Sem Conecxão a Internet."
                    else -> "Erro ao Cadastar Usuário."

                }
                listneers.onFailure(errorMensage)
            }
        }
    }

    fun loginAttendant(email: String, password: String, listneers: AuthListneers) {

        if (email.isEmpty() || password.isEmpty()) {

            listneers.onFailure("Preencha Todos os Campos")
        } else {

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    listneers.onSucess("Login Realizado com Sucesso.")
                }

            }.addOnFailureListener {

                val errorMensage = when (it) {

                    is FirebaseAuthInvalidCredentialsException -> "E-mail ou Senha Inválidos."
                    is FirebaseAuthInvalidUserException -> "E-mail ou Senha Inválidos."
                    is FirebaseNetworkException -> "Sem conexão a internet."
                    else -> "Server Error"

                }

                listneers.onFailure(errorMensage)
            }
        }
    }

    fun forgotPassword(email: String, listneers: AuthListneers) {

        if (email.isEmpty()) {

            listneers.onFailure("Digite seu E-mail.")

        } else {

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {

                listneers.onSucess("Verifique seu E-mail, Caixa de Entrada e Lixo Eletrônico")

            }.addOnFailureListener {

                listneers.onFailure("Server Error.")

            }
        }
    }
}

