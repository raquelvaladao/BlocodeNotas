package com.raquel.blocodenotas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.data.Priority
import com.raquel.blocodenotas.data.User
import com.raquel.blocodenotas.viewmodel.UserViewModel
import com.raquel.blocodenotas.databinding.FragmentNotesBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesFragment: Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private var priority = ""
    private val currentDate: String =
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    //private val sharedFunctionsViewModel: SharedFunctionsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val view = binding.root

        fun initView() {
            binding.apply {
                greenButton.setOnClickListener {
                    setGivenColorToPriorityImageView(greenButton, yellowButton, redButton)
                    priority = "1"
                }

                yellowButton.setOnClickListener {
                    setGivenColorToPriorityImageView(yellowButton, greenButton, redButton)
                    priority = "2"
                }

                redButton.setOnClickListener {
                    setGivenColorToPriorityImageView(redButton, greenButton, yellowButton)
                    priority = "3"
                }
                doneButton.setOnClickListener {
                    insertDataToDatabase()
                }
            }
            //RECUPERAR A VIEWMODEL QUE EU CRIEI AQUI NO FRAGMENTO.
            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        }
        initView()
        return view
    }

    //ADICIONAR O INPUT DA VIEW AO MEU BANCO DE DADOS
    private fun insertDataToDatabase() {
        binding.apply {
            val title = titleEditText.text.toString()
            val subtitle = subtitleEditText.text.toString()
            val notes = notesEditText.text.toString()

            if (inputCheck(title, notes)) {
                //SE FOR TRUE, CRIAR OBJETO DA CLASSE USER NO BANCO DE DADOS
                val userObj = User(
                    0,
                    title,
                    subtitle,
                    notes,
                    currentDate,
                        convertPriorityViewToModel(priority)
                )
                //ADICIONAR AO BANCO DE DADOS
                mUserViewModel.addUser(userObj)
                DynamicToast.make(
                    this@NotesFragment.requireActivity(),
                    "Nota adicionada com sucesso"
                )
                    .show()
                findNavController().navigate(R.id.action_notesFragment_to_listFragment3)
            } else
                DynamicToast.make(this@NotesFragment.requireActivity(), "Preencha todos os campos")
                    .show()
        }
    }

    companion object {
        fun inputCheck(
                titleParam: String,
                notesParam: String
        ): Boolean {
            return !(titleParam.isEmpty() &&  notesParam.isEmpty())
        }

        fun convertPriorityViewToModel(priorityFactor: String): Priority {
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

        fun setGivenColorToPriorityImageView(priorityButton: ImageView,
                                             otherPriorityButton1: ImageView,
                                             otherPriorityButton2: ImageView
        ){
            priorityButton.setImageResource(R.drawable.ic_baseline_done_24)
            otherPriorityButton1.setImageResource(0)
            otherPriorityButton2.setImageResource(0)
        }
    }

}
