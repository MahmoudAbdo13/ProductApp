package com.grand.navigation.ui.login

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grand.navigation.R
import com.grand.navigation.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    //private val args: LoginFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usernameEditText = view.findViewById<EditText>(R.id.login_email_data)
        val passwordEditText = view.findViewById<EditText>(R.id.login_password_data)
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val binding = FragmentLoginBinding.bind(view)

        //usernameEditText.setText(args.username)
        val noAccountText =
            "<font color=" + context?.let { getColor(it,R.color.gray) } + ">" + getString(R.string.don_t_have) + "</font>"
        val signUpText =
            "<font color=" + context?.let { getColor(it,R.color.orange) } + ">" + getString(R.string.sign_up) + "</font>"

        binding.donTHave.setText(Html.fromHtml("$noAccountText $signUpText"))
        val confirm = view.findViewById<Button>(R.id.login_btn)
        confirm.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }
}