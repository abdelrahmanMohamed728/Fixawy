package com.example.fixawy.home.fixer_profile.edit_profile

import android.os.Parcelable
import com.example.fixawy.model.User

interface UpdateProfile : Parcelable {

  fun updateProfile(user: User)
}
