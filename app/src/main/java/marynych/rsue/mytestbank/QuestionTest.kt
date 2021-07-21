package marynych.rsue.mytestbank

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView


internal const val ARG_PARAM = "object"

class QuestionTest : Fragment() {
    private val TAG = "Egor"
    lateinit var mTextQuestionView: TextView // объявлем переменну типа TextView
    lateinit var mNextButton: Button // объявлем переменну типа Button
    lateinit var variantOne: CheckBox // объявлем переменну типа CheckBox
    lateinit var variantTwo: CheckBox // объявлем переменну типа CheckBox
    lateinit var variantThree: CheckBox // объявлем переменну типа CheckBox
    lateinit var numbersText: TextView // объявляем переменную типа TextView

    var mCurrIndex =
        0 // переменная, которая будет играть роль счетчика вопросов (с помощью нее будет определен текущий вопрос)
    var arrayAnswer: MutableList<Int> =
        mutableListOf() // инициализируем переменную, которая будет хранить ответы пользователя
    lateinit var mBankQuestionCorn: List<Question> // банк вопросов
    var flag: Boolean =
        false // данная переменная будет служить фдагом, указывающим на то выбрал ли пользователь какой-нибудь вариант ответа (true) или нет (false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "мы во фрагменте")
        if (savedInstanceState != null) { // проверяем запуск данной активности и если это так присваиваем переменным значением
            var intArr: IntArray = savedInstanceState.getIntArray("array")!!
            arrayAnswer = intArr.toMutableList()
        }
        mBankQuestionCorn =
            Question.questionBank // присваиваем перемнную значение из класса Question, где хранятся вопросы

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mTextQuestionView = view.findViewById(R.id.text_question_view)
        variantOne = view.findViewById(R.id.variant_one)
        variantTwo = view.findViewById(R.id.variant_two)
        variantThree = view.findViewById(R.id.variant_three)
        numbersText = view.findViewById(R.id.number_question)
        mNextButton = view.findViewById(R.id.show_answer)
        val mainActivity = MainActivity()

        arguments?.takeIf { it.containsKey(ARG_PARAM) }?.apply {
            mCurrIndex = getInt(ARG_PARAM)
            if (mCurrIndex == mBankQuestionCorn.size - 1){
                Log.d(TAG, "елаем кнопку видимой")
                mNextButton.visibility = View.VISIBLE
            }
        }

        numbersText.setText("Вопрос ${mCurrIndex + 1} / ${mBankQuestionCorn.size}")

