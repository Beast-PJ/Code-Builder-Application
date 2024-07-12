plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.codebuilder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.codebuilder"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
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
    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "30.0.2"

}

dependencies {
    var lifecycle_version = "2.2.0"
    var arch_version = "2.1.0"
    var room_version = "2.2.5"
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation ("com.karumi:dexter:6.2.3")
    implementation ("com.airbnb.android:lottie:4.2.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("com.google.firebase:firebase-firestore:24.10.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    // Annotation processor
    annotationProcessor ("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")

    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")
    // optional - Test helpers for LiveData
    testImplementation ("androidx.arch.core:core-testing:$arch_version")

    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Material Design Components
}