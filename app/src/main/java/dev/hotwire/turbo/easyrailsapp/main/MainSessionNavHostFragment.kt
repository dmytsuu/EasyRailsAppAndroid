package dev.hotwire.turbo.easyrailsapp.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.hotwire.strada.Bridge
import dev.hotwire.turbo.config.TurboPathConfiguration
import dev.hotwire.turbo.easyrailsapp.features.native.NumbersFragment
import dev.hotwire.turbo.easyrailsapp.features.web.WebBottomSheetFragment
import dev.hotwire.turbo.easyrailsapp.features.web.WebFragment
import dev.hotwire.turbo.easyrailsapp.features.web.WebHomeFragment
import dev.hotwire.turbo.easyrailsapp.features.web.WebModalFragment
import dev.hotwire.turbo.easyrailsapp.util.CURRENT_URL
import dev.hotwire.turbo.session.TurboSessionNavHostFragment
import dev.hotwire.turbo.easyrailsapp.util.customUserAgent
import kotlin.reflect.KClass

@Suppress("unused")
class MainSessionNavHostFragment : TurboSessionNavHostFragment() {
    override val sessionName = "main"

    override val startLocation = CURRENT_URL

    override val registeredActivities: List<KClass<out AppCompatActivity>>
        get() = listOf()

    override val registeredFragments: List<KClass<out Fragment>>
        get() = listOf(
            WebFragment::class,
            WebHomeFragment::class,
            WebModalFragment::class,
            WebBottomSheetFragment::class,
            NumbersFragment::class
        )

    override val pathConfigurationLocation: TurboPathConfiguration.Location
        get() = TurboPathConfiguration.Location(assetFilePath = "json/configuration.json")

    override fun onSessionCreated() {
        super.onSessionCreated()
        session.webView.settings.userAgentString = session.webView.customUserAgent

        // Initialize Strada bridge with new WebView instance
        Bridge.initialize(session.webView)
    }
}
