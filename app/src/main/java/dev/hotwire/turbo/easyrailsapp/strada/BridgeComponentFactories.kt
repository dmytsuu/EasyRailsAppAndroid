package dev.hotwire.turbo.easyrailsapp.strada

import dev.hotwire.strada.BridgeComponentFactory

val bridgeComponentFactories = listOf(
    BridgeComponentFactory("form", ::FormComponent),
    BridgeComponentFactory("nav-button", ::NavButtonComponent),
    BridgeComponentFactory("flash-message", ::FlashMessageComponent),
    BridgeComponentFactory("menu", ::MenuComponent)
)
