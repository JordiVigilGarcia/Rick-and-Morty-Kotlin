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

        //Finish Fragment

        binding.imageView8.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
            }
        })

        //Retrieve favorite character

        initFav()
    }

    fun initFav(){
        presenter.favcharacter.observe(viewLifecycleOwner, Observer {
            if (it.toString().equals("-1")){
                binding.textView18.text = context?.resources?.getString(R.string.emptyfavorite)
            }else{
                viewModel.rickMortyResults.observe(viewLifecycleOwner, Observer {rickmorty ->

                    binding.textView27.text = rickmorty[it].name + " · " + rickmorty[it].location.name

                    binding.textView28.text = rickmorty[it].name

                    binding.textView18.text = rickmorty[it].name

                    activity?.let {act ->
                        Glide.with(act).load(rickmorty[it].image).into(binding.ivRICK)
                    }

                    binding.textView30.text = context?.resources?.getString(R.string.episodedescript) + rickmorty[it].name

                    binding.textView31.text = rickmorty[it].episode.size.toString()

                    binding.textView33.text = context?.resources?.getString(R.string.ubicationstring) + rickmorty[it].name

                    binding.textView38.text = rickmorty[it].location.name

                    binding.textView44.text = context?.resources?.getString(R.string.spicedescript) + rickmorty[it].name

                    binding.textView48.text = rickmorty[it].species

                    binding.textView59.text = context?.resources?.getString(R.string.genderdescript) + rickmorty[it].name

                    binding.textView60.text = rickmorty[it].gender

                    binding.textView62.text = context?.resources?.getString(R.string.statedescript)+ rickmorty[it].name

                    binding.textView63.text = rickmorty[it].status


                })
            }
        })
    }

}