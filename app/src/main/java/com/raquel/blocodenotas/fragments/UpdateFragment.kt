package com.raquel.blocodenotas.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cn.pedant.SweetAlert.SweetAlertDialog
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.data.Priority
import com.raquel.blocodenotas.data.Priority.*
import com.raquel.blocodenotas.data.User
import com.raquel.blocodenotas.databinding.FragmentUpdateBinding
import com.raquel.blocodenotas.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    //passagem de dados
    private val args by navArgs<UpdateFragmentArgs>()

    //new data
    private val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    private var priority = ""

    //update
    private val mUserViewModel: UserViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        fun initView() {

            bringDataArgsToUpdateView()

            binding.apply {
                updateGreenButton.setOnClickListener {
                    NotesFragment.setGivenColorToPriorityImageView(updateGreenButton, updateYellowButton, updateRedButton)
                    priority = "1"
                }

                updateYellowButton.setOnClickListener {
                    NotesFragment.setGivenColorToPriorityImageView(updateYellowButton, updateGreenButton, updateRedButton)
                    priority = "2"
                }

                updateRedButton.setOnClickListener {
                    NotesFragment.setGivenColorToPriorityImageView(updateRedButton, updateGreenButton, updateYellowButton)
                    priority = "3"
                }

                updateButton.setOnClickListener {
                    updateDataToDatabase()
                }
            }

        }
        initView()

        setHasOptionsMenu(true)
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if(item.itemId == R.id.delete_sigle) confirmRemoval()
        return super.onOptionsItemSelected(item)
    }



    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim"){
            _,_->
            mUserViewModel.deleteUser(args.currentItem)
            DynamicToast.make(this@UpdateFragment.requireActivity(), "Nota deletada")
                    .show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment3)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment3)

        }
        builder.setTitle("Tem certeza que deseja apagar ${args.currentItem.notesTitleDB}?")
        builder.setMessage("Não será possível recuperar o arquivo")
        builder.setNegativeButton("Não") { _, _ -> }
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun parsePriorityModelToView(enumPriority: Priority) {
       when (enumPriority) {
           LOW -> {
               binding.apply {
                   NotesFragment.setGivenColorToPriorityImageView(
                           updateGreenButton,
                           updateYellowButton,
                           updateRedButton
                   )
               }
               priority = "1"
           }
           MEDIUM -> {
               binding.apply {
                   NotesFragment.setGivenColorToPriorityImageView(
                           updateYellowButton,
                           updateGreenButton,
                           updateRedButton
                   )
               }
               priority = "2"
           }
           HIGH -> {
               binding.apply {
                   NotesFragment.setGivenColorToPriorityImageView(
                           updateRedButton,
                           updateGreenButton,
                           updateYellowButton
                   )
               }
               priority = "3"
           }
        }

    }

    private fun bringDataArgsToUpdateView() {
        binding.apply {
            titleEditText.setText(args.currentItem.notesTitleDB)
            updateSubtitleEditText.setText(args.currentItem.notesSubtitleDB)
            updateNotesEditText.setText(args.currentItem.notesContent)
        }
        when (args.currentItem.notesPriorityDB) {
            LOW -> parsePriorityModelToView(LOW)
            MEDIUM -> parsePriorityModelToView(MEDIUM)
            HIGH -> parsePriorityModelToView(HIGH)
        }
    }

    private fun updateDataToDatabase() {
        binding.apply {
            val title = titleEditText.text.toString()
            val subtitle = updateSubtitleEditText.text.toString()
            val notes = updateNotesEditText.text.toString()

            if (NotesFragment.inputCheck(title, notes)) {
                //SE FOR TRUE, CRIAR OBJETO DA CLASSE USER NO BANCO DE DADOS
                val updatedItem = User(
                        args.currentItem.id,
                        title,
                        subtitle,
                        notes,
                        currentDate,
                        NotesFragment.convertPriorityViewToModel(priority)
                )
                //ADICIONAR AO BANCO DE DADOS
                //MVIEWMODEL
                mUserViewModel.updateUser(updatedItem)
                DynamicToast.make(this@UpdateFragment.requireActivity(), "Nota salva com sucesso")
                    .show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment3)
            } else
                DynamicToast.make(this@UpdateFragment.requireActivity(), "Preencha todos os campos")
                    .show()
        }
    }

}