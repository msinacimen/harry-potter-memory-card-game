package com.proje3.harrypottermemorymaster

import android.graphics.Bitmap
import android.widget.ImageButton
import android.widget.ImageView

data class CardClass(
    val button: ImageButton,
    val id: Int,
    val name: String,
    val house: String,
    val score: Int,
    val image: Bitmap,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false,
)

data class CardClassWithoutButton(
    val id: Int,
    val name: String,
    val house: String,
    val score: Int,
    val image: String,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false,
)