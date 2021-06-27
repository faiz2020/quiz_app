package com.myapp.quizapp.utils

object Colorpicker {
    val colors = arrayOf("#800000","#B048B5","#CD5C5C", "#808000", "#FFC0CB", "#ADD8E6", "#FFA500", "#00FF00", "#C88141", "#493D26", "#A0522D")
    var currentcolorIndex = 0

    fun getcolor(): String {
        currentcolorIndex = (currentcolorIndex + 1) % colors.size
        return colors[currentcolorIndex]
    }
}