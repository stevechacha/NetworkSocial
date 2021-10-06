package com.steve.networksocial.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.steve.networksocial.domain.models.User
import com.steve.networksocial.presentation.components.StandardTextField
import com.steve.networksocial.presentation.components.StandardToolbar
import com.steve.networksocial.presentation.components.UserProfileItem
import com.steve.networksocial.presentation.search.SearchViewModel
import com.steve.networksocial.ui.theme.SpaceLarge
import com.steve.networksocial.R
import com.steve.networksocial.presentation.util.states.StandardTextFieldState
import com.steve.networksocial.ui.theme.IconSizeMedium
import com.steve.networksocial.ui.theme.SpaceMedium


@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.search_for_users),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceLarge)
        ) {
            StandardTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = viewModel.searchState.value.text,
                hint = stringResource(id = R.string.search),
                error = viewModel.searchState.value.error,
                leadingIcon = Icons.Default.Search,
                onValueChange = {
                    viewModel.setSearchState(
                        StandardTextFieldState(text = it)
                    )
                }
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(SpaceLarge))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(10) {
                    UserProfileItem(
                        user = User(
                            profilePictureUrl = "",
                            username = "Philipp Lackner",
                            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                                    "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                                    "magna aliquyam erat, sed diam voluptua",
                            followerCount = 234,
                            followingCount = 534,
                            postCount = 65
                        ),
                        actionIcon = {
                            Icon(
                                imageVector = Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground,
                                modifier = Modifier.size(IconSizeMedium)
                            )
                        }
                    )
                    Spacer(modifier = androidx.compose.ui.Modifier.height(SpaceMedium))
                }
            }
        }
    }
}