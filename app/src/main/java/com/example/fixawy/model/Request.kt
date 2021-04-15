package com.example.fixawy.model

import android.os.Parcel
import android.os.Parcelable

class Request() : Parcelable{
    companion object {
        const val ACCEPTED_STATUS = 4
        const val PENDING_STATUS = 3
        const val PAST_MODE = 0
        const val ACTIVE_MODE = 1
        const val FIXER_PENDING_MODE = 2
        const val FIXER_ACCEPTED_MODE = 3
        const val FIXER_PAST_MODE = 4

        @JvmField  val CREATOR : Parcelable.Creator<Request> = object : Parcelable.Creator<Request> {
            override fun createFromParcel(parcel: Parcel): Request {
                return Request(parcel)
            }

            override fun newArray(size: Int): Array<Request?> {
                return arrayOfNulls(size)
            }
        }

    }
    var id: Int? = 0
    var status: Int? = 0
    var fixer: Fixer? = null
    var date: String? = ""
    var price: String? = ""
    var client: Client? = null
    var subDepartmentId : Int? = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        status = parcel.readValue(Int::class.java.classLoader) as? Int
        fixer = parcel.readParcelable(Fixer::class.java.classLoader)
        date = parcel.readString()
        price = parcel.readString()
        client = parcel.readParcelable(Client::class.java.classLoader)
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeValue(id)
        dest?.writeValue(status)
        dest?.writeParcelable(fixer, flags)
        dest?.writeString(date)
        dest?.writeString(price)
        dest?.writeParcelable(client, flags)
    }

    override fun equals(other: Any?): Boolean {
        var next = other as Request
        return next.id == id
    }

}
