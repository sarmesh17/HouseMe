package com.sharkdroid.houseme.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.sharkdroid.houseme.data.manager.LocalUserManagerImp
import com.sharkdroid.houseme.domain.manager.LocalUserManager
import com.sharkdroid.houseme.domain.usecases.ReadLoginEntry
import com.sharkdroid.houseme.domain.usecases.SaveLoginEntry
import com.sharkdroid.whatsappclone.domain.usecases.all_use_case.AppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth():FirebaseAuth{

        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase():FirebaseDatabase{

        return FirebaseDatabase.getInstance()

    }

    @Provides
    @Singleton
    fun provideFirebaseStorage():FirebaseStorage{

        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideSignInClient(@ApplicationContext context: Context): SignInClient {
        return Identity.getSignInClient(context)
    }

    @Provides
    @Singleton
    fun provideLocalUserManager(@ApplicationContext context: Context):LocalUserManager{

        return LocalUserManagerImp(context)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCase(localUserManager: LocalUserManager): AppEntryUseCase {

        return AppEntryUseCase(ReadLoginEntry(localUserManager), SaveLoginEntry(localUserManager))
    }
    @Provides
    @Singleton
    @Named("root")
    fun provideFirebaseDatabaseReference(database: FirebaseDatabase):DatabaseReference{
        return database.reference
    }



}