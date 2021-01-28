package com.alfa_sc1.mastur

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import com.alfa_sc1.mastur.databinding.FragmentProfilBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            currentUser.let { updateUI(it) }
        }
        else {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogOut.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLogOut -> {
                signOut()
            }
        }
    }

    private fun signOut() {
        auth.signOut()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    @SuppressLint("SetTextI18n")
    private fun updateUI(currentUser: FirebaseUser) {
        currentUser.let {
            val name = currentUser.displayName
            val phoneNumber = currentUser.phoneNumber
            val email = currentUser.email
            val photoUrl = currentUser.photoUrl
            val emailVerified = currentUser.isEmailVerified
            val uid = currentUser.uid

            binding.tvName.text = name
            if(TextUtils.isEmpty(name)){
                binding.tvName.text = "No Name"
            } else {
                binding.tvName.text = name
            }

            binding.tvEmail.text = email
            if(photoUrl !== null){
                Glide.with(this)
                    .load(photoUrl)
                    .into(binding.ivImage)
            }
    //            for (profile in it.providerData) {
    //                val providerId = profile.providerId
    //                if(providerId=="password" && emailVerified==true){
    //                    binding.btnEmailVerify.isVisible = false
    //                }
    //                if(providerId=="phone"){
    //                    binding.tvName.text = phoneNumber
    //                    binding.tvUserId.text = providerId
    //                }
    //            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profil, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // navigate to settings screen
                val mIntent = Intent(activity, SettingActivity::class.java)
                startActivity(mIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



