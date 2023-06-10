package com.example.readscape

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.readscape.model.UserDao
import com.example.readscape.ui.EntryViewModel
import com.example.readscape.ui.LoginScreen
import com.example.readscape.ui.ProfileScreen
import com.example.readscape.ui.RegistrationFailedScreen
import com.example.readscape.ui.RegistrationSuccessfulScreen
import com.example.readscape.ui.SignupScreen

enum class ReadScapeScreen {
    LogIn,
    SignUp,
    RegistrationSuccess,
    RegistrationFailed,
    Profile,
    Home
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadScapeApp(userDao: UserDao) {
    val viewModel: EntryViewModel = viewModel(factory = EntryViewModelFactory(userDao))
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = ReadScapeScreen.valueOf(
        backStackEntry?.destination?.route ?: ReadScapeScreen.LogIn.name
    )
    val loginStatus by viewModel.loginStatus.collectAsState()

    LaunchedEffect(loginStatus) {
        when (loginStatus) {
            true -> navController.navigate(ReadScapeScreen.Home.name) {
                // Pop up to the start destination (Login) and clear it from the back stack
                popUpTo(ReadScapeScreen.LogIn.name) {
                    // Exclude the start destination (Login) from the back stack
                    inclusive = false
                }
            }
            false -> navController.navigate(ReadScapeScreen.LogIn.name)
        }
    }
    Scaffold(
        topBar = {
            ReadScapeAppBar(
                canNavigateBack = false,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = ReadScapeScreen.LogIn.name,
            modifier = Modifier.padding(innerPadding)
        ) {
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

            composable(route = ReadScapeScreen.RegistrationSuccess.name) {
                RegistrationSuccessfulScreen(
                    onBackToLoginClicked = { navController.navigate(ReadScapeScreen.LogIn.name) }
                )
            }

            composable(route = ReadScapeScreen.RegistrationFailed.name) {
                RegistrationFailedScreen(
                    onTryAgainClicked = { navController.navigate(ReadScapeScreen.SignUp.name) }
                )
            }

            composable(route = ReadScapeScreen.Home.name) {

            }

            /*
            composable(route = ReadScapeScreen.Profile.name) {
                ProfileScreen(
                    user =
                ) {

                }
            }*/
        }
    }
}
