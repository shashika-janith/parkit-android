package com.arcee.parkit.presentation.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arcee.parkit.R
import com.arcee.parkit.data.remote.dto.SignInDto
import com.arcee.parkit.domain.model.User
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel<SignInViewModel>(),
    didSignIn: (data: User) -> Unit,
) {
    val state = viewModel.state.value;

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(state.error) {
        if (state.error.isNotBlank()) {
            scope.launch {
                snackbarHostState.showSnackbar("Failed to sign-in. Try again!")
            }
        }
    }

    LaunchedEffect(state.userSession) {
        if (state.userSession != null) {
            scope.launch {
                didSignIn(state.userSession)
            }
        }
    }

    fun handleSignIn() {
        val dto = SignInDto(
            email = email.lowercase(),
            password = password
        )

        viewModel.signIn(dto)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 21.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_1),
                    contentDescription = null,
                    modifier = Modifier
                        .height(180.dp)
                        .width(180.dp)
                        .padding(bottom = 21.dp, top = 45.dp)
                )
                Text(
                    "Welcome to",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                )
                Text(
                    "ParkIt",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge.merge(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 21.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp),
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = null
                        )
                    },
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 21.dp),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = null
                        )

                    },
                    visualTransformation = PasswordVisualTransformation()
                )
                Button(
                    onClick = { handleSignIn() },
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxSize(0.8f),
                ) {
                    Text(
                        text = "Sign In",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        style = TextStyle(color = Color.White)
                    )
                }
                TextButton(onClick = {}) {
                    Text(
                        text = "Forgot password",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        style = TextStyle(color = MaterialTheme.colorScheme.primary)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(
    didSignIn: () -> Unit = {}
) {
    SignInScreen(didSignIn = {})
}