package com.alfa_sc1.mastur.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alfa_sc1.mastur.R

class ProfilFragment : Fragment() {

    private lateinit var profilViewModel: ProfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profilViewModel =
            ViewModelProvider(this).get(ProfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        profilViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}