package com.raquel.blocodenotas.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.adapter.ListAdapter
import com.raquel.blocodenotas.viewmodel.UserViewModel
import com.raquel.blocodenotas.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter: ListAdapter by lazy { ListAdapter() }

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentListBinding.inflate(inflater, container,false)
       val view = binding.root

        //RECYCLERVIEW
        val recyclerView = binding.notesRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        //USERVIEWMODEL observa mudanças e notifica pro recyclerview
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, { user -> adapter.setData(user) })

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all-> {
                confirmWipe()
            }
            R.id.high_priority_sort -> {
                mUserViewModel.sortByHigh().observe(this, { adapter.setData(it) })
            }
            R.id.low_priority_sort -> {
                mUserViewModel.sortByLow().observe(this, { adapter.setData(it) })
            }
            R.id.no_priority_sort -> {
                mUserViewModel.readAllData.observe(this, { adapter.setData(it) })
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun confirmWipe() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim"){
            _,_->
            mUserViewModel.deleteAll()
            DynamicToast.make(this@ListFragment.requireActivity(), "Notas deletadas")
                    .show()
        }
        builder.setTitle("Tem certeza que deseja apagar tudo?")
        builder.setMessage("Não será possível recuperar os arquivos")
        builder.setNegativeButton("Não") { _, _ -> }
        builder.create().show()
    }

}