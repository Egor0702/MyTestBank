package marynych.rsue.mytestbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class EndActivity : AppCompatActivity() {
    lateinit var resultOfTheText : TextView

    lateinit var imageOfUnicorn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        
        //здесь проинициализируем переменные тестового поля, кнопки и изображения
        resultOfTheText = findViewById(R.id.textView)
        imageOfUnicorn = findViewById(R.id.imageBackGround)

        getNumbersOfUnicornGroups()
    }
    fun getNumbersOfUnicornGroups(){
        var firstTypeOfUnicorn : Int = 0
        var secondTypeOfUnicorn : Int = 0
        var thirdTypeOfUnicorn : Int = 0
       val numbersGroup: IntArray = getIntent().getIntArrayExtra("array")!!
        for (i:Int in numbersGroup){
        when(i){
        1 -> firstTypeOfUnicorn++
        2 -> secondTypeOfUnicorn++
        3 -> thirdTypeOfUnicorn ++
        }
        }
        println("first $firstTypeOfUnicorn second $secondTypeOfUnicorn third $thirdTypeOfUnicorn")
        getNameOfTheUnicorn(firstTypeOfUnicorn, secondTypeOfUnicorn, thirdTypeOfUnicorn)
    }
        fun getNameOfTheUnicorn(oneType : Int, twoType : Int, threeType : Int){
        var name : String = ""
        var imageRes : Int = 0
            println(oneType > twoType && oneType > threeType)
            val i = 1
            for (i in 1..1) {
                if (oneType > twoType && oneType > threeType) {
                    name = getString(R.string.type_of_unicorn_one)
                    imageRes = R.drawable.red_unicorn300
                    break
                }
                if (twoType > oneType && twoType > threeType) {
                    name = getString(R.string.type_of_unicorn_two)
                    imageRes = R.drawable.yellow_unicorn
                    break
                }
                if (threeType > oneType && threeType > twoType) {
                    name = getString(R.string.type_of_unicorn_three)
                    imageRes = R.drawable.pink_unicorn1
                    break
                }
                if (oneType == twoType) {
                    name = getString(R.string.type_of_unicorn_one)
                    imageRes = R.drawable.red_unicorn300
                    break
                }
                if (oneType == threeType) {
                    name = getString(R.string.type_of_unicorn_three)
                    imageRes = R.drawable.pink_unicorn1
                    break
                }
                else {
                    name = getString(R.string.type_of_unicorn_two)
                    imageRes = R.drawable.yellow_unicorn
                }
            }
            setResultTest(name, imageRes)
    }
    fun setResultTest(name: String, imageRes:Int){
    resultOfTheText.setText("Ты $name")
    imageOfUnicorn.setImageResource(imageRes)
    }

    override fun onBackPressed() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
