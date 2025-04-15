package com.arcee.parkit.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.arcee.parkit.presentation.profile.components.InfoItem
import com.arcee.parkit.presentation.profile.components.ProfileMetricCard

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>(),
    handleSignOut: () -> Unit,
) {
    val profile = viewModel.state.value

    listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineLarge.merge(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
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

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProfileMetricCard(label = "Parking Areas", value = "2")
                    }
                    VerticalDivider(thickness = 1.dp, modifier = Modifier.height(60.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        ProfileMetricCard(label = "Total Earnings", value = "LKR 25,255")
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                )

                InfoItem(
                    label = "Name",
                    value = profile.user?.name ?: "",
                    icon = Icons.Rounded.Person
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )

                InfoItem(
                    label = "Email",
                    value = profile.user?.email ?: "",
                    icon = Icons.Rounded.Email
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )

                InfoItem(
                    label = "Phone", value = profile.user?.phone ?: "", icon = Icons.Rounded.Phone
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
                        .padding(horizontal = 15.dp)
                        .height(55.dp)
                        .fillMaxSize(),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ExitToApp,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 9.dp),
                            tint = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = "Sign Out",
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 900)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(handleSignOut = {})
}