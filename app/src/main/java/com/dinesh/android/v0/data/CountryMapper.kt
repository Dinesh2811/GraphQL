package com.dinesh.android.v0.data

import com.dinesh.GetCountriesQuery
import com.dinesh.GetCountryQuery
import com.dinesh.android.v0.model.DetailedCountry
import com.dinesh.android.v0.model.SimpleCountry

fun GetCountryQuery.Country.toDetailedCountry() : DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",
        currency = currency ?: "No Currency",
        languages = languages.map { it.name },
        continent = continent.name
    )
}

fun GetCountriesQuery.Country.toSimpleCountry() : SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital"
    )
}