# MercadoLibre Products (Android app made in Kotlin) #

## Description

This app let to the user find products in MercadoLibre and store it in a local database, when it is marked as favorite. Supports all coutries available in MercadoLibre.

## Preview ##

<img src="media/meli_1.gif" alt="Drawing" style="width: 200px;"/>
<img src="media/meli_2.gif" alt="Drawing" style="width: 200px;"/>

<img src="media/preview3.jpeg" alt="Drawing" style="width: 400px;"/>

## Main Features
- It has a search field to find products in Mercado Libre.
- App supports DarkMode
- User can mark favorite products to save it in a local database.
- Support live rotation without loosing data.
- Open product link to see in browser.

## General stack for this project: ##

- Dagger 2
- Navigation Components
- LiveData
- MVVM Architechture
- Room
- Retrofit
- Glide
- Coroutines

## Testing tools:

- Junit4
- Mockito

## Architecture implemented ##

For this project, a clean architecture was implemented based on the [Clean Architecture for Android](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started) reference. This implementation considers different levels represented by the following diagram:

![Taken from: "The “Real” Repository Pattern in Android"](media/Android-Clean-Architecture.png)

The "mercado-libre" app created, two modules are defined as follows:


- [core](/core): This module contains the domain and data layers, written purely in kotlin. These layers separated guarantee reused in other modules or projects without any dependency with the framework and upper layers.
  
- [app](/app): This module consumes the core module, using the corresponding android implementations, covering the framework and UI layers.

The interaction between each layer follows this diagram:

![Taken from: "The “Real” Repository Pattern in Android"](media/layers-architecture.png)

To guarantee the Dependency Inversion principle from SOLID, this project implements Dagger2 to delegate all of the injection operations.

## Navigation Graph ##

![](media/navigation.png)

## PR validations with Github Actions ##
Check [android_build.yml](.github/workflows/android_build.yml) to get more information about the container and jobs configurations:
![](media/githubactions.png)

![](media/githubactions2.png)

## References ##

RecyclerView:
- [RecyclerView 2020: a modern way of dealing with lists in Android using DataBinding](https://fraggjkee.medium.com/recyclerview-2020-a-modern-way-of-dealing-with-lists-in-android-using-databinding-d97abf5fb55f)
- [Getting screen width on API Level 30 (Android 11)](https://stackoverflow.com/questions/63407883/getting-screen-width-on-api-level-30-android-11-getdefaultdisplay-and-getme)
- [How to databinding image loading with Glide?](https://stackoverflow.com/questions/56889880/how-to-databinding-image-loading-with-glide)

Github Actions:
- [Setup CI For Android Apps Using GitHub Actions // Android CI Tutorial](https://www.youtube.com/watch?v=K9w01h4-Wnc)

Coroutines:
- [coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)

Retrofit:
- [Using Retrofit with Kotlin Coroutines in Android](https://blog.mindorks.com/using-retrofit-with-kotlin-coroutines-in-android)

Room:
- [Save data in a local database using Room](https://developer.android.com/training/data-storage/room)
- [Test and debug your database](https://developer.android.com/training/data-storage/room/testing-db)

Architechture:
- [Clean Architecture Tutorial for Android: Getting Started](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started)
- [Injecting Android ViewModels With Dagger2 in a Clean Architecture](https://betterprogramming.pub/injecting-android-viewmodels-with-dagger2-in-clean-architecture-744c1fe81530)
- [Android Dagger 2 Injection For Fragment (Kotlin)](https://code.luasoftware.com/tutorials/android/dagger2-injection-for-fragment/)
- [Create a complete Android app from scratch ~ Retrofit - Dagger2 - Room - MVVM and LiveData](https://nsaveek.medium.com/create-a-complete-android-app-from-scratch-retrofit-dagger2-room-mvvm-and-livedata-92052987ff59)
- [The “Real” Repository Pattern in Android](https://proandroiddev.com/the-real-repository-pattern-in-android-efba8662b754)
- [Injecting ViewModel — hard to easy](https://medium.com/mobile-app-development-publication/injecting-viewmodel-from-hard-to-easy-c06c0fe1c8e9)

ViewModel:
- [Should I include LifecycleOwner in ViewModel?](https://stackoverflow.com/questions/48396092/should-i-include-lifecycleowner-in-viewmodel)

Navigation:
- [Get started with the Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Pass data between destinations](https://developer.android.com/guide/navigation/navigation-pass-data)
- [Using Safe Args With the Android Navigation Component](https://www.raywenderlich.com/19327407-using-safe-args-with-the-android-navigation-component)
- [Beginner’s Guide to Bottom Navigation with Android Jetpack](https://medium.com/android-news/beginners-guide-to-bottom-navigation-with-android-jetpack-5485d2b8bbb5)

Testing Frameworks:
- [How to mock final classes on Kotlin using Mockito 2 (KAD 23)](https://antonioleiva.com/mockito-2-kotlin/)
- [Mocking Coroutines](https://proandroiddev.com/mocking-coroutines-7024073a8c09)
- [How to verify a method is called two times with mockito verify](https://stackoverflow.com/questions/14889951/how-to-verify-a-method-is-called-two-times-with-mockito-verify)

## Author
Alejandro Daniel José Gómez Flórez (@aldajo92)