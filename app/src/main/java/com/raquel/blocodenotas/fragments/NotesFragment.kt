package com.raquel.blocodenotas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.databinding.FragmentNotesBinding

class NotesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentNotesBinding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        return binding.root

        binding.doneButton.setOnClickListener{

        }
    }

}