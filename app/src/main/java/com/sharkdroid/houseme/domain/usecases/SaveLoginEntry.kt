package com.sharkdroid.houseme.domain.usecases

import com.sharkdroid.houseme.domain.manager.LocalUserManager

class SaveLoginEntry(private val userManager: LocalUserManager) {


    suspend operator fun invoke (){

        userManager.saveLoginEntry()

    }
}