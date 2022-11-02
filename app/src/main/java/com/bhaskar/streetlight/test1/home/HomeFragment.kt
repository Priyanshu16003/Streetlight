package com.bhaskar.streetlight.test1.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhaskar.streetlight.R
import com.bhaskar.streetlight.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    lateinit var database: ComplainDatabase
    private var count = 0

    private lateinit var homeViewModel: HomeViewModel2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_home)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = HomeRecyclerViewAdapter()
        }


        //Room Database
//        database = Room.databaseBuilder(
//            requireContext(),
//            ComplainDatabase::class.java,
//            "Complaint_DB"
//        ).build()
//
//        GlobalScope.launch {
//            database.complaintDao().insert(ComplaintEntity(0,97241,"xyz",1,"firstCompalint"))
//        }
//        fun getData(view: View){
//            database.complaintDao(
//        }

        // lifecycle
//        lifecycle.addObserver(HomeLifeCycleObserver())
//        Log.d("MAIN", "onCreate: ")


        // ViewModel
//        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(10))[HomeViewModel::class.java]
//        binding.counter.text = homeViewModel.count.toString()
//        binding.apply {
//            btnIncrementCount.setOnClickListener {
//                homeViewModel.increment()
//                counter.text = homeViewModel.count.toString()
//            }
//        }

        //ViewModel + LiveData
//        binding.btnIncrementCount.setOnClickListener {
//            homeViewModel.increment()
//        }
//
//        homeViewModel = ViewModelProvider(this)[HomeViewModel2::class.java]
//        homeViewModel.count.observe(viewLifecycleOwner) {
//            binding.counter.text = it.toString()
//        }

        // ViewModel + LiveData + DataBinding
        homeViewModel = ViewModelProvider(this)[HomeViewModel2::class.java]
        binding.homeView = homeViewModel
        binding.lifecycleOwner = this


        binding.download.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                download()
            }

        }

        return binding.root
    }

    private fun download() {
        for (i in 1..1000)
            Log.i("HOME", "Downloading $i ${Thread.currentThread()}")
    }

}