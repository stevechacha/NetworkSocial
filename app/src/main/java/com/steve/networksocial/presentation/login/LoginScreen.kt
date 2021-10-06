package com.steve.networksocial.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.steve.networksocial.presentation.util.Screen
import com.steve.networksocial.ui.theme.SpaceMedium
import com.steve.networksocial.presentation.components.StandardTextField
import com.steve.networksocial.ui.theme.NetworkSocialTheme
import com.steve.networksocial.ui.theme.SpaceLarge
import com.steve.networksocial.R

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = SpaceLarge,
                bottom = 50.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.usernameText.value,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                keyboardType = KeyboardType.Email,
                error = viewModel.usernameError.value,
                hint = stringResource(id = R.string.login_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.passwordText.value,
                onValueChange = {
                    viewModel.setPasswordText(it)
                },
                hint = stringResource(id = R.string.password_hint),
                keyboardType = KeyboardType.Password,
                error = viewModel.passwordError.value,
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = {
                    navController.navigate(
                        Screen.MainFeedScreen.route
                    )
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_an_account_yet))
                append(" ")
                val signUpText = stringResource(id = R.string.sign_up)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append(signUpText)
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    navController.navigate(
                        Screen.RegisterScreen.route
                    )
                }
        )
    }

}

@Composable
@Preview(showBackground = true)
fun PreviewLoginScreen(){
    val navController= rememberNavController()
    NetworkSocialTheme {
        LoginScreen(navController = navController)
    }
}