package com.proje3.harrypottermemorymaster

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Base64
import android.util.TypedValue
import android.view.View
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import kotlin.concurrent.timerTask

fun decodeImage(imageString: String): Bitmap {
    val decodedString = Base64.decode(imageString, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}

class ImagesHolder {
    fun addImages(toTypedArray: Array<String>) {
        for (i in 0..toTypedArray.size - 1) {
            images.add(toTypedArray[i])
        }
    }

    operator fun get(i: Int): String {
        return images[i]
    }

    companion object {
        var images: ArrayList<String> = ArrayList()
    }
}

class ScoresHolder {
    fun addScores(toTypedArray: Array<Int>) {
        for (i in 0..toTypedArray.size - 1) {
            scores.add(toTypedArray[i])
        }
    }

    operator fun get(i: Int): Int {
        return scores[i]
    }

    companion object {
        var scores: ArrayList<Int> = ArrayList()
    }
}

class IdsHolder {
    fun addIds(toTypedArray: Array<Int>) {
        for (i in 0..toTypedArray.size - 1) {
            ids.add(toTypedArray[i])
        }
    }

    operator fun get(i: Int): Int {
        return ids[i]
    }

    companion object {
        var ids: ArrayList<Int> = ArrayList()
    }
}

class NamesHolder {
    fun addNames(toTypedArray: Array<String>) {
        for (i in 0..toTypedArray.size - 1) {
            names.add(toTypedArray[i])
        }
    }

    operator fun get(i: Int): String {
        return names[i]
    }

    companion object {
        var names: ArrayList<String> = ArrayList()
    }
}

class HousesHolder {
    fun addHouses(toTypedArray: Array<String>) {
        for (i in 0..toTypedArray.size - 1) {
            houses.add(toTypedArray[i])
        }
    }

    operator fun get(i: Int): String {
        return houses[i]
    }

    companion object {
        var houses: ArrayList<String> = ArrayList()
    }
}

class AudioController(ctx: GameActivity) {
    companion object {
        var isMuted = false
    }

    var bgMusic = MediaPlayer.create(ctx, R.raw.main_theme)
    val matchSound = MediaPlayer.create(ctx, R.raw.duplicate_sound)
    val victorySound = MediaPlayer.create(ctx, R.raw.congratulations_sound)
    val defeatSound = MediaPlayer.create(ctx, R.raw.time_over)

    init {
        isMuted = false
        bgMusic.setVolume(0.5f, 0.5f)
        bgMusic.isLooping = true
    }

    fun startBgMusic() {
        if (isMuted) {
            return
        }
        bgMusic.start()
    }

    fun stopBgMusic() {
        bgMusic.pause()
        bgMusic.seekTo(0)
    }

    fun playMatchSound() {
        if (isMuted) {
            return
        }
        matchSound.start()
    }

    fun playVictorySound() {
        if (isMuted) {
            return
        }
        stopBgMusic()
        victorySound.start()
    }

    fun playDefeatSound() {
        if (isMuted) {
            return
        }
        stopBgMusic()
        defeatSound.start()
    }

    fun muteallSounds() {
        isMuted = true
        stopBgMusic()
    }

    fun unmuteallSounds() {
        isMuted = false
        startBgMusic()
    }


}

class GameActivity : AppCompatActivity() {
    var outsideActivity = false

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val extras = intent.extras
        val cards = ArrayList<CardClass>()
        val gridSize = extras?.getInt("gridSize") ?: 4
        var totalTime = extras?.getInt("totalTime") ?: 60
        val multiplayer = extras?.getBoolean("multiplayer") ?: false
        val cardImage = ImagesHolder()
        val cardHouse = HousesHolder()
        val cardName = NamesHolder()
        val cardScore = ScoresHolder()
        val cardId = IdsHolder()
        val table = findViewById<TableLayout>(R.id.tableLayout)
        val cardsWithoutbutton = ArrayList<CardClassWithoutButton>()
        for (i in 0 until gridSize * gridSize) {
            val card = CardClassWithoutButton(
                cardId[i], cardName[i], cardHouse[i], cardScore[i], cardImage[i]
            )
            cardsWithoutbutton.add(card)
        }
        cardsWithoutbutton.shuffle()
        val audioController = AudioController(this)
        val soundSwitch = findViewById<Switch>(R.id.switchSound)
        soundSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                audioController.muteallSounds()
            } else {
                audioController.unmuteallSounds()
            }
        }
        val multiplayerText = findViewById<TextView>(R.id.playerText)
        val score2 = findViewById<TextView>(R.id.score2)
        if (multiplayer) {
            multiplayerText.text = "Player 1"
            multiplayerText.visibility = View.VISIBLE
            score2.visibility = View.VISIBLE
            val score2text = findViewById<TextView>(R.id.scoreText2)
            score2text.visibility = View.VISIBLE
        }

        fun populateTable(table: TableLayout, gridSize: Int) {
            for (i in 0 until gridSize) {
                val row = TableRow(this)
                for (j in 0 until gridSize) {
                    val card = ImageButton(this)
                    cards.add(
                        CardClass(
                            card,
                            cardsWithoutbutton[i * gridSize + j].id,
                            cardsWithoutbutton[i * gridSize + j].name,
                            cardsWithoutbutton[i * gridSize + j].house,
                            cardsWithoutbutton[i * gridSize + j].score,
                            decodeImage(cardsWithoutbutton[i * gridSize + j].image)
                        )
                    )
                    card.adjustViewBounds = true
                    card.maxWidth = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 60f, resources.displayMetrics
                    ).toInt()
                    card.maxHeight = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 60f, resources.displayMetrics
                    ).toInt()
                    card.id = i * gridSize + j
                    card.setImageResource(R.drawable.cardbackground)
                    card.setOnClickListener {}
                    row.addView(card)
                }
                table.addView(row)
            }
        }

        val matchedCards = ArrayList<CardClass>()
        var busy = false
        var cardToCheck: CardClass? = null
        val timer = findViewById<TextView>(R.id.timer)
        val score = findViewById<TextView>(R.id.score)
        var outOfGame = false
        var secondPlayer = false
        fun startGame() {
            audioController.startBgMusic()
            timer.text = totalTime.toString()
            score.text = "0"
            score2.text = "0"
            matchedCards.clear()
            busy = false
            cardToCheck = null
            val timerui = Timer()
            timerui.schedule(timerTask {
                runOnUiThread {
                    if (outOfGame) {
                        timerui.cancel()
                    }
                    if (outsideActivity) {
                        timerui.cancel()
                        audioController.stopBgMusic()
                    }
                    if (totalTime == 0) {
                        timerui.cancel()
                        audioController.playDefeatSound()
                        busy = true
                        Timer().schedule(timerTask {
                            finish()
                        }, 5000)
                    }
                    timer.text = totalTime.toString()
                    totalTime -= 1
                }
            }, 0, 1000)
        }

        fun scoreFormula(
            card: CardClass,
            win: Boolean,
            isSameHouse: Boolean,
            card2: CardClass = card,
            isMultiplayer: Boolean = false
        ): Int {
            var houseScore = 0
            when (card.house) {
                "gryffindor" -> houseScore = 2
                "hufflepuff" -> houseScore = 1
                "ravenclaw" -> houseScore = 1
                "slytherin" -> houseScore = 2
            }
            var houseScore2 = 0
            when (card2.house) {
                "gryffindor" -> houseScore2 = 2
                "hufflepuff" -> houseScore2 = 1
                "ravenclaw" -> houseScore2 = 1
                "slytherin" -> houseScore2 = 2
            }
            if (isMultiplayer) {
                return if (win) {
                    (2 * card.score * houseScore)
                } else if (isSameHouse) {
                    ((card.score + card2.score) / houseScore)
                } else {
                    ((card.score + card2.score) / 2) * houseScore * houseScore2
                }
            } else {
                return if (win) {
                    (2 * card.score * houseScore) * (totalTime / 10)
                } else if (isSameHouse) {
                    ((card.score + card2.score) / houseScore) * (totalTime / 10)
                } else {
                    ((card.score + card2.score) / 2) * houseScore * houseScore2 * (totalTime / 10)
                }
            }

        }

        fun changeScore(
            card: CardClass, win: Boolean, isSameHouse: Boolean, card2: CardClass = card
        ) {
            if (win) {
                if (secondPlayer) {
                    var currentscore = score2.text.toString().toInt()
                    currentscore =
                        currentscore + scoreFormula(card, win, isSameHouse, card2, multiplayer)
                    score2.text = (currentscore).toString()
                } else {
                    var currentscore = score.text.toString().toInt()
                    currentscore =
                        currentscore + scoreFormula(card, win, isSameHouse, card2, multiplayer)
                    score.text = (currentscore).toString()
                }
            } else {
                if (secondPlayer) {
                    score2.text = (score2.text.toString().toInt() - scoreFormula(
                        card, win, isSameHouse, card2, multiplayer
                    )).toString()
                } else {
                    score.text = (score.text.toString().toInt() - scoreFormula(
                        card, win, isSameHouse, card2, multiplayer
                    )).toString()
                }
            }
        }

        fun checkForMatch(card: CardClass, card2: CardClass) {
            // wait to show both cards
            busy = true
            val timerToShow = Timer()
            timerToShow.schedule(timerTask {
                runOnUiThread {
                    if (card.id == card2.id && card.house == card2.house) {
                        card.isMatched = true
                        card2.isMatched = true
                        matchedCards.add(card)
                        matchedCards.add(card2)
                        audioController.playMatchSound()
                        changeScore(card, true, true)
                        if (matchedCards.size == cards.size) {
                            outOfGame = true
                            audioController.playVictorySound()
                            Timer().schedule(timerTask {
                                finish()
                            }, 5000)
                        }
                    } else {
                        card.isFaceUp = false
                        card2.isFaceUp = false
                        card.button.setImageResource(R.drawable.cardbackground)
                        card2.button.setImageResource(R.drawable.cardbackground)
                        changeScore(card, false, card.house == card2.house, card2)
                        if (multiplayer) {
                            multiplayerText.text =
                                if (multiplayerText.text == "Player 1") "Player 2" else "Player 1"
                            secondPlayer = !secondPlayer
                        }
                    }
                    busy = false
                    cardToCheck = null
                }
            }, 500)
        }

        fun canFlipCard(card: CardClass): Boolean {
            if (busy) {
                return false
            }
            if (matchedCards.contains(card)) {
                return false
            }
            if (cardToCheck == card) {
                return false
            }
            if (card.isFaceUp) {
                return false
            }
            return true
        }

        fun flipCard(card: CardClass) {
            if (cardToCheck != null && canFlipCard(card)) {
                card.isFaceUp = true
                checkForMatch(card, cardToCheck!!)

            } else if (canFlipCard(card)) {
                card.isFaceUp = true
                cardToCheck = card
            }
        }

        fun game() {
            populateTable(table, gridSize)
            startGame()
            for (i in 0 until gridSize * gridSize) {
                cards[i].button.setOnClickListener {
                    if (canFlipCard(cards[i])) {
                        cards[i].button.setImageBitmap(cards[i].image)
                        flipCard(cards[i])
                    }
                }
            }
        }
        game()
    }

    override fun finish() {
        super.finish()
        NamesHolder.names.clear()
        ImagesHolder.images.clear()
        HousesHolder.houses.clear()
        ScoresHolder.scores.clear()
        IdsHolder.ids.clear()
        // stop timer and music
        outsideActivity = true
    }
}

