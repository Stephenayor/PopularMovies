package com.example.movies.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TrailersResult(
    @SerializedName("id")
        val id: Int,
    @SerializedName("results")
        val results: List<Trailer>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            TODO("results")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrailersResult> {
        override fun createFromParcel(parcel: Parcel): TrailersResult {
            return TrailersResult(parcel)
        }

        override fun newArray(size: Int): Array<TrailersResult?> {
            return arrayOfNulls(size)
        }
    }
}

data class Trailer(
    @SerializedName("id")
    val id: String?,
    @SerializedName("iso_3166_1")
    val iso_3166_1: String,
    @SerializedName("iso_639_1")
    val iso_639_1: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(iso_3166_1)
        parcel.writeString(iso_639_1)
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(site)
        parcel.writeInt(size)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trailer> {
        override fun createFromParcel(parcel: Parcel): Trailer {
            return Trailer(parcel)
        }

        override fun newArray(size: Int): Array<Trailer?> {
            return arrayOfNulls(size)
        }
    }
}