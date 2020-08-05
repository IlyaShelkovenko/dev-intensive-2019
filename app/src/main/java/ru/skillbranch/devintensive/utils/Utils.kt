package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName : String?) : Pair<String?,String?> {
        val firstName = ""
        val lastName = ""
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return ""
    }

    fun toInitials(firstName: String?, lastName: String?) : String?{
        return listOfNotNull(firstName,lastName)
            .map { it.first().toUpperCase() }
            .joinToString(" ")
    }
}