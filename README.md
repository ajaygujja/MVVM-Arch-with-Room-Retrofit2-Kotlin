## Kotlin MVVM Architecture

A sample app that display list of Random User. The purpose of this project to illustrate the usage of MVVM architecture design pattern that follow the best practices of Object Oriented Design Patterns using the following technology stack.

1. Architecture Design Pattern
2. MVVM
3. Live Data
4. Room Database
5. Retrofit
6. Repository Pattern
7. AndroidX
8. Glide
9. Github User API
10. JetPack Libraries

## ScreenShots
<div align:left;display:inline;>
<img width="200" height="400" src="https://github.com/ajaygujja/MVVM-Arch-with-Room-Retrofit2-Kotlin/blob/master/Screens/list1.png" />
<img width="200" height="400" src="https://github.com/ajaygujja/MVVM-Arch-with-Room-Retrofit2-Kotlin/blob/master/Screens/list2.png" />
<img width="200" height="400" src="https://github.com/ajaygujja/MVVM-Arch-with-Room-Retrofit2-Kotlin/blob/master/app/src/main/res/mipmap-xxhdpi/detail_1.png" />
<img width="200" height="400" src="https://github.com/ajaygujja/MVVM-Arch-with-Room-Retrofit2-Kotlin/blob/master/Screens/Create_User_1.png" />
</div>

## Architecture
![Architecture_design_new](/app/src/main/res/mipmap-xxhdpi/Architecture_design_new.png)

## Libraries

### Android Jetpack

* [Lifecycle: Create a UI that automatically responds to lifecycle events.](https://developer.android.com/topic/libraries/architecture/lifecycle)

* [LiveData: Build data objects that notify views when the underlying database changes.](https://developer.android.com/topic/libraries/architecture/livedata)

* [ViewModel: Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.](https://developer.android.com/topic/libraries/architecture/viewmodel)

* [Room: Access your app's SQLite database with in-app objects and compile-time checks.](https://developer.android.com/topic/libraries/architecture/room)

* [Navigation: Handle everything needed for in-app navigation.](https://developer.android.com/topic/libraries/architecture/navigation/)

### Http

* [Retrofit2: Type-safe HTTP client for Android and Java by Square, Inc.](https://github.com/square/retrofit)


### Others

* [Glide: An image loading and caching library for Android focused on smooth scrolling](https://github.com/bumptech/glide)


## Usage

Fork this repo directly:

```shell
$ git clone https://github.com/ajaygujja/MVVM-Arch-with-Room-Retrofit2-Kotlin.git
```

## Author
[Ajay Gujja](https://github.com/ajaygujja "Ajay Gujja")


## Sources
 1. [Google's official doc](https://developer.android.com/jetpack/docs/guide)
 2. [Google's Codelab](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/#0)
 2. [Architecture Components Samples](https://github.com/android/architecture-components-samples/tree/88747993139224a4bb6dbe985adf652d557de621)
 3. [Jose Alcérreca's post at Medium](https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7)