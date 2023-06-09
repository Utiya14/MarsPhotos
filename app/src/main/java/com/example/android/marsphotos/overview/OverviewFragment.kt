package com.example.android.marsphotos.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.marsphotos.databinding.FragmentOverviewBinding

//Fragment ini menampilkan status transaksi layanan web foto Mars.
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Mengizinkan Data Binding untuk Mengamati LiveData dengan siklus hidup Fragment ini
        binding.lifecycleOwner = this

        // Memberikan akses binding ke OverviewViewModel
        binding.viewModel = viewModel

        // Mengatur adapter untuk RecyclerView photosGrid
        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root
    }
}