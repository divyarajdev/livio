/*
 * Copyright (C) 2026 Divyaraj D
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.divyarajdev.livio.buildlogic

internal object CatalogPlugins {
    const val ANDROID_APPLICATION = "android-application"
    const val ANDROID_LIBRARY = "android-library"
    const val DETEKT = "detekt"
    const val HILT = "hilt"
    const val KOTLIN_COMPOSE = "kotlin-compose"
    const val KOTLIN_JVM = "kotlin-jvm"
    const val KSP = "ksp"
    const val SPOTLESS = "spotless"
}

internal object CatalogLibraries {
    const val ANDROIDX_COMPOSE_BOM = "androidx-compose-bom"
    const val ANDROIDX_COMPOSE_FOUNDATION = "androidx-compose-foundation"
    const val ANDROIDX_COMPOSE_MATERIAL3 = "androidx-compose-material3"
    const val ANDROIDX_COMPOSE_MATERIAL3_ADAPTIVE = "androidx-compose-material3-adaptive"
    const val ANDROIDX_COMPOSE_MATERIAL3_ADAPTIVE_LAYOUT =
        "androidx-compose-material3-adaptive-layout"
    const val ANDROIDX_COMPOSE_MATERIAL3_ADAPTIVE_NAVIGATION =
        "androidx-compose-material3-adaptive-navigation"
    const val ANDROIDX_COMPOSE_MATERIAL3_ADAPTIVE_NAVIGATION_SUITE =
        "androidx-compose-material3-adaptive-navigation-suite"
    const val ANDROIDX_COMPOSE_MATERIAL3_WINDOW_SIZE = "androidx-compose-material3-window-size"
    const val ANDROIDX_COMPOSE_UI = "androidx-compose-ui"
    const val ANDROIDX_COMPOSE_UI_GRAPHICS = "androidx-compose-ui-graphics"
    const val ANDROIDX_COMPOSE_UI_TOOLING = "androidx-compose-ui-tooling"
    const val ANDROIDX_COMPOSE_UI_TOOLING_PREVIEW = "androidx-compose-ui-tooling-preview"
    const val HILT_ANDROID = "hilt-android"
    const val HILT_COMPILER = "hilt-compiler"
}

internal object CatalogVersions {
    const val COMPILE_SDK = "compileSdk"
    const val KTFMT = "ktfmt"
    const val MIN_SDK = "minSdk"
    const val TARGET_SDK = "targetSdk"
}
