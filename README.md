# KotlinHelper - Android Kotlin Extensions

[![](https://jitpack.io/v/tarifchakder/KotlinHelper.svg)](https://jitpack.io/#tarifchakder/KotlinHelper)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/guide/)
[![](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16#l16)
[![](https://img.shields.io/badge/Compiled%20API-31-blue.svg?style=flat)](https://developer.android.com/about/versions/12/setup-sdk)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.6.10-blue.svg)](https://kotlinlang.org)

## How to install

Add the following repository to your build.gradle:

```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Add the following dependency to your build.gradle:

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies 
        {
         ...
         def latestVersion = 1.1.4 // check latest version Jitpack badge
         
         // view 
         implementation "com.github.tarifchakder.KotlinHelper:view:$latestVersion'
   }
```

