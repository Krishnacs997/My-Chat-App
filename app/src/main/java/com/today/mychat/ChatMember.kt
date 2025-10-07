package com.today.mychat

import java.io.Serializable

data class ChatMember(
    val id: String,
    val name: String,
    val email: String,
    val mobile: String,
    val isOnline: Boolean,
    val lastSeen: String,
    val profileImageUrl: String? = null
) : Serializable
