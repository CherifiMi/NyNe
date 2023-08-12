package com.example.nyne.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.nyne.domein.util.others.NetworkObserver
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    startDestination: String,
    navController: NavHostController,
    networkStatus: NetworkObserver.Status,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        /** Welcome Screen */
        composable(
            route = Screens.WelcomeScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))

            },
        ) {

        }

        /** Home Screen */
        composable(
            route = BottomBarScreen.Home.route,
            enterTransition = { fadeIn(animationSpec = tween(400)) },
            exitTransition = {
                if (initialState.destination.route == Screens.BookDetailScreen.route) {
                    slideOutHorizontally(
                        targetOffsetX = { -300 }, animationSpec = tween(
                            durationMillis = 300, easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                } else fadeOut(animationSpec = tween(400))
            },
            popEnterTransition = {
                if (targetState.destination.route == Screens.BookDetailScreen.route) {
                    slideInHorizontally(
                        initialOffsetX = { -300 }, animationSpec = tween(
                            durationMillis = 300, easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                } else fadeIn(animationSpec = tween(400))
            },
            popExitTransition = { fadeOut(animationSpec = tween(400)) }
        ) {

        }

        /** Book Detail Screen */
        composable(
            route = Screens.BookDetailScreen.route,
            arguments = listOf(
                navArgument(BOOK_ID_ARG_KEY) {
                    type = NavType.StringType
                },
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))

            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))

            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments!!.getString(BOOK_ID_ARG_KEY)!!

        }

        /** Categories Screen */
        composable(
            route = BottomBarScreen.Categories.route,
            enterTransition = { fadeIn(animationSpec = tween(400)) },
            exitTransition = {
                if (initialState.destination.route == Screens.CategoryDetailScreen.route) {
                    slideOutHorizontally(
                        targetOffsetX = { -300 }, animationSpec = tween(
                            durationMillis = 300, easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                } else fadeOut(animationSpec = tween(400))
            },
            popEnterTransition = {
                if (targetState.destination.route == Screens.CategoryDetailScreen.route) {
                    slideInHorizontally(
                        initialOffsetX = { -300 }, animationSpec = tween(
                            durationMillis = 300, easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                } else fadeIn(animationSpec = tween(400))
            },
            popExitTransition = { fadeOut(animationSpec = tween(400)) }
        ) {

        }

        /** Category Detail Screen */
        composable(
            route = Screens.CategoryDetailScreen.route,
            arguments = listOf(navArgument(CATEGORY_DETAIL_ARG_KEY) {
                type = NavType.StringType
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))

            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))

            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) { backStackEntry ->
            val category = backStackEntry.arguments!!.getString(CATEGORY_DETAIL_ARG_KEY)!!

        }

        /** Library Screen */
        composable(
            route = BottomBarScreen.Library.route,
            enterTransition = { fadeIn(animationSpec = tween(400)) },
            exitTransition = {
                if (initialState.destination.route == Screens.BookDetailScreen.route) {
                    slideOutHorizontally(
                        targetOffsetX = { -300 }, animationSpec = tween(
                            durationMillis = 300, easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                } else fadeOut(animationSpec = tween(400))
            },
            popEnterTransition = {
                if (targetState.destination.route == BottomBarScreen.Library.route) {
                    slideInHorizontally(
                        initialOffsetX = { -300 }, animationSpec = tween(
                            durationMillis = 300, easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                } else fadeIn(animationSpec = tween(400))
            },
            popExitTransition = { fadeOut(animationSpec = tween(400)) }
        ) {

        }

        /** Reader Detail Screen */
        composable(
            route = Screens.ReaderDetailScreen.route,
            arguments = listOf(navArgument(
                BOOK_ID_ARG_KEY
            ) {
                type = NavType.StringType
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))

            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))

            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments!!.getString(BOOK_ID_ARG_KEY)!!

        }

        /** Reader Screen */
        composable(
            route = Screens.ReaderScreen.route,
            arguments = listOf(navArgument(
                BOOK_ID_ARG_KEY
            ) {
                type = NavType.StringType
            }, navArgument(READER_CHAPTER_INDEX_KEY) {
                type = NavType.IntType
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments!!.getString(BOOK_ID_ARG_KEY)!!
            val chapterIdx = backStackEntry.arguments!!.getInt(READER_CHAPTER_INDEX_KEY)

        }

        /** Settings Screen */
        composable(
            route = BottomBarScreen.Settings.route,
            enterTransition = { fadeIn(animationSpec = tween(400)) },
            exitTransition = {
                if (initialState.destination.route == Screens.OSLScreen.route || initialState.destination.route == Screens.AboutScreen.route) {
                    slideOutHorizontally(
                        targetOffsetX = { -300 }, animationSpec = tween(
                            durationMillis = 300, easing = FastOutSlowInEasing
                        )
                    ) + fadeOut(animationSpec = tween(300))
                } else fadeOut(animationSpec = tween(400))
            },
            popEnterTransition = {
                if (initialState.destination.route == Screens.OSLScreen.route || initialState.destination.route == Screens.AboutScreen.route) {
                    slideInHorizontally(
                        initialOffsetX = { -300 }, animationSpec = tween(
                            durationMillis = 300, easing = FastOutSlowInEasing
                        )
                    ) + fadeIn(animationSpec = tween(300))
                } else fadeIn(animationSpec = tween(400))
            },
            popExitTransition = { fadeOut(animationSpec = tween(400)) }
        ) {

        }

        /** Open Source Licenses Screen */
        composable(
            route = Screens.OSLScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {

        }

        /** About Screen */
        composable(
            route = Screens.AboutScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
        ) {

        }
    }
}