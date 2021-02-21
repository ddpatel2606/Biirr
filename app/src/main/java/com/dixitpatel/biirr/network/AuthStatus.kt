package com.dixitpatel.biirr.network

/**
 *  Authentication status
 */
sealed class AuthStatus
{
        object SUCCESS : AuthStatus()
        object ERROR : AuthStatus()
        object LOADING : AuthStatus()
}