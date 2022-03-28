package com.example.movies.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PopularMovies (

    @SerializedName
        ("page") var page : Int? = null,
    @SerializedName
        ("results") var results : ArrayList<MovieResult> = arrayListOf(),
    @SerializedName
        ("total_pages") var totalPages : Int? = null,
    @SerializedName
        ("total_results") var totalResults : Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        TODO("results"),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(page)
        parcel.writeValue(totalPages)
        parcel.writeValue(totalResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PopularMovies> {
        override fun createFromParcel(parcel: Parcel): PopularMovies {
            return PopularMovies(parcel)
        }

        override fun newArray(size: Int): Array<PopularMovies?> {
            return arrayOfNulls(size)
        }
    }
}