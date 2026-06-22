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

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.pluginId(alias: String): String =
    libs
        .findPlugin(alias)
        .orElseThrow { IllegalArgumentException("Missing plugin alias: $alias") }
        .get()
        .pluginId

internal fun Project.library(alias: String): Provider<MinimalExternalModuleDependency> =
    libs.findLibrary(alias).orElseThrow {
        IllegalArgumentException("Missing library alias: $alias")
    }

internal fun Project.versionInt(alias: String): Int =
    libs
        .findVersion(alias)
        .orElseThrow { IllegalArgumentException("Missing version alias: $alias") }
        .requiredVersion
        .toInt()

internal fun Project.versionString(alias: String): String =
    libs
        .findVersion(alias)
        .orElseThrow { IllegalArgumentException("Missing version alias: $alias") }
        .requiredVersion

internal fun Project.configureAndroidApplication(extension: ApplicationExtension) {
    extension.compileSdk = versionInt("compileSdk")
    extension.defaultConfig {
        minSdk = versionInt("minSdk")
        targetSdk = versionInt("targetSdk")
    }
    extension.compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

internal fun Project.configureAndroidLibrary(extension: LibraryExtension) {
    extension.compileSdk = versionInt("compileSdk")
    extension.defaultConfig {
        minSdk = versionInt("minSdk")
    }
    extension.compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

internal fun Project.configureKotlinAndroid() {
    extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(17)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}
