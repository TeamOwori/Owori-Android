package com.owori.android.auth.data

data class MemberRequest (
    val token: String,
    val authProvider: AuthProvider
)