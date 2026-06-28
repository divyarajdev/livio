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

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Livio"

include(":app")

include(
    ":core:common",
    ":core:config",
    ":core:model",
    ":core:mvi",
    ":core:domain",
    ":core:data",
    ":core:network",
    ":core:database",
    ":core:datastore",
    ":core:sync",
    ":core:backup",
    ":core:designsystem",
    ":core:ui",
    ":core:navigation",
    ":core:media",
    ":core:localization",
    ":core:permissions",
    ":core:analytics",
    ":core:accessibility",
    ":core:logging",
    ":core:crash",
    ":core:observability",
    ":core:diagnostics",
    ":core:performance",
    ":core:security",
    ":core:privacy",
    ":core:featureflag",
    ":core:update",
    ":core:integrity",
    ":core:notifications",
    ":core:legal",
    ":core:testing",
)

include(
    ":feature:onboarding",
    ":feature:auth",
    ":feature:home",
    ":feature:profile",
    ":feature:settings",
    ":feature:search",
    ":feature:saved",
    ":feature:users",
    ":feature:products",
    ":feature:carts",
    ":feature:orders",
    ":feature:recipes",
    ":feature:posts",
    ":feature:comments",
    ":feature:todos",
    ":feature:quotes",
    ":feature:developer",
)
