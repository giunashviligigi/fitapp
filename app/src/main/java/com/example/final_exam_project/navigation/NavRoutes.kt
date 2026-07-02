package com.example.final_exam_project.navigation

object NavRoutes {
    const val EDIT_ID_ARG = "editId"

    fun workoutsEdit(editId: Long): String = "${BottomNavItem.Workouts.route}/$editId"

    fun workoutsEditPattern(): String = "${BottomNavItem.Workouts.route}/{$EDIT_ID_ARG}"

    fun matchesBottomNavTab(destinationRoute: String?, tabRoute: String): Boolean {
        if (destinationRoute == null) return false
        return destinationRoute == tabRoute || destinationRoute.startsWith("$tabRoute/")
    }
}
