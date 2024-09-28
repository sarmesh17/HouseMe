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
    data object PlacesScreen:Routes()

    @Serializable
    data object ForgetPassword:Routes()

    @Serializable
    data object HotelListScreen:Routes()

    @Serializable
    data object PaymentScreen:Routes()

    @Serializable
    data object RoomDetailScreen:Routes()

    @Serializable
    data object RoomScreen:Routes()

    @Serializable
    data object OwnerValidationScreen:Routes()

    @Serializable
    data object AddRoomScreen:Routes()

    @Serializable
    data object SplashScreen:Routes()

    @Serializable
    data object ThankYouScreen:Routes()




}