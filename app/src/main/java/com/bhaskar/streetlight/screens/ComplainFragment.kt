package com.bhaskar.streetlight.screens

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.bhaskar.streetlight.R
import com.bhaskar.streetlight.databinding.FragmentComplainBinding
import com.google.android.material.snackbar.Snackbar

class ComplainFragment : Fragment() {
    private lateinit var binding: FragmentComplainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComplainBinding.inflate(inflater, container, false)

        //fetch image from gallery
        val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.complaintimage.setImageURI(it)
        }
        binding.addImage.setOnClickListener {
            getImage.launch("image/*")
        }


        binding.submitButton?.setOnClickListener {
            if (binding.mobileNumber.text.toString() != "" || binding.Address.text.toString() != "") {

                val dialog = Dialog(requireContext())
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(true)
                dialog.setContentView(R.layout.custom_layout_submit)
                dialog.findViewById<Button>(R.id.button_close).setOnClickListener {
                    dialog.dismiss()
                    findNavController(requireView()).navigate(R.id.action_complainFragment_to_trackFragment)
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