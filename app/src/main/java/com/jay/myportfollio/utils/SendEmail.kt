package com.jay.myportfollio.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log

fun Context.sendMail(to: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/html")
        intent.setPackage("com.google.android.gm")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Log.e("SendEmail",e.message.toString())
    } catch (t: Throwable) {
        Log.e("SendEmail",t.message.toString())
    }
}