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
        fun getNameOfTheUnicorn(oneType : Int, twoType : Int, threeType : Int) : String{
        if (oneType > twoType && oneType > threeType)
        return "Вы красный единорог!"
        if (twoType > oneType && twoType > threeType)
        return "Вы желтый единорог!"
        if(threeType > oneType && threeType > twoType)
        return "Вы розовый единорог!"
         if(oneType == twoType)
            return "Вы красный единорог!"
            if (oneType == threeType)
            return "Вы розовый единорог!"
            else
            return "Вы желтый единорог!"
    }
    
}
