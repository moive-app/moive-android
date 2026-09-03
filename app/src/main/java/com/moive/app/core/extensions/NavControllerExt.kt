package com.moive.app.core.extensions

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions

/**
 * 백스택 완전 초기화 및 루트 이동
 * 상태 저장 및 복원 X, 이전 상태 초기화
 */
fun NavController.clearBackStackNavOptions() = navOptions {
    popUpTo(0) {
        inclusive = true
    }
    launchSingleTop = true
}

/**
 * 백스택 초기화, 이전 Destination의 상태 저장 및 복원
 */
fun NavController.clearBackStackWithRestoreNavOptions() = navOptions {
    popUpTo(0) {
        saveState = true
        inclusive = true
    }
    restoreState = true
}
