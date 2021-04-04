package com.android.rickmorty.home_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.data.models.RickMorty
import com.android.rickmorty.R
import com.android.rickmorty.commons.BaseFragment
import com.android.rickmorty.databinding.ListFragmentBinding
import com.android.rickmorty.databinding.ProfileFragmentBinding
import com.android.rickmorty.detail_screen.vm.ViewModelShared
import com.android.rickmorty.home_screen.vm.ViewModelCharacters
import com.android.rickmorty.profile_screen.vm.ViewModelProfile
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment(), CellClickListener {
    private lateinit var binding: ListFragmentBinding

    private lateinit var adapter: CharactersAdapter

    private val presenter: ViewModelProfile by viewModel()

    private val viewModel: ViewModelCharacters by lazy {
        ViewModelProvider(this).get(ViewModelCharacters::class.java)
    }

    private val sharedModel: ViewModelShared by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  ListFragmentBinding.inflate(inflater)
        initList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Open ProfileFragment.kt

        binding.userclick.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        })

        //Open FavoriteFragment.kt

        binding.imageView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }
        })

        //Check if API results is loading or not

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })

        //Retrieve characters List on recyclerView

        initList()

        //Retrieve favorite character and put the character image on CircleImageView

        initFavIMG()
    }

    fun initList(){
        viewModel.rickMortyResults.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewList.layoutManager = LinearLayoutManager(activity)
            adapter = CharactersAdapter(it, this, this, presenter, this)
            binding.recyclerViewList.adapter = adapter
        })

    }

    fun initFavIMG(){
        presenter.favcharacter.observe(viewLifecycleOwner, Observer {pos->
            if (pos.toString().equals("-1")){
                binding.userclick.setBackgroundResource(R.mipmap.ic_launcher)
            }else {
                viewModel.rickMortyResults.observe(viewLifecycleOwner, Observer {
                    activity?.let { it1 ->
                        Glide.with(it1)
                            .load(it[pos].image)
                            .into(binding.userclick)
                    }
                })
            }
        })
    }

    //This is onclicklistener of add to favorite

    override fun onClickListener(rickMorty: RickMorty, pos: Int) {
        Snackbar.make(binding.constarintlist, "Personaje añadido a tus favoritos", Snackbar.LENGTH_LONG).show()
        presenter.setFavCharacter(pos)
        adapter.notifyDataSetChanged()
    }

    //This is the onclicklistener of open character details

    override fun onDetailClickListener(rickMorty: RickMorty) {
        Toast.makeText(context, "Has pulsado en " + rickMorty.name, Toast.LENGTH_LONG).show()
        sharedModel.setCharacter(rickMorty)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }

}