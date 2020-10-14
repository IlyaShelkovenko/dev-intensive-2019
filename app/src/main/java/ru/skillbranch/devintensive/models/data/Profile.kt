/**
 * Created by Ilia Shelkovenko on 14.10.2020.
 */

package ru.skillbranch.devintensive.models.data

data class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    val nickName: String = ru.skillbranch.devintensive.utils.Utils.transliteration("$firstName $lastName", "_")
    val rank: String = "Junior Android Developer"
    fun toMap() : Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )

}