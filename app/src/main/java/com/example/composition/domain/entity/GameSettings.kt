package com.example.composition.domain.entity

data class GameSettings(
    val maxSunValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
)