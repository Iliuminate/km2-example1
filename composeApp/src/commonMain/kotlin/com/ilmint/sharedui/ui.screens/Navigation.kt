package com.ilmint.sharedui.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ilmint.sharedui.movies
import com.ilmint.sharedui.ui.screens.detail.DetailScreen
import com.ilmint.sharedui.ui.screens.home.HomeScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController= navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(route = "details/${movie.id}")
                }
            )
        }

        composable(
            route = "details/{movieId}",
            arguments = listOf(navArgument(name = "movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            DetailScreen(
                movie = movies.first { it.id == movieId },
                onBack = { navController.popBackStack() }
            )
        }
    }
}