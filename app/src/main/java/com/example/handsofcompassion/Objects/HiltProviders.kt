package com.example.handsofcompassion.Objects

import android.app.Application
import android.content.Context
import com.example.handsofcompassion.Data.Auth.Auth
import com.example.handsofcompassion.Data.FireStore.EmployeesFireStore
import com.example.handsofcompassion.Repository.RepositoryAuth
import com.example.handsofcompassion.Repository.RepositoryEmployees
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
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

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
    fun auth(firebaseAuth: FirebaseAuth,firebaseFirestore: FirebaseFirestore,context: Context):Auth{
        return Auth(firebaseAuth,firebaseFirestore, context)
    }
    @Provides
    @Singleton
    fun Repositoryauth(auth: Auth):RepositoryAuth{
        return RepositoryAuth(auth)
    }

    @Provides
    @Singleton
    fun employeesFireStore(firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth,context: Context): EmployeesFireStore {

        return EmployeesFireStore(firestore, firebaseAuth, context)

    }

    @Provides
    @Singleton
    fun RepositoryEmployees(employeesFireStore: EmployeesFireStore):RepositoryEmployees{

        return com.example.handsofcompassion.Repository.RepositoryEmployees(employeesFireStore)

    }

}