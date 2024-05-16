package com.app.pixabay.android.presenter.detail

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.app.pixabay.android.navigator.NavigationDestination

object SearchDetailDestination : NavigationDestination {
    const val RESULT_ID = "result_id"

    private const val DESTINATION_NAME = "SearchDetail"

    private const val DESTINATION_DETAIL_PARAMS =
        "$DESTINATION_NAME/{$RESULT_ID}"

    override fun route() = DESTINATION_DETAIL_PARAMS

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(RESULT_ID) { type = NavType.StringType },
        )

    fun createDetailPath(resultID: String): String {
        return "$DESTINATION_NAME/$resultID"
    }

}