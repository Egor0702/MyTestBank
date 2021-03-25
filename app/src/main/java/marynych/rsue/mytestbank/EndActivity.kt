package marynych.rsue.mytestbank

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class EndActivity : AppCompatActivity() {
    lateinit var resultOfTheText : TextView
    lateinit var textView: TextView
    lateinit var imageOfUnicorn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        
        //здесь проинициализируем переменные тестового поля, кнопки и изображения
        resultOfTheText = findViewById(R.id.resultOfTheText)
        imageOfUnicorn = findViewById(R.id.imageBackGround)
        textView = findViewById(R.id.textView)

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
        var name = ""
        var colorName = 0
        var imageRes = 0
        var text = ""
            println(oneType > twoType && oneType > threeType)
            val i = 1
            for (i in 1..1) {
                if (oneType > twoType && oneType > threeType) {
                    name = getString(R.string.type_of_unicorn_one)
                    colorName = Color.RED
                    imageRes = R.drawable.red_unicorn300
                    text = getString(R.string.red_unicorn_wiki)
                    break
                }
                if (twoType > oneType && twoType > threeType) {
                    name = getString(R.string.type_of_unicorn_two)
                    colorName = Color.YELLOW
                    imageRes = R.drawable.yellow_unicorn
                    text = getString(R.string.yellow_unicorn_wiki)
                    break
                }
                if (threeType > oneType && threeType > twoType) {
                    name = getString(R.string.type_of_unicorn_three)
                    colorName = Color.parseColor("#fc0fc0")
                    imageRes = R.drawable.pink_unicorn1
                    text = getString(R.string.pink_unicorn_wiki)
                    break
                }
                if (oneType == twoType) {
                    name = getString(R.string.type_of_unicorn_one)
                    colorName = Color.RED
                    imageRes = R.drawable.red_unicorn300
                    text = getString(R.string.red_unicorn_wiki)
                    break
                }
                if (oneType == threeType) {
                    name = getString(R.string.type_of_unicorn_three)
                    colorName = Color.parseColor("#fc0fc0")
                    imageRes = R.drawable.pink_unicorn1
                    text = getString(R.string.pink_unicorn_wiki)
                    break
                }
                else {
                    name = getString(R.string.type_of_unicorn_two)
                    colorName = Color.YELLOW
                    imageRes = R.drawable.yellow_unicorn
                    text = getString(R.string.yellow_unicorn_wiki)
                }
            }
            setResultTest(name, colorName, imageRes, text)
    }
    fun setResultTest(name: String, colorName : Int, imageRes:Int, text : String){
    resultOfTheText.setText("$name")
        resultOfTheText.setTextColor(colorName)
    imageOfUnicorn.setImageResource(imageRes)
        textView.setText(text)
    }

    override fun onBackPressed() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
