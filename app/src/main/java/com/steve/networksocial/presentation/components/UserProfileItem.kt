package com.steve.networksocial.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.steve.networksocial.R
import com.steve.networksocial.domain.models.User
import com.steve.networksocial.ui.theme.IconSizeMedium
import com.steve.networksocial.ui.theme.ProfilePictureSizeSmall
import com.steve.networksocial.ui.theme.SpaceMedium
import com.steve.networksocial.ui.theme.SpaceSmall


@ExperimentalMaterialApi
@Composable
fun UserProfileItem(
    modifier: Modifier = Modifier,
    actionIcon: @Composable () -> Unit = {},
    onItemClick: () -> Unit = {},
    onActionItemClick: () -> Unit = {},
    user: User
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onItemClick,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = SpaceSmall,
                    horizontal = SpaceMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(ProfilePictureSizeSmall)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = SpaceSmall)
            ) {
                Text(
                    text = "user.username",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text = "user.description",
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            IconButton(
                onClick = onActionItemClick,
                modifier = Modifier.size(IconSizeMedium)

            ) {
                actionIcon()
            }
        }
    }
}