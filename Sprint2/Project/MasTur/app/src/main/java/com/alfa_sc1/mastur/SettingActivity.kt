package com.alfa_sc1.mastur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alfa_sc1.mastur.databinding.ActivitySettingBinding
import com.alfa_sc1.mastur.databinding.FragmentProfilBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class SettingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showPreferenceInForm()
        binding.button2.setOnClickListener(this)
    }
    fun showPreferenceInForm(){
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                // Id of the provider (ex: google.com)
                val providerId = profile.providerId

                // UID specific to the provider
                val uid = profile.uid

                // Name, email address, and profile photo Url
                val name = profile.displayName
                val email = profile.email
                val phone = profile.phoneNumber
                val photoUrl = profile.photoUrl

                binding.edtEmail.setText(email)
                binding.edtNama.setText(name)
                binding.edtNoTlp.setText(phone)
            }
        }
    }

    fun simpan(){
        val user = Firebase.auth.currentUser
        val nama = binding.edtNama.getText().toString()
        val email = binding.edtEmail.getText().toString()
        val phone = binding.edtNoTlp.getText().toString()
        val newPassword = binding.edtPassword.getText().toString()

        val profileUpdates = userProfileChangeRequest {
            displayName = nama
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Update Nama Berhasil", Toast.LENGTH_SHORT).show()
                }
            }
        user!!.updateEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Update Email Berhasil", Toast.LENGTH_SHORT).show()
                }
            }

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Update Password Berhasil", Toast.LENGTH_SHORT).show()
                }
            }

    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.button2 -> {
                simpan()
                val intent = Intent(this@SettingActivity, ProfilFragment::class.java)
                startActivity(intent)
            }
        }
    }

}