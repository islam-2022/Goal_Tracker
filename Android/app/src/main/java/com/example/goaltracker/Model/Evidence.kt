package com.example.goaltracker.Model

import com.example.goaltracker.Model.User
import com.google.gson.annotations.SerializedName

data class Evidence(
    var author: String,
    var taggedPerson: String,
    var context: String,
	@SerializedName("time")
    var timeStamp: String,
    var image: String,
    var seen: Boolean
)
{
}
