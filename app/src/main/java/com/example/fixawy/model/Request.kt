package com.example.fixawy.model

import android.os.Parcel
import android.os.Parcelable

class Request(
    var id: String?,
    var status: Int?,
    var fixer: Fixer?,
    var date: String?,
    var price: String?,
    var client: Client?
) : Parcelable{
    companion object {
        const val ACCEPTED_STATUS = 0
        const val PENDING_STATUS = 1
        const val PAST_MODE = 0
        const val ACTIVE_MODE = 1
        const val FIXER_ACCEPTED_MODE = 2
        const val FIXER_PENDING_MODE = 3
        const val FIXER_PAST_MODE = 4
        @JvmField
        val CREATOR: Parcelable.Creator<Request> = object : Parcelable.Creator<Request>{
            override fun createFromParcel(parcel: Parcel): Request {
                return Request(parcel)
            }

            override fun newArray(size: Int): Array<Request?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readParcelable(Fixer::class.java.classLoader),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? String,
        parcel.readParcelable(Client::class.java.classLoader)
    ) {
    }

    constructor(id: String, fixer: Fixer, date: String, client: Client?) : this(
        id,
        null,
        fixer,
        date,
        "0",
        client
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeValue(status)
        parcel.writeParcelable(fixer, flags)
        parcel.writeString(date)
        parcel.writeValue(price)
    }

    override fun describeContents(): Int {
        return 0
    }




}
