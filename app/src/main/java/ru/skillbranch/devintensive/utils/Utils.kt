package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        firstName = if (firstName == "") null else firstName
        lastName = if (lastName == "") null else lastName

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val transLiterationMap = mapOf(
            " " to divider,
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )
        var translatedString = ""
        for (character in payload) {
            var isCapitalize = false
            if (character.isUpperCase())
                isCapitalize = true
            var translatedCharacter = transLiterationMap.getOrElse(
                character.toLowerCase().toString()
            ) { character.toString() }
            translatedString += if (isCapitalize) translatedCharacter.toUpperCase() else translatedCharacter
        }

        return translatedString
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstLetter =
            if (firstName?.trim()?.length == 0) "" else firstName?.firstOrNull()?.toUpperCase() ?: ""
        val secondLetter =
            if (lastName?.trim()?.length == 0) "" else lastName?.firstOrNull()?.toUpperCase() ?: ""
        return if ("$firstLetter$secondLetter" == "") null else {
            "$firstLetter$secondLetter"
        }
    }

}