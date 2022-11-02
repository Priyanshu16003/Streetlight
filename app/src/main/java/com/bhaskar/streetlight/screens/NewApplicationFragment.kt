package com.bhaskar.streetlight.screens

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bhaskar.streetlight.R
import com.bhaskar.streetlight.databinding.FragmentNewApplicationBinding
import com.google.android.material.snackbar.Snackbar

class NewApplicationFragment : Fragment() {
    private lateinit var binding: FragmentNewApplicationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewApplicationBinding.inflate(inflater, container, false)

        binding.submitButton.setOnClickListener {
            if (binding.name.text.toString() != "" || binding.mobileNumber.text.toString() != "") {
                val dialog = Dialog(requireContext())
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.custom_layout_submit)
                dialog.findViewById<Button>(R.id.button_close).setOnClickListener {
                    dialog.dismiss()
                    Navigation.findNavController(requireView()).navigate(R.id.dashBoardFragment)
                }
                dialog.show()

            } else {
                activity?.let { it1 ->
                    Snackbar.make(
                        it1.findViewById(android.R.id.content),
                        "Please fill all fields", Snackbar.LENGTH_LONG
                    ).show()
                }
            }

        }
        return binding.root

    }

}