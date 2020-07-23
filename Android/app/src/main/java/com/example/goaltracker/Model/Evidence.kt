package com.example.goaltracker.Model

import com.example.goaltracker.Model.User

data class Evidence(
    var author: User,
    var taggedPerson: User,
    var context: String,
    var timeStamp: String,
    var image: String,
    var seen: Boolean
)
{
}