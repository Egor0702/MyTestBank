package marynych.rsue.mytestbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var mTextQuestionView: TextView // объявлем переменну типа TextView
    lateinit var mNextButton: Button // объявлем переменну типа Button
    lateinit var variantOne: CheckBox // объявлем переменну типа CheckBox
    lateinit var variantTwo: CheckBox // объявлем переменну типа CheckBox
    lateinit var variantThree: CheckBox // объявлем переменну типа CheckBox

    var arrayAnswer:MutableList<Int>() = mutableListOf() // инициализируем переменную, которая будет хранить ответы пользователя
    var mCurrIndex = 0 // переменная, которая будет играть роль счетчика вопросов (с помощью нее будет определен текущий вопрос)
    lateinit var mBankQuestionCorn : List<Question> // банк вопросов
    )
    override fun onCreate(savedInstanceState: Bundle?){
    if (savedInstanceState != null){ // проверяем запускалась ли данная активность и если это так присваиваем переменным значение
    mCurrIndex = savedInstanceState.getInt("currentNumber", 0)
    arrayAnswer = savedInstanceState.getIntArrayExtra("array", null)
    }
    
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        mBankQuestionCorn = Question.questionBank // присваиваем перемнной значение переменной из класса Question, где хранятся вопросы

        mTextQuestionView = findViewById(R.id.text_question_view) 
        variantOne = findViewById(R.id.variant_one)
        variantTwo = findViewById(R.id.variant_two)
        variantThree = findViewById(R.id.variant_three)
        mNextButton = findViewById(R.id.show_answer)

        mTextQuestionView.setText(mBankQuestionCorn[mCurrIndex].question) // устанавливаем первый вопрос на экране
        mTextQuestionView.setOnClickListener { // реализуем возможность, чтобы пользователь мог вызывать следующий вопрос нажатием на экран.
            var next = mCurrIndex
            next++
            if (next >= 0 && next < mBankQuestionCorn.size) { // устанавливаем границы, чтобы пользователь не выходил за рамки имеющихся вопросов
                mCurrIndex = next
                setQuestion(mCurrIndex)
            } else // если пользователь все-таки вышел за рамки, то всплывает уведомление
                Toast.makeText(this, R.string.stop_name, Toast.LENGTH_SHORT).show()
        }
        variantOne.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean -> // если пользователь нажал на первый вариант ответа...
           val textVariantOne: String = variantOne.text.toString() // присваиваем этому полю текст первого ваианта на вопрос
          var answerVariantOne: Int = 0 // устанавливаем значение по умолчанию
            if (variantOne.isChecked()){ // если пользователь нажал на флажок чекбокса, то выполняется код заключенный в скобках
              if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantOne) // если вариант ответа описанный в данном поле совпдает со значением заключенным в переменной данного Question,
                answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyOne) // то присваиваем answerVariantOne ее значение
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantOne)
                 answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyTwo)
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantOne)
                 answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyThree)   
                           arrayAnswer.add( answerVariantOne)
            }else // если пользователь снял флажок
                 arrayAnser.remove( answerVariantOne) // удаляем этот ответ из массива                   
        }
        variantTwo.setOnCheckedChangeListener{buttonView: CompoundButton, isChecked: Boolean ->
            val textVariantTwo: String = variantTwo.text.toString()
          var answerVariantTwo: Int = 0
            if (variantOne.isChecked()){
              if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantTwo)
                answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyOne
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantTwo)
                 answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyTwo
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantTwo)
                 answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyThree   
              arrayAnswer.add(answerVariantTwo)
            }else
                 arrayAnser.remove( answerVariantTwo)   
        }
        variantThree.setOnCheckedChangeListener{buttonView: CompoundButton, isChecked: Boolean ->
            val textVariantThree: String = variantThree.text.toString()
          var answerVariantThree: Int = 0
            if (variantOne.isChecked()){
              if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantTwo)
                answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyOne
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantTwo)
                 answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyTwo
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantTwo)
                 answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyThree   
              arrayAnswer.add(answerVariantThree)
            }else
                 arrayAnser.remove( answerVariantThree)   
        }
        }
        mNextButton.setOnClickListener { // реализуем функциональность кнопки, выводящей следующий вопрос
           if(mNextButton.text == "Завершить опрос"){ // если кнопка называется "Завершить опрос", то выполняется код заключенный в скобках
               val intent = Intent(this, EndActivity::class.java) // создаем объект типа Интент. передаем ему текущий контекст и ссылку на класс EndActivity
               arr : Array<Int> = arrayAnswer.toArray() // создаем массив целочисленных типов на основе переменной arrayAnswer
               intent.putExtra("array", arr) // передаем в интент переменную arr и ключ "array"
               startActivity(intent) // передаем в метод интент и переходим к новой активити
           }
            var nextIndex = mCurrIndex // присваиваем переменной nextIndex текущее значение счеткика вопросов. Мы делаем это не напрямую с mCurrIndex, так как может оазаться, что мы выходим за границы массива
            nextIndex++ // инкрементируем переменную
            if (nextIndex >= 0 && nextIndex < mBankQuestionCorn.size) { // если переменная находится в диапозоне массива, то выполняем следующий код:
                mCurrIndex = nextIndex // присвиваем "счеткику" значение локальной переменной, так как теперь это безопасно, мы знаем, что мы в диапозоне нашего банка вопросов
                setQuestion(mCurrIndex) // передаем значение этой переменной в метод setQuestion()
            } else // если пользователь вышел за пределы банка вопросов, то
                Toast.makeText(this, R.string.stop_name, Toast.LENGTH_SHORT).show() // предупреждаем его об этом в уведомлении и не передаем ничего в метод setQuestion()
        }
    }
    override fun onSaveInstanceState(Bundle outState){ // на случай уничтожения активности сохраним ключевую информацию
    super.onSaveInstanceState(outState)
        outState.putInt("currentNumber", mCurrIndex) // текущий вопрос
        arr : Array<Int> = arrayAnswer.toArray() // имеющиеся ответы пользователя
        outState.putIntArrayExtra("array", arr) // помещаем это в Bundle, чтобы при работе метода onCreate записать данные значения в соответсвующие переменные
        
    }

        fun setQuestion(index:Int){ // этот метод устанавливает вопрос, отображающийся на текстовом поле приложения
            if(index == mBankQuestionCorn.size - 1) // если полученное число совпадает с индексом последнего вопроса, то 
                mNextButton.setText(R.string.button_name_two) // меняеи наименование кнопки mNextButton
            mTextQuestionView.setText(mBankQuestionCorn[index].question) //устанавливаем текст вопроса и ответа
            variantOne.setText(mBankQuestionCorn[index].answerOne)
            variantTwo.setText(mBankQuestionCorn[index].answerTwo)
            variantThree.setText(mBankQuestionCorn[index].answerThree)
        }
    fun testResult() : String{
        val valuesList :MutableCollection<Int> = mapAnswer.values
        var totalNumber = 0
        for( i in valuesList)

}
