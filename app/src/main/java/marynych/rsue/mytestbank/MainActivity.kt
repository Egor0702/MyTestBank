package marynych.rsue.mytestbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.*
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var mTextQuestionView: TextView // объявлем переменну типа TextView
    lateinit var mNextButton: Button // объявлем переменну типа Button
    lateinit var variantOne: CheckBox // объявлем переменну типа CheckBox
    lateinit var variantTwo: CheckBox // объявлем переменну типа CheckBox
    lateinit var variantThree: CheckBox // объявлем переменну типа CheckBox
    lateinit var numbersText: TextView // объявляем переменную типа TextView

    var arrayAnswer:MutableList<Int> = mutableListOf() // инициализируем переменную, которая будет хранить ответы пользователя
    var mCurrIndex = 0 // переменная, которая будет играть роль счетчика вопросов (с помощью нее будет определен текущий вопрос)
    lateinit var mBankQuestionCorn : List<Question> // банк вопросов
    var flag : Boolean = false // данная переменная будет служить фдагом, указывающим на то выбрал ли пользователь какой-нибудь вариант ответа (true) или нет (false)

    override fun onCreate(savedInstanceState: Bundle?){
    if (savedInstanceState != null){ // проверяем запуск данной активности и если это так присваиваем переменным значением
    mCurrIndex = savedInstanceState.getInt("currentNumber", 0)
    var intArr: IntArray = savedInstanceState.getIntArray("array")!!
        arrayAnswer = intArr.toMutableList()
    }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBankQuestionCorn = Question.questionBank // присваиваем перемнную значение из класса Question, где хранятся вопросы

        mTextQuestionView = findViewById(R.id.text_question_view)
        variantOne = findViewById(R.id.variant_one)
        variantTwo = findViewById(R.id.variant_two)
        variantThree = findViewById(R.id.variant_three)
        mNextButton = findViewById(R.id.show_answer)
        numbersText = findViewById(R.id.number_question)

        mTextQuestionView.setText(mBankQuestionCorn[mCurrIndex].question) // устанавливаем первый вопрос на экране
        mTextQuestionView.setOnClickListener { // реализуем возможность, чтобы пользователь мог вызвать следующий вопрос нажатием на экран.
            var next = mCurrIndex
            next++
            if (next >= 0 && next < mBankQuestionCorn.size) { // устанавливаем границы, чтобы пользователь не выходил за рамки других вопросов
                mCurrIndex = next
                setQuestion(mCurrIndex)
            } else  // если пользователь все-таки вышел за рамки, то всплывает уведомление
                Toast.makeText(this, R.string.stop_name, Toast.LENGTH_SHORT).show()
        }
        numbersText.setText("Вопрос ${mCurrIndex+1} / ${mBankQuestionCorn.size}")
        variantOne.setText(mBankQuestionCorn[mCurrIndex].answerOne)
        variantOne.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->  // если пользователь нажал на первый вариант ответа ...
           val textVariantOne: String = variantOne.text.toString() // присваиваем этот полюбившийся текст первого ваианта на вопрос
          var answerVariantOne: Int = 0 // устанавливаем значение по умолчанию
            if (variantOne.isChecked()){ // если пользователь нажал на флажок чекбокса, то выполняется код заключенный в скобках
                flag = true // пользователь выбрал вариант ответа
                setEnableCheck(variantOne) // блокируем все остальные поля с ответами
                if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantOne) // если вариант ответа описанный в данном поле совпдает со значением заключенным в переменной данного Question,
                answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyOne) // то присваиваем answerVariantOne ее значение
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantOne)
                 answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyTwo)
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantOne)
                 answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyThree)
                           arrayAnswer.add( answerVariantOne)
            }else {
                Log.d(TAG,"сняли флаг в первом поле и перешли в поле else")
                arrayAnswer.remove(answerVariantOne) // удаляем этот ответ из массива
                setFreedomCheck(variantOne)
                flag = false // снимаем флаг
            }
        }
        variantTwo.setText(mBankQuestionCorn[mCurrIndex].answerTwo)
        variantTwo.setOnCheckedChangeListener{buttonView: CompoundButton, isChecked: Boolean ->
            val textVariantTwo: String = variantTwo.text.toString()
          var answerVariantTwo: Int = 0
            if (variantTwo.isChecked()){
                flag = true // пользователь выбрал вариант ответа
                setEnableCheck(variantTwo)
              if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantTwo)
                answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyOne)
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantTwo)
                 answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyTwo)
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantTwo)
                 answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyThree)
              arrayAnswer.add(answerVariantTwo)
            }else {
                Log.d(TAG,"сняли флаг во втором поле и перешли в поле else")
                arrayAnswer.remove(answerVariantTwo)
                setFreedomCheck(variantTwo)
                flag = false // снимаем флаг
            }
        }
        variantThree.setText(mBankQuestionCorn[mCurrIndex].answerThree)
        variantThree.setOnCheckedChangeListener{buttonView: CompoundButton, isChecked: Boolean ->
            val textVariantThree: String = variantThree.text.toString()
          var answerVariantThree: Int = 0
            if (variantThree.isChecked()){
                flag = true // пользователь выбрал вариант ответа
                setEnableCheck(variantThree)
              if(mBankQuestionCorn[mCurrIndex].answerOne == textVariantThree)
                answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyOne)
              if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantThree)
                 answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyTwo)
               if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantThree)
                 answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyThree)
              arrayAnswer.add(answerVariantThree)
            }else {
                Log.d(TAG,"сняли флаг в третьем поле и перешли в поле else")
                arrayAnswer.remove(answerVariantThree)
                setFreedomCheck(variantThree)
                flag = false // снимаем флаг
            }
        }

        mNextButton.setOnClickListener { // реализуем функциональность кнопки, выводящей следующий вопрос
           if(mNextButton.text == "Завершить опрос"){ // если кнопка называется "Завершить опрос", то выполняется код заключенный в скобках
               val intent = Intent(this, EndActivity::class.java) // создаем объект типа Интент. передаем ему текущий контекст и ссылку на класс EndActivity
               val arr : IntArray = arrayAnswer.toIntArray() // создаем массив типа IntArray на основе переменной arrayAnswer
               intent.putExtra("array", arr) // передаем в интент переменную arr и ключ "array"
               startActivity(intent) // передаем в метод интент и переходим к новой активити
               return@setOnClickListener
           }
            var nextIndex = mCurrIndex // присваиваем переменной nextIndex текущее значение счеткика вопросов. Мы делаем это не напрямую с mCurrIndex, так как может оазаться, что мы выходим за границы массива
            nextIndex++ // инкрементируем переменную
            if ((nextIndex >= 0 && nextIndex < mBankQuestionCorn.size) && flag) { // если переменная находится в диапозоне массива и пользователь выбрал какой-нибудь вариант ответа, то выполняем следующий код:
                mCurrIndex = nextIndex // присвиваем "счеткику" значение локальной переменной, так как теперь это безопасно, мы знаем, что мы в диапозоне нашего банка вопросов
                setQuestion(mCurrIndex) // передаем значение этой переменной в метод setQuestion()
                setCheck() // снимаем флаг с ответом
                setFreedomCheck() // снимаем блокировку со всех CheckBox
            } else // если пользователь вышел за пределы банка вопросов, то
                Toast.makeText(this, R.string.stop_name, Toast.LENGTH_SHORT).show() // предупреждаем его об этом в уведомлении и не передаем ничего в метод setQuestion()
        }
        }
    override fun onSaveInstanceState(outState : Bundle){ // на случай уничтожения активности сохраним ключевую информацию
    super.onSaveInstanceState(outState)
        outState.putInt("currentNumber", mCurrIndex) // текущий вопрос
        var arr : IntArray = arrayAnswer.toIntArray() // имеющиеся ответы пользователя
        outState.putIntArray("array", arr)  // помещаем это в Bundle, чтобы при работе метода onCreate записать данные значения в соответсвующие переменные
    }

        fun setQuestion(index:Int){ // этот метод устанавливает вопрос, отображающийся на текстовом поле приложения
            if(index == mBankQuestionCorn.size - 1) // если полученное число совпадает с индексом последнего вопроса, то
                mNextButton.setText(R.string.button_name_two) // меняеи наименование кнопки mNextButton
            mTextQuestionView.setText(mBankQuestionCorn[index].question) //устанавливаем текст вопроса и ответа
            variantOne.setText(mBankQuestionCorn[index].answerOne)
            variantTwo.setText(mBankQuestionCorn[index].answerTwo)
            variantThree.setText(mBankQuestionCorn[index].answerThree)
            numbersText.setText("Вопрос ${mCurrIndex+1} / ${mBankQuestionCorn.size}") // устанавливаем текущий номер вопроса в текстовом поле вверху
        }
    fun setCheck(){ // с помощью данного метода мы снимаем флаги, оставленные пользователем
        if(variantOne.isChecked())
            variantOne.setChecked(false)
        if(variantTwo.isChecked())
            variantTwo.setChecked(false)
        if(variantThree.isChecked())
            variantThree.setChecked(false)
    }
    fun setEnableCheck(check : CheckBox){ // когда пользователь выберет ответ, заблокируем остальные кнопки
            when (check){
                variantOne -> {variantTwo.isClickable = false
                               variantTwo.isFocusable = false
                               variantThree.isClickable = false
                               variantThree.isFocusable = false
                    Log.d(TAG, "setEnableCheck заблокировали два и три")}
                variantTwo -> {variantOne.isClickable = false
                               variantOne.isFocusable = false
                               variantThree.isClickable = false
                               variantThree.isFocusable = false
                    Log.d(TAG, "setEnableCheck заблокировали один и три")}
                variantThree ->{variantOne.isClickable = false
                                variantOne.isFocusable = false
                                variantTwo.isClickable = false
                                variantTwo.isFocusable = false
                    Log.d(TAG, "setEnableCheck заблокировали один и два")}
                else -> Log.d(TAG, "ошибка в setEnableCheck")
            }
        }
    fun setFreedomCheck(check : CheckBox){
        when (check) {
            variantOne -> {
                variantTwo.isClickable = true
                variantTwo.isFocusable = true
                variantThree.isClickable = true
                variantThree.isFocusable = true
            }
            variantTwo -> {
                variantOne.isClickable = true
                variantOne.isFocusable = true
                variantThree.isClickable = true
                variantThree.isFocusable = true
            }
            variantThree -> {
                variantOne.isClickable = true
                variantOne.isFocusable = true
                variantTwo.isClickable = true
                variantTwo.isFocusable = true
            }
            else -> Log.d(TAG, "ошибка в setFreedomCheck")
        }
    }
    fun setFreedomCheck(){
        variantOne.isClickable = true
        variantOne.isFocusable = true
        variantTwo.isClickable = true
        variantTwo.isFocusable = true
        variantThree.isClickable = true
        variantThree.isFocusable = true
    }
    }
