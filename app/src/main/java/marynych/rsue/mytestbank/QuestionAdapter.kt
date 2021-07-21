package marynych.rsue.mytestbank

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class QuestionAdapter(fragment:FragmentActivity, val list:MutableList<Question>) : FragmentStateAdapter(fragment){
    val TAG = "Egor"
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "мы в createFragment")
        val fragment = QuestionTest()
        Log.d(TAG, "создали фрагмент")
        fragment.arguments = Bundle().apply {
            putInt(ARG_PARAM, position)
            Log.d("Egor", "position: $position")
        }
        return fragment
    }
}