package com.example.readscape

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.readscape.model.BookRepository
import com.example.readscape.model.user.UserDao
import com.example.readscape.ui.BookOverviewScreen
import com.example.readscape.ui.EntryViewModel
import com.example.readscape.ui.LoginScreen
import com.example.readscape.ui.ProfileScreen
import com.example.readscape.ui.RegistrationFailedScreen
import com.example.readscape.ui.RegistrationSuccessfulScreen
import com.example.readscape.ui.SignupScreen
import com.example.readscape.ui.UserPreferences

enum class ReadScapeScreen {
    LogIn,
    SignUp,
    RegistrationSuccess,
    RegistrationFailed,
    BookOverview,
    BookDetail,
    Profile
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadScapeAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "ArrowBack"
                    )
                }
            }
        }
    )
}

@Composable
fun ReadscapeMenuNavigationBar(navController: NavController) {
    val currentRoute = currentRoute(navController)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(
            onClick = {
                navController.navigate(ReadScapeScreen.BookOverview.name) {
                    popUpTo(ReadScapeScreen.BookOverview.name) {
                        inclusive = true
                    }
                }
            }
        ) {
            Text("Book Overview")
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = {
                navController.navigate(ReadScapeScreen.Profile.name) {
                    popUpTo(ReadScapeScreen.Profile.name) {
                        inclusive = true
                    }
                }
            }
        ) {
            Text("Profile")
        }
    }
}

@Composable
private fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadScapeApp(userDao: UserDao, bookRepository: BookRepository) {
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)
    val viewModel: EntryViewModel = viewModel(factory = EntryViewModelFactory(userDao, bookRepository, userPreferences))
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = ReadScapeScreen.valueOf(
        backStackEntry?.destination?.route ?: ReadScapeScreen.LogIn.name
    )
    val loginStatus by viewModel.loginStatus.collectAsState()


    LaunchedEffect(loginStatus) {
        when (loginStatus) {
            true -> navController.navigate(ReadScapeScreen.BookOverview.name)
            false -> navController.navigate(ReadScapeScreen.LogIn.name)
        }
    }
    Scaffold(
        topBar = {
            ReadScapeAppBar(
                canNavigateBack = false,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            if (currentScreen == ReadScapeScreen.BookOverview ||
                currentScreen == ReadScapeScreen.BookDetail ||
                currentScreen == ReadScapeScreen.Profile
            ) {
                ReadscapeMenuNavigationBar (navController = navController)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = ReadScapeScreen.LogIn.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            /* LOG IN */
            composable(route = ReadScapeScreen.LogIn.name) {
                LoginScreen(
                    onLogInButtonClicked = { email, password ->
                        viewModel.logIn(email, password)
                    },
                    onSignUpButtonClicked = {
                        navController.navigate(ReadScapeScreen.SignUp.name)
                    },
                    onValueChangedEmail = {
                        email -> viewModel.setEmail(email)
                    },
                    onValueChangedPassword = {
                        password -> viewModel.setPassword(password)
                    }
                )
            }

            /* SIGN UP */
            composable(route = ReadScapeScreen.SignUp.name) {
                SignupScreen(
                    onLogInButtonClicked = {
                        navController.navigate(ReadScapeScreen.LogIn.name)
                    },
                    onSignUpButtonClicked = { email, password, repeatPassword ->
                        if (viewModel.signUp(email, password, repeatPassword)) {
                            navController.navigate(ReadScapeScreen.RegistrationSuccess.name)
                        } else {
                            navController.navigate(ReadScapeScreen.RegistrationFailed.name)
                        }
                    },
                    onValueChangedEmail = {
                        email -> viewModel.setEmail(email)
                    },
                    onValueChangedPassword = {
                        password -> viewModel.setPassword(password)
                    },
                    onValueChangedRepeatPassword = {

                    }
                )
            }

            /* REGISTRATION SUCCESS */
            composable(route = ReadScapeScreen.RegistrationSuccess.name) {
                RegistrationSuccessfulScreen(
                    onBackToLoginClicked = { navController.navigate(ReadScapeScreen.LogIn.name) }
                )
            }

            /* REGISTRATION FAILED */
            composable(route = ReadScapeScreen.RegistrationFailed.name) {
                RegistrationFailedScreen(
                    onTryAgainClicked = { navController.navigate(ReadScapeScreen.SignUp.name) }
                )
            }

            /* BOOK OVERVIEW */
            composable(route = ReadScapeScreen.BookOverview.name) {
                val books by viewModel.books.collectAsState(initial = listOf())
                LaunchedEffect(key1 = Unit) {
                    viewModel.fetchBooks()
                }
                println(books)
                BookOverviewScreen(
                    books = books
                )
            }

            /* BOOK DETAIL */
            composable(route = ReadScapeScreen.BookDetail.name) {

            }

            /*PROFILE*/
            composable(route = ReadScapeScreen.Profile.name) {
                viewModel.loggedInUser.value?.let { it1 ->
                    ProfileScreen(
                        user = it1,
                        onLogoutClicked = {
                            viewModel.logOut()
                            navController.navigate(ReadScapeScreen.LogIn.name)
                        }
                    )
                }
            }
        }
    }
}
