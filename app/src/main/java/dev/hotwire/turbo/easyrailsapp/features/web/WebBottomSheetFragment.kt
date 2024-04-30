package dev.hotwire.turbo.easyrailsapp.features.web

import dev.hotwire.turbo.easyrailsapp.base.NavDestination
import dev.hotwire.turbo.fragments.TurboWebBottomSheetDialogFragment
import dev.hotwire.turbo.nav.TurboNavGraphDestination

@TurboNavGraphDestination(uri = "turbo://fragment/web/modal/sheet")
class WebBottomSheetFragment : TurboWebBottomSheetDialogFragment(), NavDestination
