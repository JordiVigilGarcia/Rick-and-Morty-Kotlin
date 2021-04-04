package com.android.rickmorty.profile_screen.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.rickmorty.R
import com.android.rickmorty.commons.BaseFragment
import com.android.rickmorty.databinding.EditProfileFragmentBinding
import com.android.rickmorty.databinding.ListFragmentBinding
import com.android.rickmorty.profile_screen.vm.ViewModelProfile
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment : BaseFragment() {

    private var _binding: EditProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val presenter: ViewModelProfile by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = EditProfileFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //This is for close fragment

        binding.imageView2.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
            }
        })

        //Check if name exists

        presenter.name.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                binding.editTextTextPersonName.setHint(getString(R.string.hintname))
            }else{
                binding.editTextTextPersonName.setText(it)
            }
        })

        //Check if username exists

        presenter.username.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                binding.editTextTextPersonName2.setHint(getString(R.string.hinusername))
            }else{
                binding.editTextTextPersonName2.setText(it)
            }
        })

        //Check if description exists

        presenter.description.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                binding.editTextTextPersonName3.setHint(getString(R.string.hindescript))
            }else{
                binding.editTextTextPersonName3.setText(it)
            }
        })

        //OnClick for create user on Rick&Morty App

        binding.imageView5.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                fetchData()
            }
        })

    }

    fun fetchData(){
        presenter.setName(binding.editTextTextPersonName.text.toString())
        presenter.setUsername(binding.editTextTextPersonName2.text.toString())
        presenter.setDescription(binding.editTextTextPersonName3.text.toString())

        findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
    }

}