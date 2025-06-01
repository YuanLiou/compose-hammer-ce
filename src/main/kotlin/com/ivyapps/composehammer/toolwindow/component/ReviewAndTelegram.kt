package com.ivyapps.composehammer.toolwindow.component

import com.intellij.icons.AllIcons
import com.intellij.ui.dsl.builder.Panel

fun Panel.reviewAndTelegramPrompt(indent: Boolean) {
    group(indent = indent) {
        row {
            icon(AllIcons.General.Balloon)
            label("Like the plugin?").bold()
        }
        row {
            text("Help us make it better!")
        }
        row {
            label("Give us feedback")
            browserLink(
                "Review Compose Hammer",
                "https://plugins.jetbrains.com/plugin/21912-compose-hammer/reviews"
            ).bold()
        }
    }
}
