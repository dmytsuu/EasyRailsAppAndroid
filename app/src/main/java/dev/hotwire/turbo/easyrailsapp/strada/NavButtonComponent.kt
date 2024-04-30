package dev.hotwire.turbo.easyrailsapp.strada

import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import dev.hotwire.strada.BridgeComponent
import dev.hotwire.strada.BridgeDelegate
import dev.hotwire.strada.Message
import dev.hotwire.turbo.easyrailsapp.R
import dev.hotwire.turbo.easyrailsapp.base.NavDestination
import dev.hotwire.turbo.easyrailsapp.databinding.NavButtonComponentBinding
import kotlinx.serialization.Serializable

class NavButtonComponent(
    name: String,
    private val delegate: BridgeDelegate<NavDestination>
) : BridgeComponent<NavDestination>(name, delegate) {

    private val navButtonItemId = 10
    private var navButtonMenuItem: MenuItem? = null
    private val fragment: Fragment
        get() = delegate.destination.fragment
    private val toolbar: Toolbar?
        get() = fragment.view?.findViewById(R.id.toolbar)

    override fun onReceive(message: Message) {
        if (message.event == "connect") {
            handleConnectEvent(message)
        } else {
            Log.w("TurboNative", "Unknown event for message: $message")
        }
    }

    private fun handleConnectEvent(message: Message) {
        val data = message.data<MessageData>() ?: return
        showToolbarButton(data)
    }

    private fun showToolbarButton(data: MessageData) {
        val menu = toolbar?.menu ?: return
        val inflater = LayoutInflater.from(fragment.requireContext())
        val binding = NavButtonComponentBinding.inflate(inflater)
        val order = 999 // Show as the right-most button

        binding.navButton.apply {
            text = data.title
            setOnClickListener {
                performAction()
            }
        }

        menu.removeItem(navButtonItemId)
        navButtonMenuItem = menu.add(Menu.NONE, navButtonItemId, order, data.title).apply {
            actionView = binding.root
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
    }

    private fun performAction(): Boolean {
        return replyTo("connect")
    }

    @Serializable
    data class MessageData(
        val title: String
    )
}
