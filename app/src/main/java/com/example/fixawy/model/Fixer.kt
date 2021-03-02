package com.example.fixawy.model

import android.os.Parcel
import android.os.Parcelable

class Fixer(
     idFixer : Int?,
     nameFixer : String?,

):Parcelable , User(idFixer!!,nameFixer!!){
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
    )
    var email : String? = null
    var city : City? = null
    var image : String? = ""
    var rating : Int? = 0
    var job : Job? = null
    var password : String? = ""
    var mobile : String? = ""

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id!!)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fixer> {
        override fun createFromParcel(parcel: Parcel): Fixer {
            return Fixer(parcel)
        }

        override fun newArray(size: Int): Array<Fixer?> {
            return arrayOfNulls(size)
        }
    }
}
