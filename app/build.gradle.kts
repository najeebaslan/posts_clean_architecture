
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0"


}

android {
    namespace = "com.najeeb.income_expense_tracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.najeeb.income_expense_tracker"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.activity:activity-compose:1.8.1")
    // Consolidate Compose BOMs to avoid conflicts and simplify updates
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00") // <--- Update to a newer BOM version
    implementation(composeBom)
    androidTestImplementation(composeBom)
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Lifecycle
    val lifecycleVersion = "2.7.0"
    val lifecycleRuntime = "2.8.6"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntime")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Saved State
    implementation ("androidx.savedstate:savedstate:1.2.1")
    implementation("androidx.core:core-ktx:1.9.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.2")

    // Local Storage (Room)
    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")
    kapt("androidx.room:room-compiler:2.7.1")

    // Graphics and Preview (already in BOM, but explicit is fine)
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}