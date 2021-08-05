package com.raquel.blocodenotas.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.databinding.FragmentNotesBinding
import com.raquel.blocodenotas.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    //passagem de dados
    private val args by navArgs<UpdateFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = FragmentUpdateBinding.inflate(inflater, container,false)
        val view = binding.root

        fun InitView(){
            binding.updateButton.setOnClickListener {
                //updateDataToDatabase
            }
        }

        InitView()
        return view
    }



    }


