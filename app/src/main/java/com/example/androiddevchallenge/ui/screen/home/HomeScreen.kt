package com.example.androiddevchallenge.ui.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.DataSource
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.widget.*
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    val state = homeViewModel.homeState.collectAsState(initial = HomeState.Initial)
    val scrollState = rememberLazyListState()
    val paws = homeViewModel.paws.collectAsState(initial = listOf())

    MainScaffold(
        topBar = {
            Toolbar(
                title = "Pick your doggo 🐶 🐶",
                elevation = if (scrollState.firstVisibleItemScrollOffset > 0) {
                    4.dp
                } else {
                    0.dp
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            PawsBackground(paws = paws.value)

            Crossfade(
                targetState = state.value,
                animationSpec = tween(150)
            ) { currentState ->

                Root(homeViewModel) {
                    when (currentState) {
                        HomeState.Initial -> {
                        }
                        HomeState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is HomeState.Success -> {
                            HomeList(
                                scrollState = scrollState,
                                homeViewModel = homeViewModel,
                                navController = navController,
                                puppies = currentState.puppies
                            )
                        }
                        HomeState.Failure -> ErrorView {
                            homeViewModel.loadPuppies()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Root(
    homeViewModel: HomeViewModel,
    columnScope: @Composable ColumnScope.() -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .onGloballyPositioned {
                homeViewModel.generatePaws(
                    it.size.width /
                            LocalContext.current
                                .resources.displayMetrics.density
                )
            }
            .background(MaterialTheme.colors.surface.copy(alpha = 0.4f)),
        content = columnScope
    )
}

@Composable
private fun HomeList(
    scrollState: LazyListState,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    puppies: List<Puppy>
) {
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(puppies.size + 1) { index ->
            if (index < puppies.size) {
                val puppy = puppies[index]
                HomeItem(
                    puppy = puppy,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                        .clickable {
                            navController.navigate("details/${puppy.id}")
                        },
                    color = Color(
                        (90..130).random(),
                        90,
                        (110..140).random(),
                    )
                )
            } else {
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = {
                        homeViewModel.forceFailure()
                    },
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        backgroundColor = MaterialTheme.colors.error
                    )
                ) {
                    Text("I hate dogs")
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreviewLight() {
    MyTheme(darkTheme = false) {
        HomeScreen(
            navController = rememberNavController(),
            homeViewModel = HomeViewModel(flowOf(DataSource.puppies))
        )
    }
}

@Preview
@Composable
fun HomeScreenPreviewDark() {
    MyTheme(darkTheme = true) {
        HomeScreen(
            navController = rememberNavController(),
            homeViewModel = HomeViewModel(flowOf(DataSource.puppies))
        )
    }
}
