package com.najeeb.income_expense_tracker.features.home.data.models


val listJobs = listOf<JobModel>(
    JobModel(1, "Najeeb Esmaeel Abdo Alsan", "Mobile developer with Flutter, Kotlin, and Firebase"),
    JobModel(2, "Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),
    JobModel(3,"Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),
    JobModel(4,"Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),
    JobModel(5,"Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),
    JobModel(6,"Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),
    JobModel(7,"Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),
    JobModel(8,"Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),
    JobModel(9,"Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),
    JobModel(10,"Najeeb Esmaeel Abdo Alsan", "Mobile developer\n with Flutter, Kotlin, and Firebase"),

)

data class JobModel(
    val id: Int, val name: String, val description: String,
    var isFavorite: Boolean = false,

)