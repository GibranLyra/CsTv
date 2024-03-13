package com.gibranlyra.cstv.ui.ext

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.getRouteWithoutArguments(): String? = destination.route?.substringBefore("/")
