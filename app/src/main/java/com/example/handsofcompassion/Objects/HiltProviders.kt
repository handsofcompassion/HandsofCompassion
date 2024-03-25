package com.example.handsofcompassion.Objects

import com.example.handsofcompassion.Data.Auth.Auth
import com.example.handsofcompassion.Repository.RepositoryAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HiltProviders {

    @Provides
    @Singleton
    fun fireBaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun fireBaseFireStore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun auth(firebaseAuth: FirebaseAuth,firebaseFirestore: FirebaseFirestore):Auth{
        return Auth(firebaseAuth,firebaseFirestore)
    }
    @Provides
    @Singleton
    fun Repositoryauth(auth: Auth):RepositoryAuth{
        return RepositoryAuth(auth)
    }
}