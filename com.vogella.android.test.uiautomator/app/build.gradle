apply plugin: 'com.android.application'

android {
    compileSdkVersion 19
    buildToolsVersion "23.0.0"

    defaultConfig {
        applicationId "com.vogella.android.test.uiautomator"
        minSdkVersion 19
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    androidTestCompile 'com.android.support.test:runner:0.3'
    androidTestCompile 'junit:junit:4.12'
    testCompile 'junit:junit:4.12'
    androidTestCompile 'org.hamcrest:hamcrest-library:1.3'
    androidTestCompile 'com.android.support.test:runner:0.3'
    androidTestCompile 'com.android.support.test:rules:0.3'
    androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.1'
}
