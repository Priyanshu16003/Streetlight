package com.bhaskar.streetlight.login

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.bhaskar.streetlight.R
import com.bhaskar.streetlight.database.RegisterDatabase
import com.bhaskar.streetlight.database.RegisterRepository
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: com.bhaskar.streetlight.databinding.FragmentLoginBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login, container, false
            )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        binding.myLoginViewModel = loginViewModel

        binding.lifecycleOwner = this

        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                displayUsersList()
                loginViewModel.doneNavigatingRegiter()
            }
        }

        loginViewModel.errotoast.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {

                activity?.let { it1 ->
                    Snackbar.make(
                        it1.findViewById(android.R.id.content),
                        "username/password invalid", Snackbar.LENGTH_LONG
                    ).show()
                }
                loginViewModel.donetoast()
            }
        }

        loginViewModel.errotoastUsername.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                activity?.let { it1 ->
                    Snackbar.make(
                        it1.findViewById(android.R.id.content),
                        "User doesn't exist,please Register!", Snackbar.LENGTH_LONG
                    ).show()
                }
                loginViewModel.donetoastErrorUsername()
            }
        }

        loginViewModel.errorToastInvalidPassword.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                activity?.let { it1 ->
                    Snackbar.make(
                        it1.findViewById(android.R.id.content),
                        "Please check your Password", Snackbar.LENGTH_LONG
                    ).show()
                }
                loginViewModel.donetoastInvalidPassword()
            }
        }

        loginViewModel.navigatetoUserDetails.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                navigateUserDetails()
                loginViewModel.doneNavigatingUserDetails()
            }
        }


        return binding.root
    }


    private fun displayUsersList() {
        Log.i("MYTAG", "insidisplayUsersList")
        findNavController(requireView()).navigate(R.id.action_loginScreen_to_registrationFragment2)

    }

    private fun navigateUserDetails() {
        Log.i("MYTAG", "inside_display_UsersList")
        val userName =
            requireActivity().findViewById<EditText>(R.id.userNameTextField).text.toString()
        val action = LoginFragmentDirections.actionLoginScreenToDashBoardFragment(userName)
        findNavController(requireView()).navigate(action)
    }
}