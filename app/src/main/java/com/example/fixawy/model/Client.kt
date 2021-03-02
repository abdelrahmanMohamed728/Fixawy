package com.example.fixawy.model

import android.os.Parcel
import android.os.Parcelable

class Client(idClient : Int, nameClient : String) : User(idClient,nameClient) , Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    var city : City? = null
    var mobile : String? = ""
    var email : String? = ""
    var password : String? = ""
    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Client> {
        override fun createFromParcel(parcel: Parcel): Client {
            return Client(parcel)
        }

        override fun newArray(size: Int): Array<Client?> {
            return arrayOfNulls(size)
        }
    }

}