package com.example.handsofcompassion.Data.Auth


import android.content.Context
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
    private val firestore: FirebaseFirestore,
    private val context: Context
) {



    fun createUserAttendant(
        name: String,
        email: String,
        password: String,
        listneers: AuthListneers
    ) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            listneers.onFailure(context.getString(R.string.preencha))

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

                        listneers.onSucess(context.getString(R.string.sucessoUser))

                    }.addOnFailureListener {

                        listneers.onFailure(context.getString(R.string.erroserver))

                    }
                }
            }.addOnFailureListener {


                val errorMensage = when (it) {

                    is FirebaseAuthWeakPasswordException -> context.getString(R.string.senha6digitos)
                    is FirebaseAuthInvalidCredentialsException -> context.getString(R.string.emailvalido)
                    is FirebaseAuthUserCollisionException -> context.getString(R.string.emailemuso)
                    is FirebaseNetworkException -> context.getString(R.string.semconexao)
                    else -> context.getString(R.string.errocadastar)

                }
                listneers.onFailure(errorMensage)
            }
        }
    }

    fun loginAttendant(email: String, password: String, listneers: AuthListneers) {

        if (email.isEmpty() || password.isEmpty()) {

            listneers.onFailure(context.getString(R.string.preencha))
        } else {

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {
                    listneers.onSucess(context.getString(R.string.loginRealizado))
                }

            }.addOnFailureListener {

                val errorMensage = when (it) {

                    is FirebaseAuthInvalidCredentialsException ->   context.getString(R.string.emailousenha)
                    is FirebaseAuthInvalidUserException -> context.getString(R.string.emailousenha)
                    is FirebaseNetworkException -> context.getString(R.string.semconexao)
                    else -> context.getString(R.string.sucessoUser)

                }

                listneers.onFailure(errorMensage)
            }
        }
    }

    fun forgotPassword(email: String, listneers: AuthListneers) {

        if (email.isEmpty()) {

            listneers.onFailure(context.getString(R.string.digiteseuemail))

        } else {

            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {

                listneers.onSucess(context.getString(R.string.verificaremailoulixo))

            }.addOnFailureListener {

                listneers.onFailure(context.getString(R.string.sucessoUser))

            }
        }
    }
}

