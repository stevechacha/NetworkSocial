package com.steve.networksocial.presentation.activity.components

import com.steve.networksocial.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)