        variantOne.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->  // если пользователь нажал на первый вариант ответа ...
            val textVariantOne: String =
                variantOne.text.toString() // присваиваем этот полюбившийся текст первого ваианта на вопрос
            var answerVariantOne: Int = 0 // устанавливаем значение по умолчанию
            if (variantOne.isChecked()) { // если пользователь нажал на флажок чекбокса, то выполняется код заключенный в скобках
                flag = true // пользователь выбрал вариант ответа
                setEnableCheck(variantOne) // блокируем все остальные поля с ответами
                if (mBankQuestionCorn[mCurrIndex].answerOne == textVariantOne) // если вариант ответа описанный в данном поле совпдает со значением заключенным в переменной данного Question,
                    answerVariantOne =
                        (mBankQuestionCorn[mCurrIndex].keyOne) // то присваиваем answerVariantOne ее значение
                if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantOne)
                    answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyTwo)
                if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantOne)
                    answerVariantOne = (mBankQuestionCorn[mCurrIndex].keyThree)
                arrayAnswer.add(answerVariantOne)
            } else {
                Log.d(TAG, "сняли флаг в первом поле и перешли в поле else")
                arrayAnswer.remove(answerVariantOne) // удаляем этот ответ из массива
                setFreedomCheck(variantOne)
                flag = false // снимаем флаг
            }
        }
        variantTwo.setText(mBankQuestionCorn[mCurrIndex].answerTwo)
        variantTwo.setOnCheckedChangeListener { buttonView: CompoundButton, isChecked: Boolean ->
            val textVariantTwo: String = variantTwo.text.toString()
            var answerVariantTwo: Int = 0
            if (variantTwo.isChecked()) {
                flag = true // пользователь выбрал вариант ответа
                setEnableCheck(variantTwo)
                if (mBankQuestionCorn[mCurrIndex].answerOne == textVariantTwo)
                    answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyOne)
                if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantTwo)
                    answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyTwo)
                if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantTwo)
                    answerVariantTwo = (mBankQuestionCorn[mCurrIndex].keyThree)
                arrayAnswer.add(answerVariantTwo)
            } else {
                Log.d(TAG, "сняли флаг во втором поле и перешли в поле else")
                arrayAnswer.remove(answerVariantTwo)
                setFreedomCheck(variantTwo)
                flag = false // снимаем флаг
            }
        }
        variantThree.setText(mBankQuestionCorn[mCurrIndex].answerThree)
        variantThree.setOnCheckedChangeListener { buttonView: CompoundButton, isChecked: Boolean ->
            val textVariantThree: String = variantThree.text.toString()
            var answerVariantThree: Int = 0
            if (variantThree.isChecked()) {
                flag = true // пользователь выбрал вариант ответа
                setEnableCheck(variantThree)
                if (mBankQuestionCorn[mCurrIndex].answerOne == textVariantThree)
                    answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyOne)
                if (mBankQuestionCorn[mCurrIndex].answerTwo == textVariantThree)
                    answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyTwo)
                if (mBankQuestionCorn[mCurrIndex].answerThree == textVariantThree)
                    answerVariantThree = (mBankQuestionCorn[mCurrIndex].keyThree)
                arrayAnswer.add(answerVariantThree)
            } else {
                Log.d(TAG, "сняли флаг в третьем поле и перешли в поле else")
                arrayAnswer.remove(answerVariantThree)
                setFreedomCheck(variantThree)
                flag = false // снимаем флаг
            }
        }
        mNextButton.setOnClickListener { // реализуем функциональность кнопки, выводящей следующий вопрос
            val intent = Intent(context, EndActivity::class.java) // создаем объект типа Интент. передаем ему текущий контекст и ссылку на класс EndActivity
            val arr : IntArray = arrayAnswer.toIntArray() // создаем массив типа IntArray на основе переменной arrayAnswer
            intent.putExtra("array", arr) // передаем в интент переменную arr и ключ "array"
            startActivity(intent) // передаем в метод интент и переходим к новой активити
           }
        setQuestion(mCurrIndex)
    }

    fun setQuestion(index: Int) { // этот метод устанавливает вопрос, отображающийся на текстовом поле приложения
        if (index == mBankQuestionCorn.size - 1) // если полученное число совпадает с индексом последнего вопроса, то
            mNextButton.setText(R.string.button_name_two) // меняеи наименование кнопки mNextButton
        mTextQuestionView.setText(mBankQuestionCorn[index].question) //устанавливаем текст вопроса и ответа
        variantOne.setText(mBankQuestionCorn[index].answerOne)
        variantTwo.setText(mBankQuestionCorn[index].answerTwo)
        variantThree.setText(mBankQuestionCorn[index].answerThree)
        numbersText.setText("Вопрос ${mCurrIndex + 1} / ${mBankQuestionCorn.size}") // устанавливаем текущий номер вопроса в текстовом поле вверху
    }

//    fun setCheck() { // с помощью данного метода мы снимаем флаги, оставленные пользователем
//        if (variantOne.isChecked())
//            variantOne.setChecked(false)
//        if (variantTwo.isChecked())
//            variantTwo.setChecked(false)
//        if (variantThree.isChecked())
//            variantThree.setChecked(false)
//    }

    fun setEnableCheck(check: CheckBox) { // когда пользователь выберет ответ, заблокируем остальные кнопки
        when (check) {
            variantOne -> {
                variantTwo.isClickable = false
                variantTwo.isFocusable = false
                variantThree.isClickable = false
                variantThree.isFocusable = false
                Log.d(TAG, "setEnableCheck заблокировали два и три")
            }
            variantTwo -> {
                variantOne.isClickable = false
                variantOne.isFocusable = false
                variantThree.isClickable = false
                variantThree.isFocusable = false
                Log.d(TAG, "setEnableCheck заблокировали один и три")
            }
            variantThree -> {
                variantOne.isClickable = false
                variantOne.isFocusable = false
                variantTwo.isClickable = false
                variantTwo.isFocusable = false
                Log.d(TAG, "setEnableCheck заблокировали один и два")
            }
            else -> Log.d(TAG, "ошибка в setEnableCheck")
        }
    }

    fun setFreedomCheck(check: CheckBox) {
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

    fun setFreedomCheck() {
        variantOne.isClickable = true
        variantOne.isFocusable = true
        variantTwo.isClickable = true
        variantTwo.isFocusable = true
        variantThree.isClickable = true
        variantThree.isFocusable = true
    }

    override fun onSaveInstanceState(outState: Bundle) { // на случай уничтожения активности сохраним ключевую информацию
        super.onSaveInstanceState(outState)
        var arr: IntArray = arrayAnswer.toIntArray() // имеющиеся ответы пользователя
        outState.putIntArray(
            "array",
            arr
        )  // помещаем это в Bundle, чтобы при работе метода onCreate записать данные значения в соответсвующие переменные
    }
}