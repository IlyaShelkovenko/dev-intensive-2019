/**
 * Created by Ilia Shelkovenko on 19.08.2020.
 */
package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
    val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}
fun Activity.isKeyboardOpen() : Boolean{
    val rootView = findViewById<View>(android.R.id.content)
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val heightDiff = rootView.height - rect.height()
    val err = this.dpToPx(20F)

    return heightDiff > err
}

fun Activity.isKeyboardClosed() : Boolean{
    return !isKeyboardOpen()
}

fun Context.dpToPx (dp: Float): Float {
    return TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP , dp, this.resources.displayMetrics)
}