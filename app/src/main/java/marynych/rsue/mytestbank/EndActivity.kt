package marynych.rsue.mytestbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EndActivity : AppCompatActivity() {
    lateinit var resultOfTheText : TextView
    lateinit var returnButton : Button
    lateinit var imageOfUnicorn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        
        //здесь проинициализируем переменные тестового поля, кнопки и изображения
        resultOfTheText = findViewById(R.id.textView)
        returnButton = findViewById(R.id.button_return)
        imageOfUnicorn = findViewById(R.id.imageView2)
        
        returnButton.setOnClickListener{
        val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun getNumbersOfUnicornGroups(){
        var firstTypeOfUnicorn : Int = 0
        var secondTypeOfUnicorn : Int = 0
        var thirdTypeOfUnicorn : Int = 0
        val numbersGroup : Array<Int> = getIntent().getIntArrayExtra("array")
        for (i : Int in.. numbersGroup){
        when(i){
        1 -> firstTypeOfUnicorn++
        2 -> secondTypeOfUnicorn++
        3 -> thirdTypeOfUnicorn ++
        }
        }
        getNameOfTheUnicorn(firstTypeOfUnicorn, secondTypeOfUnicorn, thirdTypeOfUnicorn)
    }
        fun getNameOfTheUnicorn(oneType : Int, twoType : Int, threeType : Int) : Int, Int{
        val name : Int = 0
        val imageRes : Int = 0
        if (oneType > twoType && oneType > threeType){
        name = R.id.type_of_unicorn_one
        imageRes = R.drawable.red_unicorn
        }
        if (twoType > oneType && twoType > threeType){
        name = R.id.type_of_unicorn_two
        imageRes = R.drawable.yellow_unicorn_1
        }
        if(threeType > oneType && threeType > twoType){
        name = R.id.type_of_unicorn_three
        imageRes = R.drawable.pink_unicorn
        }
         if(oneType == twoType){
        name = R.id.type_of_unicorn_one
        imageRes = R.drawable.red_unicorn
        }
            if (oneType == threeType){
        name = R.id.type_of_unicorn_three
        imageRes = R.drawable.pink_unicorn
        }
            else{
        name = R.id.type_of_unicorn_two
        imageRes = R.drawable.yellow_unicorn_1
        }
        
            setResultTest(name, imageRes)
    }
    fun setResultTest(name, imageRes){
    resultOfTheText.setText(name)
    imageOfUnicorn.setImageResource(imageRes)
    }
    
}
