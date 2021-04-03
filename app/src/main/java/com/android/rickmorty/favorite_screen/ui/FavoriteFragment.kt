package com.android.rickmorty.favorite_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.rickmorty.R
import com.android.rickmorty.commons.BaseFragment
import com.android.rickmorty.databinding.FavoriteFragmentBinding
import com.android.rickmorty.databinding.ProfileFragmentBinding
import com.android.rickmorty.home_screen.vm.ViewModelCharacters
import com.android.rickmorty.profile_screen.vm.ViewModelProfile
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment: BaseFragment() {

    private val presenter: ViewModelProfile by viewModel()

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelCharacters by lazy {
        ViewModelProvider(this).get(ViewModelCharacters::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView8.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
            }
        })

        initFav()
    }

    fun initFav(){
        presenter.favcharacter.observe(viewLifecycleOwner, Observer {
            if (it.toString().equals("-1")){
                binding.textView18.text = context?.resources?.getString(R.string.emptyfavorite)
            }else{
                viewModel.rickAndMortyData.observe(viewLifecycleOwner, Observer {rickmorty ->
                    binding.textView18.text = rickmorty[it].name
                    binding.textView27.text = rickmorty[it].name + " · " + rickmorty[it].location.name
                    binding.textView28.text = rickmorty[it].name
                    binding.textView30.text = "Aquí se hace un recuento de todos los episodios que sale " + rickmorty[it].name
                    binding.textView31.text = "Género: " + rickmorty[it].gender
                    binding.textView32.text = "Estado: " + rickmorty[it].status
                    binding.textView33.text = rickmorty[it].episode.size.toString()

                    activity?.let {it1 ->
                        Glide.with(it1).load(rickmorty[it].image).into(binding.ivRICK)
                    }

                })
            }
        })
    }

}