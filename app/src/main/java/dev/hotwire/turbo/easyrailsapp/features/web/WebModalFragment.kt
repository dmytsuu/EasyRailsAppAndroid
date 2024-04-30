package dev.hotwire.turbo.easyrailsapp.features.web

import android.os.Bundle
import android.view.View
import dev.hotwire.turbo.nav.TurboNavGraphDestination
import dev.hotwire.turbo.easyrailsapp.util.displayBackButtonAsCloseIcon

@TurboNavGraphDestination(uri = "turbo://fragment/web/modal")
class WebModalFragment : WebFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState); initToolbar()
    }

    private fun initToolbar() {
        toolbarForNavigation()?.displayBackButtonAsCloseIcon()
    }
}
