package marynych.rsue.mytestbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var mTextQuestionView: TextView
    lateinit var mNextButton: Button
    lateinit var variantOne: CheckBox
    lateinit var variantTwo: CheckBox
    lateinit var variantThree: CheckBox

    var arrayAnswer:Arr
    var mCurrIndex = 0
    val mBankQuestionCorn : List<Question> = listOf(
        Question("Какой твой любимый цвет?", "Зеленый", 1,  "Розовый", 3,"Желтый", 2),
        Question("Что лучше всего описывает тебя:", "Дружелюбный, открыт к общению", 3, "Люблю природу, тишину", 1, "Разве есть кто-то лучше меня?!", 2)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextQuestionView = findViewById(R.id.text_question_view)
        variantOne = findViewById(R.id.variant_one)
        variantTwo = findViewById(R.id.variant_two)
        variantThree = findViewById(R.id.variant_three)
        mNextButton = findViewById(R.id.show_answer)

        mTextQuestionView.setText(mBankQuestionCorn[mCurrIndex].question)
        mTextQuestionView.setOnClickListener {
            var next = mCurrIndex
            next++
            if (next >= 0 && next < mBankQuestionCorn.size) {
                mCurrIndex = next
                setQuestion(mCurrIndex)
            } else
                Toast.makeText(this, R.string.stop_name, Toast.LENGTH_SHORT).show()
        }
        variantOne.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
          val text: String = variantOne.text.toString()
            if (variantOne.isChecked())
              mapAnswer.put(mCurrIndex, 1)
            else
              mapAnswer.remove(mCurrIndex)

        }
        variantTwo.setOnCheckedChangeListener{buttonView: CompoundButton, isChecked: Boolean ->
            if (variantTwo.isChecked())
                mapAnswer.put(mCurrIndex, 2)
            else
                mapAnswer.remove(mCurrIndex)
        }
        variantThree.setOnCheckedChangeListener{buttonView: CompoundButton, isChecked: Boolean ->
            if (variantThree.isChecked())
                mapAnswer.put(mCurrIndex, 3)
            else
                mapAnswer.remove(mCurrIndex)
        }
        mNextButton.setOnClickListener {
           if(mNextButton.text == "Завершить опрос"){
               val intent = Intent(this, EndActivity::class.java)
               intent.putExtra("key", mapAnswer)
               startActivity(intent)
           }
            var nextIndex = mCurrIndex
            nextIndex++
            if (nextIndex >= 0 && nextIndex < mBankQuestionCorn.size) {
                mCurrIndex = nextIndex
                setQuestion(mCurrIndex)
            } else
                Toast.makeText(this, R.string.stop_name, Toast.LENGTH_SHORT).show()
        }
    }
        fun setQuestion(index:Int){
            if(index == mBankQuestionCorn.size - 1)
                mNextButton.setText(R.string.button_name_two)
            mTextQuestionView.setText(mBankQuestionCorn[index].question)
            variantOne.setText(mBankQuestionCorn[index].answerOne)
            variantTwo.setText(mBankQuestionCorn[index].answerTwo)
            variantThree.setText(mBankQuestionCorn[index].answerThree)
        }
    fun testResult() : String{
        val valuesList :MutableCollection<Int> = mapAnswer.values
        var totalNumber = 0
        for( i in valuesList)

}