package com.ivyapps.composematerial3helper.toolwindow

import com.ivyapps.composematerial3helper.domain.data.MaterialComponent
import com.ivyapps.composematerial3helper.domain.MaterialComponentsService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.profile.codeInspection.ui.addScrollPaneIfNecessary
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.content.ContentFactory

class MaterialComponentsWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val ui = MaterialComponentsUi(toolWindow)
        ui.navigateToMenu()
    }

    override fun shouldBeAvailable(project: Project) = true
}


class MaterialComponentsUi(private val toolWindow: ToolWindow) {
    private val contentFactory = ContentFactory.SERVICE.getInstance()
    private val service = toolWindow.project.service<MaterialComponentsService>()

    private val menuContent by lazy {
        contentFactory.createContent(
            ScrollPaneFactory.createScrollPane(
                ComponentsMenuUi(
                    service = service,
                    navigateToComponent = ::navigateToComponent
                ).ui()
            ),
            "Components",
            false,
        )
    }

    fun navigateToMenu() {
        toolWindow.contentManager.removeAllContents(true)
        toolWindow.contentManager.addContent(menuContent)
    }

    fun navigateToComponent(component: MaterialComponent) {
        toolWindow.contentManager.removeAllContents(true)
        toolWindow.contentManager.addContent(
            contentFactory.createContent(
                ScrollPaneFactory.createScrollPane(
                    ComponentDetailsUi(
                        navigateToMenu = ::navigateToMenu
                    ).ui(component)
                ),
                component.name,
                false
            )
        )
    }
}