/**
 * Created by Ilia Shelkovenko on 04.08.2020.
 */

package ru.skillbranch.devintensive.models

data class UserView(
    val id : String,
    val fullName: String,
    val nickName: String,
    var avatar: String? = null,
    var status: String? = "offline",
    val initials: String?
) {
}