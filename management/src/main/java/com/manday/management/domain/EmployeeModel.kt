package com.manday.management.domain

import java.io.Serializable

data class EmployeeModel (
    var name: String,
    var surname: String,
    var email: String,
    var typeEmployee: Int,
    var image: String
): Serializable