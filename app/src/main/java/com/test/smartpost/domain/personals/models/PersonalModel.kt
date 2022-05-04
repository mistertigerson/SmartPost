package com.test.smartpost.domain.personals.models

import java.io.Serializable


data class PersonalModel(
    val image: Int? = null,
    val name: String? = null,
    val email: String,
    val speciality: String? = null,
    val city: String? = null,
    val contacts: String? = null,
    val education: String? = null,
    val experience: String?,
    val etCareerDescription: String?
) : Serializable
