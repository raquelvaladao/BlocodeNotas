package com.raquel.blocodenotas.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.viewmodel.UserViewModel
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
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)

        //USERVIEWMODEL
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user -> adapter.setData(user) })

        binding.newNotesButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment3_to_notesFragment)
        }

        binding.listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment3_to_updateFragment)
        }

        //menu personalizado.
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}