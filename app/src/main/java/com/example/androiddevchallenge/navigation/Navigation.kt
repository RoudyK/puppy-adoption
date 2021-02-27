package com.example.androiddevchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.DataSource
import com.example.androiddevchallenge.ui.screen.details.DetailsScreen
import com.example.androiddevchallenge.ui.screen.details.DetailsViewModel
import com.example.androiddevchallenge.ui.screen.home.HomeScreen
import com.example.androiddevchallenge.ui.screen.home.HomeViewModel


@Composable
fun Navigation() {
    val homeViewModel = HomeViewModel(DataSource.puppies())
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, homeViewModel) }
        composable("details/{puppyId}") {
            DetailsScreen(
                navController,
                DetailsViewModel(requireNotNull(it.arguments?.getString("puppyId")))
            )
        }
    }
}
