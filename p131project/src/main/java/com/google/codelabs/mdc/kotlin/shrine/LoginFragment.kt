package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private var nextBtn: Button? = null
    private var passwordTextInput: TextInputLayout? = null
    private var passwordEditText: TextInputEditText? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextBtn = view.findViewById(R.id.next_button)
        passwordTextInput = view.findViewById(R.id.password_text_input)
        passwordEditText = view.findViewById(R.id.password_edit_text)

        notValid()
        valid()
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun notValid(){
        nextBtn?.setOnClickListener {
            if (!isPasswordValid(passwordEditText?.text!!)) {
                passwordTextInput?.error = getString(R.string.shr_error_password)
            } else {
                passwordTextInput?.error = null
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        }
    }

    private fun valid(){
        passwordEditText?.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(passwordEditText!!.text!!)) {

                passwordTextInput?.error = null
            }
            false
        }
    }

}
