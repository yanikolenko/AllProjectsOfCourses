package com.yaroslavnikolenko.p91project

import android.os.Parcel
import android.os.Parcelable

@Parcelize
data class InfoAboutCountry(val name: String, val flag: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(flag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InfoAboutCountry> {
        override fun createFromParcel(parcel: Parcel): InfoAboutCountry {
            return InfoAboutCountry(parcel)
        }

        override fun newArray(size: Int): Array<InfoAboutCountry?> {
            return arrayOfNulls(size)
        }
    }
}

annotation class Parcelize
