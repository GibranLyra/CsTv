@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.gibranlyra.cstv"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.gibranlyra.cstv"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "TOKEN", "\"v_A9LyMTewYfR66oruNnPvKovpglfhwE26arqnzFvGI9-YqqPGE\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.coroutines)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.bundles.coil)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.compose)
    implementation(libs.hilt)
    implementation(libs.hilt.compose)
    implementation(libs.android.paging)
    implementation(libs.android.paging.compose)
    implementation(libs.hilt.android.testing)
    implementation(libs.material.pulltorefresh)
    implementation(libs.core.splashscreen)
    implementation(libs.threetenbp)
    kapt(libs.hilt.compiler)
    kapt(libs.android.hilt.compiler)
    annotationProcessor(libs.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit.ktx)
    testImplementation(libs.ui.test.junit4)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent.jvm)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.android.arch.core.testing)
    testImplementation(libs.roboletric)
    testImplementation(libs.turbine)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.kotlin.serialization.converter)
    lintChecks(libs.compose.lint.checks)
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = false
}
