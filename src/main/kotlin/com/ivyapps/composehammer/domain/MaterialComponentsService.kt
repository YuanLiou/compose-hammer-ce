package com.ivyapps.composehammer.domain

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.ivyapps.composehammer.domain.data.material3.MaterialComponent
import com.ivyapps.composehammer.domain.data.material3.MaterialComponentsGroup
import com.ivyapps.composehammer.m3content.animations
import com.ivyapps.composehammer.m3content.badges
import com.ivyapps.composehammer.m3content.bottomAppBar
import com.ivyapps.composehammer.m3content.bottomSheets
import com.ivyapps.composehammer.m3content.buttons
import com.ivyapps.composehammer.m3content.cards
import com.ivyapps.composehammer.m3content.checkboxes
import com.ivyapps.composehammer.m3content.chips
import com.ivyapps.composehammer.m3content.composeRuntime
import com.ivyapps.composehammer.m3content.datePickers
import com.ivyapps.composehammer.m3content.dialogs
import com.ivyapps.composehammer.m3content.dividers
import com.ivyapps.composehammer.m3content.fab
import com.ivyapps.composehammer.m3content.iconButtons
import com.ivyapps.composehammer.m3content.layouts
import com.ivyapps.composehammer.m3content.lists
import com.ivyapps.composehammer.m3content.menus
import com.ivyapps.composehammer.m3content.navigationBars
import com.ivyapps.composehammer.m3content.navigationDrawers
import com.ivyapps.composehammer.m3content.navigationRails
import com.ivyapps.composehammer.m3content.progressIndicators
import com.ivyapps.composehammer.m3content.quickUi
import com.ivyapps.composehammer.m3content.radioButtons
import com.ivyapps.composehammer.m3content.searchBars
import com.ivyapps.composehammer.m3content.sliders
import com.ivyapps.composehammer.m3content.snackbars
import com.ivyapps.composehammer.m3content.switches
import com.ivyapps.composehammer.m3content.tabs
import com.ivyapps.composehammer.m3content.textFields
import com.ivyapps.composehammer.m3content.timePickers
import com.ivyapps.composehammer.m3content.tooltips
import com.ivyapps.composehammer.m3content.topAppBars

@Service(Service.Level.PROJECT)
class MaterialComponentsService(
    project: Project
) {
    val content by lazy {
        buildList {
            quickUi()
            layouts()
            composeRuntime()
            animations()
            buttons()
            fab()
            iconButtons()
            textFields()
            cards()
            checkboxes()
            switches()
            radioButtons()
            sliders()
            bottomSheets()
            dialogs()
            badges()
            lists()
            dividers()
            chips()
            menus()
            topAppBars()
            tabs()
            navigationBars()
            navigationDrawers()
            navigationRails()
            bottomAppBar()
            progressIndicators()
            snackbars()
            datePickers()
            timePickers()
            searchBars()
            tooltips()
        }
    }

    fun findGroupByTitle(groupTitle: String): MaterialComponentsGroup =
        requireNotNull(
            content.find {
                it.title == groupTitle || it.shortTitle == groupTitle
            }
        ) {
            "Couldn't find '$groupTitle' group in $content!!!"
        }

    fun findComponentByNameInGroup(
        group: MaterialComponentsGroup,
        componentName: String
    ): MaterialComponent =
        requireNotNull(
            group.components.find {
                it.name == componentName || it.shortName == componentName
            }
        ) {
            "Couldn't find '$componentName' component in ${group.components}!!!"
        }
}
