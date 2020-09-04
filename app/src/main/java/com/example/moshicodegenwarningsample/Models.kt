package com.example.moshicodegenwarningsample

import com.example.moshicodegenwarningsample.Frequency.DAILY
import com.example.moshicodegenwarningsample.Frequency.EVERY_OTHER_WEEK
import com.example.moshicodegenwarningsample.Frequency.MONTHLY
import com.example.moshicodegenwarningsample.Frequency.WEEKLY
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import java.io.Serializable

enum class Frequency {
    @Json(name = "DAILY")
    DAILY,

    @Json(name = "WEEKLY")
    WEEKLY,

    @Json(name = "BI_WEEKLY")
    EVERY_OTHER_WEEK,

    @Json(name = "MONTHLY")
    MONTHLY
}

sealed class FrequencySelection : Serializable {
    abstract val id: Frequency
}

@JsonClass(generateAdapter = true)
data class FrequencySelectionDaily(
    @Transient override val id: Frequency = DAILY
) : FrequencySelection(), Serializable

@JsonClass(generateAdapter = true)
data class FrequencySelectionWeekly(
    @Transient override val id: Frequency = WEEKLY,
    val dayOfWeek: String
) : FrequencySelection(), Serializable

@JsonClass(generateAdapter = true)
data class FrequencySelectionEveryOtherWeek(
    @Transient override val id: Frequency = EVERY_OTHER_WEEK,
    val dayOfWeek: String
) : FrequencySelection(), Serializable

@JsonClass(generateAdapter = true)
data class FrequencySelectionMonthly(
    @Transient override val id: Frequency = MONTHLY,
    val dayOfTheMonth: String
) : FrequencySelection(), Serializable

fun frequencySelectionAdapter(builder: Moshi.Builder) {
    val factory = PolymorphicJsonAdapterFactory.of(FrequencySelection::class.java, "id")
        .withSubtype(FrequencySelectionDaily::class.java, "DAILY")
        .withSubtype(FrequencySelectionWeekly::class.java, "WEEKLY")
        .withSubtype(FrequencySelectionEveryOtherWeek::class.java, "EVERY_OTHER_WEEK")
        .withSubtype(FrequencySelectionMonthly::class.java, "MONTHLY")
    builder.add(factory)
}

fun Moshi.Builder.addPolymorphicJsonAdapters(): Moshi.Builder {
    // other types would go here
    frequencySelectionAdapter(this)
    return this
}
