package com.sharkdroid.houseme.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

     fun readLoginEntry():Flow<Boolean>

    suspend fun saveLoginEntry()
}