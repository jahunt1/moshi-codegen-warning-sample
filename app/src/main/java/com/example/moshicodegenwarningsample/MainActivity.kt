package com.example.moshicodegenwarningsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {
    val moshi = Moshi.Builder()
        .addPolymorphicJsonAdapters()
        .add(KotlinJsonAdapterFactory())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
