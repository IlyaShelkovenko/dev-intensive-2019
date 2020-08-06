/**
 * Created by Ilia Shelkovenko on 06.08.2020.
 */
package ru.skillbranch.devintensive.extensions

fun String.truncate(number: Int = 16) : String{
    var sb = StringBuilder()
    var isTruncated = false
    var index = 0
    for(character in this){
        sb.append(character)
        index++
        if(index == number) {
            isTruncated = true
            break
        }
    }
    val str = sb.dropLastWhile { it == ' ' }
    if(isTruncated)
        return StringBuilder(str).append("...").toString()
    else
        return str.toString()
}
