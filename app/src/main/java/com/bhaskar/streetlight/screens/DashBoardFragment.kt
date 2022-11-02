package com.bhaskar.streetlight.screens

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.bhaskar.streetlight.R
import com.bhaskar.streetlight.databinding.FragmentDashBoardBinding


class DashBoardFragment : Fragment() {
    private lateinit var binding: FragmentDashBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userName = DashBoardFragmentArgs.fromBundle(requireArguments()).userName

        binding = FragmentDashBoardBinding.inflate(inflater, container, false)

        binding.complainCard.setOnClickListener {
            findNavController(requireView()).navigate(R.id.complainFragment)
        }

        binding.newApplicationCard.setOnClickListener {
            findNavController(requireView()).navigate(R.id.newApplicationFragment)
        }

        binding.feedbackCard.setOnClickListener {
            findNavController(requireView()).navigate(R.id.feedbackFragment)
        }

        binding.trackCard.setOnClickListener {

            findNavController(requireView()).navigate(R.id.action_dashBoardFragment_to_trackFragment)
        }

        binding.profileButton.setOnClickListener {

            val dialog = Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.user_profile_layout)
            dialog.findViewById<TextView>(R.id.user_name).text = userName
            dialog.findViewById<Button>(R.id.button_close).setOnClickListener {
                dialog.dismiss()
                findNavController(requireView()).navigate(R.id.action_dashBoardFragment_to_loginScreen)
            }
            dialog.show()
        }
        return binding.root
    }


}