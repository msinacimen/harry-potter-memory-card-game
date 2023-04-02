package com.proje3.harrypottermemorymaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class GameMenuActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_menu)
        val seekbar = findViewById<SeekBar>(R.id.seekBar)
        val singleplayerButton = findViewById<Button>(R.id.singleplayerButton)
        val multiplayerButton = findViewById<Button>(R.id.multiplayerButton)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")

        findViewById<TextView>(R.id.textView).text = "Welcome, " + displayName

        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            auth.signOut()
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
            val googlesigninclient = GoogleSignIn.getClient(this, gso)
            googlesigninclient.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

        singleplayerButton.setOnClickListener {
            val gridSize = (seekbar.progress + 1) * 2
            val cardsArray = ArrayList<CardClassWithoutButton>()
            if (seekbar.progress == 0) { //2x2
                readDatagryffindor2x2 { cardswithbutton ->
                    for (card in cardswithbutton.toTypedArray()) {
                        cardsArray.add(card)
                    }
                    readDataslytherin2x2 { cardswithbuttona ->
                        for (card in cardswithbuttona.toTypedArray()) {
                            cardsArray.add(card)
                        }
                        val intent = Intent(this, GameActivity::class.java)
                        intent.putExtra("gridSize", gridSize)
                        intent.putExtra("totalTime", 45)
                        val cardImage = ImagesHolder()
                        cardImage.addImages(cardsArray.map { it.image }.toTypedArray())
                        val cardScore = ScoresHolder()
                        cardScore.addScores(cardsArray.map { it.score }.toTypedArray())
                        val cardName = NamesHolder()
                        cardName.addNames(cardsArray.map { it.name }.toTypedArray())
                        val cardHouse = HousesHolder()
                        cardHouse.addHouses(cardsArray.map { it.house }.toTypedArray())
                        val cardId = IdsHolder()
                        cardId.addIds(cardsArray.map { it.id }.toTypedArray())
                        startActivity(intent)
                    }
                }
            }  //2x2
            else if (seekbar.progress == 1) { //4x4
                readDatagryffindor4x4 { cardswithbutton ->
                    for (card in cardswithbutton.toTypedArray()) {
                        cardsArray.add(card)
                    }
                    readDataslytherin4x4 { cardswithbuttona ->
                        for (card in cardswithbuttona.toTypedArray()) {
                            cardsArray.add(card)
                        }
                        readDatahufflepuff4x4 { cardswithbuttonb ->
                            for (card in cardswithbuttonb.toTypedArray()) {
                                cardsArray.add(card)
                            }
                            readDataravenclaw4x4 { cardswithbuttonc ->
                                for (card in cardswithbuttonc.toTypedArray()) {
                                    cardsArray.add(card)
                                }
                                val intent = Intent(this, GameActivity::class.java)
                                intent.putExtra("gridSize", gridSize)
                                intent.putExtra("totalTime", 45)
                                val cardImage = ImagesHolder()
                                cardImage.addImages(cardsArray.map { it.image }.toTypedArray())
                                val cardScore = ScoresHolder()
                                cardScore.addScores(cardsArray.map { it.score }.toTypedArray())
                                val cardName = NamesHolder()
                                cardName.addNames(cardsArray.map { it.name }.toTypedArray())
                                val cardHouse = HousesHolder()
                                cardHouse.addHouses(cardsArray.map { it.house }.toTypedArray())
                                val cardId = IdsHolder()
                                cardId.addIds(cardsArray.map { it.id }.toTypedArray())
                                startActivity(intent)
                            }
                        }
                    }
                }
            } //4x4
            else if (seekbar.progress == 2) { //6x6
                readDatagryffindor6x6 { cardswithbutton ->
                    for (card in cardswithbutton.toTypedArray()) {
                        cardsArray.add(card)
                    }
                    readDataslytherin6x6 { cardswithbuttona ->
                        for (card in cardswithbuttona.toTypedArray()) {
                            cardsArray.add(card)
                        }
                        readDatahufflepuff6x6 { cardswithbuttonb ->
                            for (card in cardswithbuttonb.toTypedArray()) {
                                cardsArray.add(card)
                            }
                            readDataravenclaw6x6 { cardswithbuttonc ->
                                for (card in cardswithbuttonc.toTypedArray()) {
                                    cardsArray.add(card)
                                }
                                val intent = Intent(this, GameActivity::class.java)
                                intent.putExtra("gridSize", gridSize)
                                intent.putExtra("totalTime", 45)
                                val cardImage = ImagesHolder()
                                cardImage.addImages(cardsArray.map { it.image }.toTypedArray())
                                val cardScore = ScoresHolder()
                                cardScore.addScores(cardsArray.map { it.score }.toTypedArray())
                                val cardName = NamesHolder()
                                cardName.addNames(cardsArray.map { it.name }.toTypedArray())
                                val cardHouse = HousesHolder()
                                cardHouse.addHouses(cardsArray.map { it.house }.toTypedArray())
                                val cardId = IdsHolder()
                                cardId.addIds(cardsArray.map { it.id }.toTypedArray())
                                startActivity(intent)
                            }
                        }
                    }
                }
            } //6x6
        }
        multiplayerButton.setOnClickListener {
            val gridSize = (seekbar.progress + 1) * 2
            val cardsArray = ArrayList<CardClassWithoutButton>()
            if (seekbar.progress == 0) { //2x2
                readDatagryffindor2x2 { cardswithbutton ->
                    for (card in cardswithbutton.toTypedArray()) {
                        cardsArray.add(card)
                    }
                    readDataslytherin2x2 { cardswithbuttona ->
                        for (card in cardswithbuttona.toTypedArray()) {
                            cardsArray.add(card)
                        }
                        val intent = Intent(this, GameActivity::class.java)
                        intent.putExtra("gridSize", gridSize)
                        intent.putExtra("totalTime", 60)
                        intent.putExtra("multiplayer", true)
                        val cardImage = ImagesHolder()
                        cardImage.addImages(cardsArray.map { it.image }.toTypedArray())
                        val cardScore = ScoresHolder()
                        cardScore.addScores(cardsArray.map { it.score }.toTypedArray())
                        val cardName = NamesHolder()
                        cardName.addNames(cardsArray.map { it.name }.toTypedArray())
                        val cardHouse = HousesHolder()
                        cardHouse.addHouses(cardsArray.map { it.house }.toTypedArray())
                        val cardId = IdsHolder()
                        cardId.addIds(cardsArray.map { it.id }.toTypedArray())
                        startActivity(intent)
                    }
                }
            }  //2x2
            else if (seekbar.progress == 1) { //4x4
                readDatagryffindor4x4 { cardswithbutton ->
                    for (card in cardswithbutton.toTypedArray()) {
                        cardsArray.add(card)
                    }
                    readDataslytherin4x4 { cardswithbuttona ->
                        for (card in cardswithbuttona.toTypedArray()) {
                            cardsArray.add(card)
                        }
                        readDatahufflepuff4x4 { cardswithbuttonb ->
                            for (card in cardswithbuttonb.toTypedArray()) {
                                cardsArray.add(card)
                            }
                            readDataravenclaw4x4 { cardswithbuttonc ->
                                for (card in cardswithbuttonc.toTypedArray()) {
                                    cardsArray.add(card)
                                }
                                val intent = Intent(this, GameActivity::class.java)
                                intent.putExtra("gridSize", gridSize)
                                intent.putExtra("totalTime", 60)
                                intent.putExtra("multiplayer", true)
                                val cardImage = ImagesHolder()
                                cardImage.addImages(cardsArray.map { it.image }.toTypedArray())
                                val cardScore = ScoresHolder()
                                cardScore.addScores(cardsArray.map { it.score }.toTypedArray())
                                val cardName = NamesHolder()
                                cardName.addNames(cardsArray.map { it.name }.toTypedArray())
                                val cardHouse = HousesHolder()
                                cardHouse.addHouses(cardsArray.map { it.house }.toTypedArray())
                                val cardId = IdsHolder()
                                cardId.addIds(cardsArray.map { it.id }.toTypedArray())
                                startActivity(intent)
                            }
                        }
                    }
                }
            } //4x4
            else if (seekbar.progress == 2) { //6x6
                readDatagryffindor6x6 { cardswithbutton ->
                    for (card in cardswithbutton.toTypedArray()) {
                        cardsArray.add(card)
                    }
                    readDataslytherin6x6 { cardswithbuttona ->
                        for (card in cardswithbuttona.toTypedArray()) {
                            cardsArray.add(card)
                        }
                        readDatahufflepuff6x6 { cardswithbuttonb ->
                            for (card in cardswithbuttonb.toTypedArray()) {
                                cardsArray.add(card)
                            }
                            readDataravenclaw6x6 { cardswithbuttonc ->
                                for (card in cardswithbuttonc.toTypedArray()) {
                                    cardsArray.add(card)
                                }
                                val intent = Intent(this, GameActivity::class.java)
                                intent.putExtra("gridSize", gridSize)
                                intent.putExtra("totalTime", 60)
                                intent.putExtra("multiplayer", true)
                                val cardImage = ImagesHolder()
                                cardImage.addImages(cardsArray.map { it.image }.toTypedArray())
                                val cardScore = ScoresHolder()
                                cardScore.addScores(cardsArray.map { it.score }.toTypedArray())
                                val cardName = NamesHolder()
                                cardName.addNames(cardsArray.map { it.name }.toTypedArray())
                                val cardHouse = HousesHolder()
                                cardHouse.addHouses(cardsArray.map { it.house }.toTypedArray())
                                val cardId = IdsHolder()
                                cardId.addIds(cardsArray.map { it.id }.toTypedArray())
                                startActivity(intent)
                            }
                        }
                    }
                }
            } //6x6
        }
    }


    fun readDatagryffindor4x4(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("gryffindor")
        val randomIdArray = ArrayList<Int>()
        val random = Random()
        for (i in 0..1) {
            var randomId = random.nextInt(11) + 1
            while (randomIdArray.contains(randomId)) {
                randomId = random.nextInt(11) + 1
            }
            randomIdArray.add(randomId)
        }
        val query = placesRef.whereIn("id", randomIdArray)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "gryffindor",
                                house = "gryffindor",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDataslytherin4x4(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("slytherin")
        val randomIdArray = ArrayList<Int>()
        val random = Random()
        for (i in 0..1) {
            var randomId = random.nextInt(11) + 1
            while (randomIdArray.contains(randomId)) {
                randomId = random.nextInt(11) + 1
            }
            randomIdArray.add(randomId)
        }
        val query = placesRef.whereIn("id", randomIdArray)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "slytherin",
                                house = "slytherin",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDatahufflepuff4x4(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("hufflepuff")
        val randomIdArray = ArrayList<Int>()
        val random = Random()
        for (i in 0..1) {
            var randomId = random.nextInt(11) + 1
            while (randomIdArray.contains(randomId)) {
                randomId = random.nextInt(11) + 1
            }
            randomIdArray.add(randomId)
        }
        val query = placesRef.whereIn("id", randomIdArray)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "hufflepuff",
                                house = "hufflepuff",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDataravenclaw4x4(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("ravenclaw")
        val randomIdArray = ArrayList<Int>()
        val random = Random()
        for (i in 0..1) {
            var randomId = random.nextInt(11) + 1
            while (randomIdArray.contains(randomId)) {
                randomId = random.nextInt(11) + 1
            }
            randomIdArray.add(randomId)
        }
        val query = placesRef.whereIn("id", randomIdArray)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "ravenclaw",
                                house = "ravenclaw",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDatagryffindor2x2(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("gryffindor")

        val query = placesRef.whereEqualTo("id", Random().nextInt(11) + 1)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "gryffindor",
                                house = "gryffindor",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDataslytherin2x2(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("slytherin")

        val query = placesRef.whereEqualTo("id", Random().nextInt(11) + 1)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "slytherin",
                                house = "slytherin",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDatahufflepuff2x2(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("hufflepuff")

        val query = placesRef.whereEqualTo("id", Random().nextInt(11) + 1)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "hufflepuff",
                                house = "hufflepuff",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDataravenclaw2x2(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("ravenclaw")

        val query = placesRef.whereEqualTo("id", Random().nextInt(11) + 1)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "ravenclaw",
                                house = "ravenclaw",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDatagryffindor6x6(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("gryffindor")
        val randomIdArray = ArrayList<Int>()
        val random = Random()
        for (i in 0..4) {
            var randomId = random.nextInt(11) + 1
            while (randomIdArray.contains(randomId)) {
                randomId = random.nextInt(11) + 1
            }
            randomIdArray.add(randomId)
        }
        val query = placesRef.whereIn("id", randomIdArray)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "gryffindor",
                                house = "gryffindor",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDataslytherin6x6(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("slytherin")
        val randomIdArray = ArrayList<Int>()
        val random = Random()
        for (i in 0..3) {
            var randomId = random.nextInt(11) + 1
            while (randomIdArray.contains(randomId)) {
                randomId = random.nextInt(11) + 1
            }
            randomIdArray.add(randomId)
        }
        val query = placesRef.whereIn("id", randomIdArray)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "slytherin",
                                house = "slytherin",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDatahufflepuff6x6(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("hufflepuff")
        val randomIdArray = ArrayList<Int>()
        val random = Random()
        for (i in 0..4) {
            var randomId = random.nextInt(11) + 1
            while (randomIdArray.contains(randomId)) {
                randomId = random.nextInt(11) + 1
            }
            randomIdArray.add(randomId)
        }
        val query = placesRef.whereIn("id", randomIdArray)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "hufflepuff",
                                house = "hufflepuff",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }

    fun readDataravenclaw6x6(myCallback: (List<CardClassWithoutButton>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val placesRef = db.collection("ravenclaw")
        val randomIdArray = ArrayList<Int>()
        val random = Random()
        for (i in 0..3) {
            var randomId = random.nextInt(11) + 1
            while (randomIdArray.contains(randomId)) {
                randomId = random.nextInt(11) + 1
            }
            randomIdArray.add(randomId)
        }
        val query = placesRef.whereIn("id", randomIdArray)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardClassWithoutButton>()
                for (document in task.result) {
                    val scr = (document.data.getValue(key = "score")).toString().toInt()
                    val img = (document.data.getValue(key = "img")).toString()
                    for (a in 0..1) {
                        list.add(
                            CardClassWithoutButton(
                                id = document.data.getValue(key = "id").toString().toInt(),
                                name = "ravenclaw",
                                house = "ravenclaw",
                                score = scr,
                                image = img
                            )
                        )
                    }
                }
                myCallback(list)
            }
        }
    }
}
