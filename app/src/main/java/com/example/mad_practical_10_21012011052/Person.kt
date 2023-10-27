package com.example.mad_practical_10_21012011052

import java.io.Serializable

    class Person(
        var id : String,
        var name : String,
        var emailid: String,
        var phoneNo: String,
        var address: String,
        var latitude: Double,
        var longitude: Double) :Serializable {

    }

