package com.example.membershipform

data class UserData(var id: String,var pw: String,var name: String,var age: String = "20",var mbti: String = "ESTJ",var gender: String ="남성")