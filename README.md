# Rick-and-Morty-Kotlin

En esta ocasión, he creado una mini app de Rick & Morty con Kotlin, en esta app se puede crear un perfil, gracias a SharedPreferences, también gracias al API de Rick y Morty.

https://rickandmortyapi.com/

He llamado a esta API con Retrofit2

//Retrofit2
implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
implementation "com.squareup.retrofit2:converter-moshi:$retrofit2_version"
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"

En esta lista he puesto una opción de Favorito que puedes marcar de cualquier personaje y la app guarda cuál es tu personaje favorito marcando la estrella y con una opción en la pantalla principal que puede visualizar con mas amplitud tu personaje favorito.

En este proyecto he utilizado una arquitectura MVVM, añadiendo un modulo de :data, para añadir el repository, remote, models...

Toda la app esta enlazada con un navigation alojado en el HomeActivity, que puede acceder a todas las pantallas de la app.

Librerias implementadas en esta aplicación:

//Glide
implementation 'com.github.bumptech.glide:glide:4.12.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

//Timber
implementation 'com.jakewharton.timber:timber:4.7.1'

//CircleImageView
implementation 'de.hdodenhof:circleimageview:3.1.0'

// KTX
implementation 'androidx.core:core-ktx:1.3.2'

//Navigation
implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
implementation "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"

// Jetpack Compose Integration
implementation "androidx.navigation:navigation-compose:1.0.0-alpha04"

//Koin
implementation "org.koin:koin-core:$koin_version"
implementation "org.koin:koin-androidx-scope:$koin_version"
implementation "org.koin:koin-androidx-viewmodel:$koin_version"
implementation 'androidx.legacy:legacy-support-v4:1.0.0'
testImplementation "org.koin:koin-test:$koin_version"

//Moshi
implementation "com.squareup.moshi:moshi-kotlin:$moshi_version"
kapt "com.squareup.moshi:moshi-kotlin-codegen:$moshi_version"

implementation "com.google.code.gson:gson:$gson_version"

//Okhttp3
implementation "com.squareup.okhttp3:okhttp:$okhttp3_version"
implementation "com.squareup.okhttp3:logging-interceptor:$okhttp3_version"

//Retrofit2
implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
implementation "com.squareup.retrofit2:converter-moshi:$retrofit2_version"
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"

//Kotlin Coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"

//Lifecycle
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

//Anko
implementation "org.jetbrains.anko:anko-commons:$anko_version"

// Testing
testImplementation 'junit:junit:4.13.1'
androidTestImplementation 'androidx.test.ext:junit:1.1.2'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
androidTestImplementation "androidx.navigation:navigation-testing:$nav_version"

Espero que guste mi pequeño proyecto de prueba, cualquier sugerencia sería muy bueno.
