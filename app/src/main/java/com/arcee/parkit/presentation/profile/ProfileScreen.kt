package com.arcee.parkit.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arcee.parkit.presentation.components.TopBar
import com.arcee.parkit.presentation.profile.components.InfoItem

@Composable
fun ProfileScreen(handleSignOut: () -> Unit) {
    Scaffold(topBar = {
        TopBar(
            onNavigateBack = { /*TODO*/ },
            label = "Profile"
        )
    }, containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()

        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.surfaceDim)
                        .padding(9.dp)
                ) {
                    AsyncImage(
                        model = "https://citations.robbinsparking.com/assets/images/2022-06-oie_jpg-1.png",
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                )

                InfoItem(
                    label = "Name",
                    value = "Shashika Jayawardhana",
                    icon = Icons.Rounded.Person
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )

                InfoItem(
                    label = "Address",
                    value = "94, Awissawella Road, Welivita, Kaduwela",
                    icon = Icons.Rounded.LocationOn
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )

                InfoItem(
                    label = "Email",
                    value = "shashika.janith@outlook.com",
                    icon = Icons.Rounded.Email
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )

                InfoItem(
                    label = "Phone",
                    value = "0717580287",
                    icon = Icons.Rounded.Phone
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 21.dp, top = 15.dp)
                )

                OutlinedButton(
                    onClick = { handleSignOut() },
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxSize(),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ExitToApp,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 9.dp)
                        )
                        Text(
                            text = "Sign Out",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(handleSignOut = {})
}