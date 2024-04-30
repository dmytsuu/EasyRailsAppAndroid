package dev.hotwire.turbo.easyrailsapp.base

import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.SHARE_STATE_ON
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.google.android.material.snackbar.Snackbar
import dev.hotwire.strada.BridgeDestination
import dev.hotwire.turbo.config.TurboPathConfigurationProperties
import dev.hotwire.turbo.config.context
import dev.hotwire.turbo.easyrailsapp.R
import dev.hotwire.turbo.easyrailsapp.util.BASE_URL
import dev.hotwire.turbo.nav.TurboNavDestination
import dev.hotwire.turbo.nav.TurboNavPresentationContext.DEFAULT
import dev.hotwire.turbo.nav.TurboNavPresentationContext.MODAL

interface NavDestination : TurboNavDestination, BridgeDestination {
    override fun shouldNavigateTo(newLocation: String): Boolean {
        return when (isNavigable(newLocation)) {
            true -> { displayNoticeMessage(newLocation); true }
            else -> { launchCustomTab(newLocation); false }
        }
    }

    override fun getNavigationOptions(
        newLocation: String,
        newPathProperties: TurboPathConfigurationProperties
    ): NavOptions {
        return when (newPathProperties.context) {
            MODAL -> slideAnimation()
            else -> super.getNavigationOptions(newLocation, newPathProperties)
        }
    }

    override fun bridgeWebViewIsReady(): Boolean {
        return session.isReady
    }

    private fun isNavigable(location: String): Boolean {
        return location.startsWith(BASE_URL)
    }

    private fun displayNoticeMessage(location: String) {
        var message = Uri.parse(location).getQueryParameter("notice")

        if (pathProperties.context == DEFAULT && message != null) {
            Snackbar.make(fragment.requireView(), message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun launchCustomTab(location: String) {
        val context = fragment.context ?: return
        val color = context.getColor(R.color.white)
        val colorParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(color)
            .setNavigationBarColor(color)
            .build()

        CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setShareState(SHARE_STATE_ON)
            .setUrlBarHidingEnabled(false)
            .setDefaultColorSchemeParams(colorParams)
            .build()
            .launchUrl(context, Uri.parse(location))
    }

    private fun slideAnimation(): NavOptions {
        return navOptions {
            anim {
                enter = R.anim.nav_slide_enter
                exit = R.anim.nav_slide_exit
                popEnter = R.anim.nav_slide_pop_enter
                popExit = R.anim.nav_slide_pop_exit
            }
        }
    }
}
