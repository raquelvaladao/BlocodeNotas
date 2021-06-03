package com.raquel.blocodenotas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.fragment.findNavController
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentListBinding.inflate(inflater, container,false)
       val view = binding.root

        binding.newNotesButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment3_to_notesFragment)
        }

        return view
    }

}