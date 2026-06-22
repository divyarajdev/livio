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

plugins {
    `kotlin-dsl`
}

group = "io.github.divyarajdev.livio.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.ksp.gradle.plugin)
    implementation(libs.hilt.gradle.plugin)
    implementation(libs.spotless.gradle.plugin)
    implementation(libs.detekt.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "livio.android.application"
            implementationClass =
                "io.github.divyarajdev.livio.buildlogic.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "livio.android.library"
            implementationClass =
                "io.github.divyarajdev.livio.buildlogic.AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "livio.android.compose"
            implementationClass =
                "io.github.divyarajdev.livio.buildlogic.AndroidComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "livio.android.hilt"
            implementationClass =
                "io.github.divyarajdev.livio.buildlogic.AndroidHiltConventionPlugin"
        }
        register("kotlinJvm") {
            id = "livio.kotlin.jvm"
            implementationClass = "io.github.divyarajdev.livio.buildlogic.KotlinJvmConventionPlugin"
        }
        register("spotless") {
            id = "livio.spotless"
            implementationClass = "io.github.divyarajdev.livio.buildlogic.SpotlessConventionPlugin"
        }
        register("detekt") {
            id = "livio.detekt"
            implementationClass = "io.github.divyarajdev.livio.buildlogic.DetektConventionPlugin"
        }
    }
}
