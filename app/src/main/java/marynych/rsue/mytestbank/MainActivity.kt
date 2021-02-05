package marynych.rsue.mytestbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    lateinit var mTextQuestionView: TextView
    lateinit var mNextButton: Button
    lateinit var variantOne: CheckBox
    lateinit var variantTwo: CheckBox
    lateinit var variantThree: CheckBox

    var arrayAnswer:MutableList<Int> = mutableListOf()
    var mCurrIndex = 0
    lateinit var mBankQuestionCorn : List<Question>

    override fun onCreate(savedInstanceState: Bundle?){
    if (savedInstanceState != null){
    mCurrIndex = savedInstanceState.getInt("currentNumber", 0)
    var intArr: IntArray = savedInstanceState.getIntArray("array")!!
        arrayAnswer = intArr.toMutableList()
    }
    
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        mBankQuestionCorn = Question.questionBank

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
           val textVariantOne: String = variantOne.text.toString()
          var answerVariantOne: Int = 0
            if (variantOne.isChecked()){
              if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantOne)
                answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyOne)
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantOne)
                 answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyTwo)
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantOne)
                 answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyThree)
                           arrayAnswer.add( answerVariantOne)
            }else
                 arrayAnswer.remove( answerVariantOne)
        }
        variantTwo.setOnCheckedChangeListener{buttonView: CompoundButton, isChecked: Boolean ->
            val textVariantTwo: String = variantTwo.text.toString()
          var answerVariantTwo: Int = 0
            if (variantOne.isChecked()){
              if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantTwo)
                answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyOne)
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantTwo)
                 answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyTwo)
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantTwo)
                 answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyThree)
              arrayAnswer.add(answerVariantTwo)
            }else
                 arrayAnswer.remove( answerVariantTwo)
        }
        variantThree.setOnCheckedChangeListener{buttonView: CompoundButton, isChecked: Boolean ->
            val textVariantThree: String = variantThree.text.toString()
          var answerVariantThree: Int = 0
            if (variantOne.isChecked()){
              if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantThree)
                answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyOne)
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantThree)
                 answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyTwo)
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantThree)
                 answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyThree)
              arrayAnswer.add(answerVariantThree)
            }else
                 arrayAnswer.remove( answerVariantThree)
        }

        mNextButton.setOnClickListener {
           if(mNextButton.text == "Завершить опрос"){
               val intent = Intent(this, EndActivity::class.java)
               val arr : IntArray = arrayAnswer.toIntArray()
               intent.putExtra("array", arr)
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
    override fun onSaveInstanceState(outState : Bundle){
    super.onSaveInstanceState(outState)
        outState.putInt("currentNumber", mCurrIndex)
        var arr : IntArray = arrayAnswer.toIntArray()
        outState.putIntArray("array", arr)
    }

        fun setQuestion(index:Int){
            if(index == mBankQuestionCorn.size - 1)
                mNextButton.setText(R.string.button_name_two)
            mTextQuestionView.setText(mBankQuestionCorn[index].question)
            variantOne.setText(mBankQuestionCorn[index].answerOne)
            variantTwo.setText(mBankQuestionCorn[index].answerTwo)
            variantThree.setText(mBankQuestionCorn[index].answerThree)
        }
}