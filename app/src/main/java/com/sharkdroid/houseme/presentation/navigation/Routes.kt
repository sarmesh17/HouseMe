package com.sharkdroid.houseme.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object HomeScreen: Routes()

    @Serializable
    data object SignUpScreen:Routes()

    @Serializable
    data object SigInScreen:Routes()

    @Serializable
    data object Places:Routes()


}