# View

[![](https://jitpack.io/v/tarifchakder/KotlinHelper.svg)](https://jitpack.io/#tarifchakder/KotlinHelper)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.6.10-blue.svg)](https://kotlinlang.org)

## How to install

Add the following repository to your build.gradle:

```gradle
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
         implementation "com.github.tarifchakder.KotlinHelper:view:$latestVersion"
         
       
   }
```

