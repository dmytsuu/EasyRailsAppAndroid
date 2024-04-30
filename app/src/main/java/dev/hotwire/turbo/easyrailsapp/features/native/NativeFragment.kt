package dev.hotwire.turbo.easyrailsapp.features.native

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import dev.hotwire.turbo.fragments.TurboFragment
import dev.hotwire.turbo.easyrailsapp.R
import dev.hotwire.turbo.easyrailsapp.base.NavDestination

abstract class NativeFragment : TurboFragment(), NavDestination {
    fun setContent(inflater: LayoutInflater, container: ViewGroup?, content: @Composable () -> Unit): View? {
        val root = inflater.inflate(R.layout.fragment_native, container, false)
        val composeView = root.findViewById<ComposeView>(R.id.compose_view)

        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { content.invoke() }
        }

        return root
    }
}
