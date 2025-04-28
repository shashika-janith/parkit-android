package com.arcee.parkit.presentation.update_profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.presentation.components.CustomTextField
import com.arcee.parkit.presentation.components.ScreenHeader
import com.arcee.parkit.ui.theme.ParkItTheme

@Composable
fun UpdateProfileScreen(onNavigateBack: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeContent),
        topBar = {
            ScreenHeader(onNavigateBack = onNavigateBack, title = "Update Profile")
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                CustomTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    placeholder = { Text("First Name") },
                )
                CustomTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = { Text("Last Name") },
                )
                CustomTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = { Text("Phone No.") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = null
                        )
                    }
                )
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdateProfileScreenPreview(
    onNavigateBack: () -> Unit = {}
) {
    ParkItTheme {
        UpdateProfileScreen(
            onNavigateBack = onNavigateBack
        )
    }
}