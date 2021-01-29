package com.alfa_sc1.mastur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alfa_sc1.mastur.databinding.ActivitySejarahAddUpdateBinding
import com.alfa_sc1.mastur.helper.EXTRA_POSITION
import com.alfa_sc1.mastur.helper.EXTRA_QUOTE
import com.alfa_sc1.mastur.helper.RESULT_ADD
import com.alfa_sc1.mastur.helper.RESULT_UPDATE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SejarahAddUpdateActivity : AppCompatActivity() , View.OnClickListener {
    private var isEdit = false
    private var myDataSejarah: MyDataSejarah? = null
    private var position: Int = 0
    private lateinit var binding: ActivitySejarahAddUpdateBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySejarahAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore
        auth = Firebase.auth
        myDataSejarah = intent.getParcelableExtra(EXTRA_QUOTE)
        if (myDataSejarah != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            myDataSejarah = MyDataSejarah()
        }
        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = "Ubah"
            btnTitle = "Update"
            myDataSejarah?.let {
                binding.edtNama.setText(it.nama)
                binding.edtLocation.setText(it.location)
                binding.edtUrl.setText(it.url)
            }!!
        } else {
            actionBarTitle = "Tambah"
            btnTitle = "Simpan"
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnSubmit.text = btnTitle
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_submit) {
            val title = binding.edtNama.text.toString().trim()
            val location = binding.edtLocation.text.toString().trim()
            val photo = binding.edtUrl.text.toString().trim()
            if (title.isEmpty()) {
                binding.edtNama.error = "Field can not be blank"
                return
            }
            if (isEdit) {
                val currentUser = auth.currentUser
                val user = hashMapOf(
                    "uid" to currentUser?.uid,
                    "nama" to title,
                    "location" to location,
                    "url" to photo
                )
                firestore.collection("MyDataSejarah").document(myDataSejarah?.id.toString())
                    .set(user)
                    .addOnSuccessListener {
                        setResult(RESULT_UPDATE, intent)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this@SejarahAddUpdateActivity, "Gagal mengupdate data", Toast.LENGTH_SHORT).show()
                    }
            } else {
                val currentUser = auth.currentUser
                val user = hashMapOf(
                    "uid" to currentUser?.uid,
                    "nama" to title,
                    "location" to location,
                    "url" to photo
                )
                firestore.collection("MyDataSejarah")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this@SejarahAddUpdateActivity,
                            "DocumentSnapshot added with ID: ${documentReference.id}",
                            Toast.LENGTH_SHORT).show()
                        setResult(RESULT_ADD, intent)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this@SejarahAddUpdateActivity, "Error adding document", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}