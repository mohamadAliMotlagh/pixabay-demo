package com.app.pixabay.android.navigator

import android.os.Bundle
import androidx.navigation.NavOptionsBuilder

/**
 * Created by funkymuse on 6/25/21 to long live and prosper !
 */
sealed class NavigatorEvent {
    object NavigateUp : NavigatorEvent()

    class Directions(
        val destination: String,
        val builder: NavOptionsBuilder.() -> Unit,
    ) : NavigatorEvent()

    object PopBackStack : NavigatorEvent()

    data class PopBackStackWithData(val bundle: Bundle) : NavigatorEvent()
}
