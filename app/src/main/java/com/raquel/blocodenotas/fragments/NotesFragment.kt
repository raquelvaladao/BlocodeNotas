package com.raquel.blocodenotas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.data.Priority
import com.raquel.blocodenotas.data.User
import com.raquel.blocodenotas.data.UserViewModel
import com.raquel.blocodenotas.databinding.FragmentNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesFragment: Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private var priority = ""
    private val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container,false)
        val view = binding.root

        fun InitView() {
            binding.apply {
                greenButton.setOnClickListener {
                    greenButton.setImageResource(R.drawable.ic_baseline_done_24)
                    yellowButton.setImageResource(0)
                    redButton.setImageResource(0)
                    priority = "1"
                }

                yellowButton.setOnClickListener {
                    yellowButton.setImageResource(R.drawable.ic_baseline_done_24)
                    greenButton.setImageResource(0)
                    redButton.setImageResource(0)
                    priority = "2"
                }

                redButton.setOnClickListener {
                    redButton.setImageResource(R.drawable.ic_baseline_done_24)
                    greenButton.setImageResource(0)
                    yellowButton.setImageResource(0)
                    priority = "3"
                }
                doneButton.setOnClickListener {
                    insertDataToDatabase()
                }
            }
            //RECUPERAR A VIEWMODEL QUE EU CRIEI AQUI NO FRAGMENTO.
            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        }
        InitView()
        return view
    }

    //ADICIONAR O INPUT DA VIEW AO MEU BANCO DE DADOS
    private fun insertDataToDatabase(){
        binding.apply {
            val title = titleEditText.text.toString()
            val subtitle = subtitleEditText.text.toString()
            val notes = notesEditText.text.toString()

            if (inputCheck(title, subtitle, notes, priority)) {
                //SE FOR TRUE, CRIAR OBJETO DA CLASSE USER NO BANCO DE DADOS
                val userObj = User(0, title, subtitle, notes, currentDate, convertPriority(priority))
                //ADICIONAR AO BANCO DE DADOS
                mUserViewModel.addUser(userObj)
                DynamicToast.make(this@NotesFragment.requireActivity(), "Nota adicionada com sucesso")
                        .show()
                findNavController().navigate(R.id.action_notesFragment_to_listFragment3)
            } else
                DynamicToast.make(this@NotesFragment.requireActivity(), "Preencha todos os campos")
                        .show()
        }
    }
    //VER SE TODOS OS INPUT TÃO PREENCHIDOS
    private fun inputCheck (
        titleParam: String,
        subtitleParam: String,
        notesParam: String,
        priorityParam: String): Boolean {
        return!(titleParam.isEmpty() && subtitleParam.isEmpty() && notesParam.isEmpty() && priorityParam.isEmpty())
    }

    private fun convertPriority(priorityFactor: String): Priority{
        return when(priorityFactor) {
            "3" -> {
                Priority.HIGH
            }
            "2" -> {
                Priority.MEDIUM
            }
            "1" -> {
                Priority.LOW
            }
            else -> return Priority.LOW
        }
    }
}