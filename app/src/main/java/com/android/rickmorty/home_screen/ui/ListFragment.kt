package com.android.rickmorty.home_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.rickmorty.R
import com.android.rickmorty.commons.BaseFragment
import com.android.rickmorty.databinding.ListFragmentBinding
import com.android.rickmorty.databinding.ProfileFragmentBinding
import com.android.rickmorty.home_screen.vm.ViewModelCharacters

class ListFragment : BaseFragment() {
    private lateinit var binding: ListFragmentBinding

    private lateinit var adapter: CharactersAdapter

    private val viewModel: ViewModelCharacters by lazy {
        ViewModelProvider(this).get(ViewModelCharacters::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  ListFragmentBinding.inflate(inflater)
        initList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userclick.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        })

        initList()
    }

    fun initList(){
        viewModel.rickAndMortyData.observe(viewLifecycleOwner, Observer {
            binding.itemGrid.layoutManager = LinearLayoutManager(activity)
            adapter = CharactersAdapter(it)
            binding.itemGrid.adapter = adapter
        })

    }



}