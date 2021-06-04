package com.raquel.blocodenotas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.data.UserViewModel
import com.raquel.blocodenotas.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentListBinding.inflate(inflater, container,false)
       val view = binding.root

        //RECYCLERVIEW
        val adapter = ListAdapter()
        val recyclerView = binding.notesRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //USERVIEWMODEL
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user -> adapter.setData(user) })

        binding.newNotesButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment3_to_notesFragment)
        }

        return view
    }

}