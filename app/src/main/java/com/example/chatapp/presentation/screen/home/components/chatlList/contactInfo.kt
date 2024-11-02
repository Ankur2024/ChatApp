package com.example.chatapp.presentation.screen.home.components.chatlList

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.chatapp.R


data class contactInfo(
    val id: Int = 0,
    @StringRes val Name: Int,
    @DrawableRes val ImageResourceId: Int,
    @StringRes val message:Int,
    @StringRes val time:Int,
)

val contacts = listOf(
    contactInfo(
        1,
        R.string.contact1,
        R.drawable.contact1,
        R.string.chat1,
        R.string.time1
    ),
    contactInfo(
        3,
        R.string.contact3,
        R.drawable.contact3,
        R.string.chat3,
        R.string.time3
    ),
    contactInfo(
        4,
        R.string.contact4,
        R.drawable.contact4,
        R.string.chat4,
        R.string.time4
    ),
    contactInfo(
        5,
        R.string.contact5,
        R.drawable.contact5,
        R.string.chat5,
        R.string.time5
    ),
    contactInfo(
        6,
        R.string.contact6,
        R.drawable.contact6,
        R.string.chat6,
        R.string.time6
    ),
    contactInfo(
        7,
        R.string.contact7,
        R.drawable.contact4,
        R.string.chat7,
        R.string.time7
    ),
    contactInfo(
        8,
        R.string.contact8,
        R.drawable.contact3,
        R.string.chat7,
        R.string.time7
    ),
    contactInfo(
        9,
        R.string.contact9,
        R.drawable.contact5,
        R.string.chat7,
        R.string.time7
    ),
    contactInfo(
        10,
        R.string.contact10,
        R.drawable.contact6,
        R.string.chat7,
        R.string.time7
    ),
)