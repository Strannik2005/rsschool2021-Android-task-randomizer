package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var minValue: EditText
    private lateinit var maxValue: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        var min = 0 // or null?
        var max = 0 // or null?

        generateButton?.setOnClickListener {
            if (minValue.text.isEmpty() == true || maxValue.text.isEmpty() == true){                // Check fields are not empty
                Snackbar.make(view, "Заполните оба поля", Snackbar.LENGTH_SHORT).show()
            } else if (maxValue.text.toString().toInt() <= minValue.text.toString().toInt()) {
                Snackbar.make(view, "Максимальное значение должно быть больше минимального.", Snackbar.LENGTH_SHORT).show()
            } else {
                min = minValue.text.toString().toInt()
                max = maxValue.text.toString().toInt()
                //Snackbar.make(view, "Min: $min Max: $max", Snackbar.LENGTH_SHORT).show()
                fun Fragment.mainActivity() = requireActivity() as MainActivity
                mainActivity().openSecondFragment(min, max)

            }
            // TODO: send min and max to the SecondFragment
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}