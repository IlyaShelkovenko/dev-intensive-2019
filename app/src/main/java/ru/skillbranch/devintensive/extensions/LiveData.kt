/**
 * Created by Ilia Shelkovenko on 06.10.2020.
 */
package ru.skillbranch.devintensive.extensions

import androidx.lifecycle.MutableLiveData

fun <T>mutableLiveData(defaultValue: T? = null): MutableLiveData<T> {
    val data = MutableLiveData<T>()

    if(defaultValue != null) {
        data.value = defaultValue
    }

    return data
}