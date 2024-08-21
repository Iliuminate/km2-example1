package com.ilmint.sharedui.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ilmint.sharedui.data.MovieServices
import com.ilmint.sharedui.data.movies
import com.ilmint.sharedui.ui.screens.detail.DetailScreen
import com.ilmint.sharedui.ui.screens.home.HomeScreen
import com.ilmint.sharedui.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.tmdb_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val client = remember {
        HttpClient() {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    val apiKey = stringResource(Res.string.tmdb_key)
    val viewModel = viewModel{
        HomeViewModel(
            movieServices = MovieServices(
                apiKey = apiKey,
                client = client
            )
        )
    }

    NavHost(navController= navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(route = "details/${movie.id}")
                },
                vm = viewModel
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