package dev.hotwire.turbo.easyrailsapp.util

import android.webkit.WebView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import dev.hotwire.strada.Strada
import dev.hotwire.turbo.easyrailsapp.R
import dev.hotwire.turbo.easyrailsapp.strada.bridgeComponentFactories

fun Toolbar.displayBackButtonAsCloseIcon() {
    navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_close)
}

val WebView.customUserAgent: String
    get() {
        val turboSubstring = "Turbo Native Android"
        val stradaSubstring = Strada.userAgentSubstring(bridgeComponentFactories)
        return "$turboSubstring; $stradaSubstring; ${settings.userAgentString}"
    }