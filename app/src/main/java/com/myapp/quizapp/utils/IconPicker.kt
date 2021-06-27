package com.myapp.quizapp.utils

import com.myapp.quizapp.R

object IconPicker {
        val icon = arrayOf(

                R.drawable.icon_2,
                R.drawable.icon_1,
                R.drawable.logo

                )
        var currenticonIndex = 0

        fun geticon(): Int {
            currenticonIndex = (currenticonIndex + 1) % icon.size
            return icon[currenticonIndex]
        }
    }

