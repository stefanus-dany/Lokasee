package com.bangkit.lokasee.data.response

import android.os.Parcelable
import com.bangkit.lokasee.data.User
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LogoutResponse(
	@field:SerializedName("message")
	val message: String
) : Parcelable