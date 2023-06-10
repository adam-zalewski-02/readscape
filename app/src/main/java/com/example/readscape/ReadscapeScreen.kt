package com.example.readscape

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.readscape.ui.EntryViewModel
import com.example.readscape.ui.LoginScreen
import com.example.readscape.ui.RegistrationSuccessfulScreen
import com.example.readscape.ui.SignupScreen

enum class ReadScapeScreen {
    LogIn,
    SignUp,
    RegistrationSuccess,
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
fun ReadScapeApp(viewModel: EntryViewModel = viewModel()) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = ReadScapeScreen.valueOf(
        backStackEntry?.destination?.route ?: ReadScapeScreen.LogIn.name
    )

    val viewModel: EntryViewModel = viewModel()

    Scaffold(
        topBar = {
            ReadScapeAppBar(
                canNavigateBack = false,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ReadScapeScreen.LogIn.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ReadScapeScreen.LogIn.name) {
                LoginScreen(
                    onLogInButtonClicked = { email, password ->
                        viewModel.logIn(email, password)
                        if (viewModel.uiState.value.loggedIn) {
                            println("login success")
                            navController.navigate(ReadScapeScreen.Home.name)
                        } else {
                            println("login failed")
                            navController.navigate(ReadScapeScreen.LogIn.name)
                        }
                    },
                    onSignUpButtonClicked = {
                        navController.navigate(ReadScapeScreen.SignUp.name)
                    },
                    onValueChangedEmail = {
                        email -> viewModel.setEmail(email)
                        navController.navigate(ReadScapeScreen.SignUp.name)
                    },
                    onValueChangedPassword = {
                        password -> viewModel.setPassword(password)
                    }
                )
            }
            
            composable(route = ReadScapeScreen.SignUp.name) {
                SignupScreen(
                    onLogInButtonClicked = { navController.navigate(ReadScapeScreen.LogIn.name) },
                    onSignUpButtonClicked = { navController.navigate(ReadScapeScreen.RegistrationSuccess.name) }
                )
            }

            composable(route = ReadScapeScreen.RegistrationSuccess.name) {
                RegistrationSuccessfulScreen(
                    onBackToLoginClicked = { navController.navigate(ReadScapeScreen.LogIn.name) }
                )
            }

            composable(route = ReadScapeScreen.Home.name) {

            }
        }
    }
}
