package com.android.rickmorty.detail_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.rickmorty.commons.BaseFragment
import com.android.rickmorty.databinding.DetailFragmentBinding
import com.android.rickmorty.databinding.FavoriteFragmentBinding
import com.android.rickmorty.home_screen.vm.ViewModelCharacters
import com.android.rickmorty.profile_screen.vm.ViewModelProfile
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment: BaseFragment() {

    private val presenter: ViewModelProfile by viewModel()

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelCharacters by lazy {
        ViewModelProvider(this).get(ViewModelCharacters::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}