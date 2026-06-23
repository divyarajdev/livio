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

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.spotless.LineEnding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(pluginId(CatalogPlugins.SPOTLESS))
            val ktfmtVersion = versionString(CatalogVersions.KTFMT)

            extensions.configure<SpotlessExtension> {
                lineEndings = LineEnding.UNIX

                kotlin {
                    target(
                        rootProject.fileTree(rootProject.rootDir) {
                            include("**/*.kt")
                            exclude(commonBuildExcludes + "config/spotless/copyright.kt")
                        }
                    )
                    ktfmt(ktfmtVersion).kotlinlangStyle()
                    licenseHeaderFile(rootProject.file("config/spotless/copyright.kt"))
                    toggleOffOn()
                }

                kotlinGradle {
                    target(
                        rootProject.fileTree(rootProject.rootDir) {
                            include("**/*.gradle.kts")
                            exclude(commonBuildExcludes)
                        }
                    )
                    ktfmt(ktfmtVersion).kotlinlangStyle()
                    licenseHeaderFile(
                        rootProject.file("config/spotless/copyright.kt"),
                        "(^(?![\\/ ]\\*).*$)",
                    )
                    toggleOffOn()
                }

                format("xml") {
                    target(
                        rootProject.fileTree(rootProject.rootDir) {
                            include("**/*.xml")
                            exclude(commonBuildExcludes + ".idea/**")
                        }
                    )
                    trimTrailingWhitespace()
                    endWithNewline()
                }

                format("misc") {
                    target(
                        rootProject.fileTree(rootProject.rootDir) {
                            include(
                                "*.md",
                                ".gitignore",
                                "*.toml",
                                "*.yml",
                                "*.yaml",
                                "config/**/*.yml",
                                "config/**/*.yaml",
                                "gradle/**/*.toml",
                            )
                            exclude(commonBuildExcludes + "gradle/wrapper/**")
                        }
                    )
                    trimTrailingWhitespace()
                    endWithNewline()
                }
            }
        }
    }

    private companion object {
        val commonBuildExcludes =
            listOf(
                ".gradle/**",
                "build/**",
                "**/.gradle/**",
                "**/build/**",
            )
    }
}
