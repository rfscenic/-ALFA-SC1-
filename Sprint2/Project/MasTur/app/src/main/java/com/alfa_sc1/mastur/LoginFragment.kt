package com.alfa_sc1.mastur

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.alfa_sc1.mastur.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(): LoginFragment{
            val fragment = LoginFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnFrgLogin.setOnClickListener(this)
        binding.btnEmail.setOnClickListener(this)
        binding.btnPhone.setOnClickListener(this)

        auth = Firebase.auth
    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_frg_login -> {
                signIn(binding.inputFrgEmail.text.toString(), binding.inputFrgPassword.text.toString())
            }
        }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    activity?.finish()
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                }else {
                    Toast.makeText(activity?.baseContext, "Authentication Faled", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun validateForm(): Boolean {
        var valid = true
        val email = binding.inputFrgEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.inputFrgEmail.error = "Required."
            valid = false
        } else {
            binding.inputFrgEmail.error = null
        }
        val password = binding.inputFrgPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.inputFrgPassword.error = "Required."
            valid = false
        } else {
            binding.inputFrgPassword.error = null
        }
        return valid
    }
}