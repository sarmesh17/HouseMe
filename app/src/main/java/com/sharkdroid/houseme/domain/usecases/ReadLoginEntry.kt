package com.sharkdroid.houseme.domain.usecases

import com.sharkdroid.houseme.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadLoginEntry(private val userManager: LocalUserManager) {

     operator fun invoke():Flow<Boolean> {

        return userManager.readLoginEntry()

    }
}