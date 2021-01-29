package com.alfa_sc1.mastur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfa_sc1.mastur.adapter.ListSejarahMyDataAdapter
import com.alfa_sc1.mastur.databinding.ActivitySejarahBinding
import com.alfa_sc1.mastur.helper.REQUEST_ADD
import com.alfa_sc1.mastur.helper.REQUEST_UPDATE
import com.alfa_sc1.mastur.helper.RESULT_ADD
import com.alfa_sc1.mastur.helper.RESULT_DELETE
import com.alfa_sc1.mastur.helper.RESULT_UPDATE
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sejarah.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SejarahActivity : AppCompatActivity() {
    private lateinit var adapter: ListSejarahMyDataAdapter
    private lateinit var binding: ActivitySejarahBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySejarahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = Firebase.firestore
        auth = Firebase.auth

        binding.rvMydataSejarah.layoutManager = LinearLayoutManager(this)
        binding.rvMydataSejarah.setHasFixedSize(true)
        adapter = ListSejarahMyDataAdapter(this)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@SejarahActivity, SejarahAddUpdateActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD)
        }
        showRecyclerList()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        showRecyclerList()
    }
    private fun showRecyclerList() {
        GlobalScope.launch(Dispatchers.Main){
            progressbar.visibility = View.VISIBLE
            val sejarahList = ArrayList<MyDataSejarah>()
            val currentUser = auth.currentUser

            firestore.collection("MyDataSejarah")
                .whereEqualTo("uid", currentUser?.uid)
                .get()
                .addOnSuccessListener { result ->
                    progressbar.visibility = View.INVISIBLE
                    for (document in result) {
                        val id = document.id
                        val name = document.get("nama").toString()
                        val location = document.get("location").toString()
                        val url = document.get("url").toString()
                        sejarahList.add(MyDataSejarah(id, name, location ,url))
                    }
                    if (sejarahList.size > 0) {
                        binding.rvMydataSejarah.adapter = adapter
                        adapter.listSejarah = sejarahList
                    } else {
                        adapter.listSejarah.clear()
                        binding.rvMydataSejarah.adapter?.notifyDataSetChanged()
                        showSnackbarMessage("Tidak ada data saat ini")
                    }
                }
                .addOnFailureListener { exception ->
                    progressbar.visibility = View.INVISIBLE
                    Toast.makeText(
                        this@SejarahActivity, "Error adding document", Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.rvMydataSejarah, message, Snackbar.LENGTH_SHORT).show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            when (requestCode) {
                REQUEST_ADD -> if (resultCode == RESULT_ADD) {
                    showRecyclerList()
                    showSnackbarMessage("Satu item berhasil ditambahkan")
                }
                REQUEST_UPDATE ->
                    when (resultCode) {
                        RESULT_UPDATE -> {
                            showRecyclerList()
                            showSnackbarMessage("Satu item berhasil diubah")
                        }
                        RESULT_DELETE -> {
                            showRecyclerList()
                            showSnackbarMessage("Satu item berhasil dihapus")
                        }
                    }
            }
        }
    }
}