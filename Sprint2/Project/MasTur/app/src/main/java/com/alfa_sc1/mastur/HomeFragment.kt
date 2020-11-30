package com.alfa_sc1.mastur

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        fun newInstance(): HomeFragment{
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentSejarah = SejarahFragment()
        btn_sejarah.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, fragmentSejarah, SejarahFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
        val fragmentKuliner = KulinerFragment()
        btn_kuliner.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, fragmentKuliner, KulinerFragment::class.java.simpleName)
                        .addToBackStack(null)
                        .commit()
            }
        }
        val fragmentPenginapan = PenginapanFragment()
        btn_penginapan.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, fragmentPenginapan, PenginapanFragment::class.java.simpleName)
                        .addToBackStack(null)
                        .commit()
            }
        }
    }


}