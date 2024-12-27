plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    id("kotlin-parcelize")
}

android {
    compileSdkVersion = "android-35"
    buildToolsVersion = "35.0.0"
    namespace = "com.shreyas.go_train_schedule"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shreyas.go_train_schedule"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    kapt {
        generateStubs = true
        correctErrorTypes = true
        javacOptions {
            option("-Adagger.fastInit=enabled")
        }
    }
    lint {
        warningsAsErrors = true
        abortOnError = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    sourceSets {
        getByName("main").java.srcDir("src/main/java")
        getByName("test").java.srcDirs("src/test/java")
        getByName("test").resources.srcDirs("src/test/assets")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.compose.activities)
    implementation(libs.compose.material.design)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.animations)
    implementation(libs.compose.tooling)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.viewmodels)
    implementation(libs.compose.navigation)
    implementation(libs.compose.livedata)
    implementation(libs.compose.ui.tests)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    kapt(libs.dagger.processor)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.coroutine.adapter)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // OkHttp Logging
    implementation(libs.okhttp.logging.interceptor)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.androidx.arch.core.testing)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.intents)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.espresso.idling.resources)
    androidTestImplementation(libs.androidx.testing)
    androidTestImplementation(libs.compose.ui.android.tests)
    androidTestImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockito.inline)
    androidTestImplementation(libs.mockito.kotlin)
}