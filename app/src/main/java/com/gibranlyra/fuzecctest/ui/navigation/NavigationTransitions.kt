package com.gibranlyra.fuzecctest.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween

private const val ANIMATION_DURATION = 400

internal fun AnimatedContentTransitionScope<*>.slideInEnterTransition() =
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
        animationSpec = tween(ANIMATION_DURATION)
    )

internal fun AnimatedContentTransitionScope<*>.slideOutEnterTransition() =
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
        animationSpec = tween(ANIMATION_DURATION)
    )

internal fun AnimatedContentTransitionScope<*>.slideIntoTransition() =
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(ANIMATION_DURATION)
    )

internal fun AnimatedContentTransitionScope<*>.slideOutOfContainerTransition() =
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
        animationSpec = tween(ANIMATION_DURATION)
    )