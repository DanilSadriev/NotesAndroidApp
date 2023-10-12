package com.sdr.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sdr.notes.presentation.bookmark.BookmarkViewModel
import com.sdr.notes.presentation.detail.DetailAssistedFactory
import com.sdr.notes.presentation.home.HomeViewModel
import com.sdr.notes.presentation.navigation.NoteNavigation
import com.sdr.notes.presentation.navigation.Screens
import com.sdr.notes.presentation.navigation.navigateToSingleTop
import com.sdr.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var assistedFactory: DetailAssistedFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(), Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.Transparent.toArgb(), Color.Transparent.toArgb()
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                NoteApp()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background,
//                    contentColor = MaterialTheme.colorScheme.onSurface
//                ) {
//
//                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NoteApp() {
        val homeViewModel: HomeViewModel = viewModel()
        val bookmarkViewModel: BookmarkViewModel = viewModel()
        val navController = rememberNavController()
        var currentTab by remember {
            mutableStateOf(TabScreen.Home)
        }
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            InputChip(
                                selected = currentTab == TabScreen.Home,
                                onClick = {
                                    currentTab = TabScreen.Home
                                    navController.navigateToSingleTop(
                                        route = Screens.Home.name
                                    )
                                },
                                label = {
                                    Text(text = stringResource(id = R.string.app_bar_home_title))
                                }
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        InputChip(
                            selected = currentTab == TabScreen.BookMark,
                            onClick = {
                                currentTab = TabScreen.BookMark
                                navController.navigateToSingleTop(
                                    route = Screens.Bookmark.name
                                )
                            },
                            label = {
                                Text(text = stringResource(id = R.string.app_bar_bookmark_title))
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                navController.navigateToSingleTop(
                                    route = Screens.Detail.name
                                )
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    }
                )
            }
        ) {
            NoteNavigation(
                modifier = Modifier.padding(it),
                navHostController = navController,
                homeViewModel = homeViewModel,
                bookmarkViewModel = bookmarkViewModel,
                assistedFactory = assistedFactory
            )
        }
    }
}

enum class TabScreen {
    Home, BookMark
}