package com.android.rickmorty.detail_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.rickmorty.R
import com.android.rickmorty.commons.BaseFragment
import com.android.rickmorty.databinding.DetailFragmentBinding
import com.android.rickmorty.databinding.FavoriteFragmentBinding
import com.android.rickmorty.detail_screen.vm.ViewModelShared
import com.android.rickmorty.home_screen.vm.ViewModelCharacters
import com.android.rickmorty.profile_screen.vm.ViewModelProfile
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment: BaseFragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelShared by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView9.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
        })

        val sharedCharacter = viewModel.shared.value
        sharedCharacter?.let {rickmorty ->
            binding.textView36.text = rickmorty.name

            activity?.let {
                Glide.with(it).load(rickmorty.image).into(binding.ivRICK2)
            }

            binding.textView39.text = rickmorty.name

            binding.textView41.text = context?.resources?.getString(R.string.episodedescript) + rickmorty.name

            binding.textView42.text = rickmorty.episode.size.toString()

            binding.textView46.text = context?.resources?.getString(R.string.ubicationstring) + rickmorty.name

            binding.textView47.text = rickmorty.location.name

            binding.textView51.text = context?.resources?.getString(R.string.spicedescript) + rickmorty.name

            binding.textView52.text = rickmorty.species

            binding.textView54.text = context?.resources?.getString(R.string.genderdescript) + rickmorty.name

            binding.textView55.text = rickmorty.gender

            binding.textView57.text = context?.resources?.getString(R.string.statedescript) + rickmorty.name

            binding.textView58.text = rickmorty.status

        }



    }

}