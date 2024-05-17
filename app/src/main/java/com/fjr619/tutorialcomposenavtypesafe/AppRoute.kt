package com.fjr619.tutorialcomposenavtypesafe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
object Page1

@Serializable
data class Page2(val person: Person = Person())

@Serializable
@Parcelize
data class Person(
    val name: String? = null,
    val age: Int? = null
) : Parcelable