package com.bhaskar.streetlight.register

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bhaskar.streetlight.R
import com.bhaskar.streetlight.database.RegisterDatabase
import com.bhaskar.streetlight.database.RegisterRepository
import com.bhaskar.streetlight.databinding.FragmentRegistrationBinding
import com.google.android.material.snackbar.Snackbar
import from.bhaskar.register.RegisterViewModelFactory


class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentRegistrationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_registration, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        binding.myViewModel = registerViewModel

        binding.lifecycleOwner = this

        registerViewModel.navigateto.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        }

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner) {
            Log.i("MYTAG", it.toString() + "000000000000000000000000")
        }


        registerViewModel.errotoast.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                activity?.let { it1 ->
                    Snackbar.make(
                        it1.findViewById(android.R.id.content),
                        "Please fill all fields", Snackbar.LENGTH_LONG
                    ).show()
                }
                registerViewModel.donetoast()
            }
        }

        registerViewModel.errotoastUsername.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.donetoastUserName()
            }
        }

        return binding.root
    }

    private fun displayUsersList() {
        Log.i("MYTAG", "insidisplayUsersList")
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dailog_layout)
        dialog.findViewById<Button>(R.id.button_close).setOnClickListener {
            dialog.dismiss()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_registrationFragment_to_loginScreen)
        }
        dialog.show()
    }

}