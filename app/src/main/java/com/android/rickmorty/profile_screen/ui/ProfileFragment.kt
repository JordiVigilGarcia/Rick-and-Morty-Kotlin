package com.android.rickmorty.profile_screen.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.rickmorty.R
import com.android.rickmorty.commons.BaseFragment
import com.android.rickmorty.databinding.ProfileFragmentBinding
import com.android.rickmorty.home_screen.vm.ViewModelCharacters
import com.android.rickmorty.profile_screen.vm.ViewModelProfile
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : BaseFragment() {

    private val presenter: ViewModelProfile by viewModel()

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelCharacters by lazy {
        ViewModelProvider(this).get(ViewModelCharacters::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView3.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
            }
        })

        binding.imageView4.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
            }
        })

        presenter.name.observe(viewLifecycleOwner, Observer {

                if (it.isEmpty()){
                    binding.textView8.text = resources.getString(R.string.emptyname)
                }else{
                    binding.textView8.text = it
                }

        })

        presenter.username.observe(viewLifecycleOwner, Observer { username ->
                if (username.isEmpty()){
                    binding.textView6.text = getString(R.string.emptyusernname)
                }else{
                    binding.textView6.text = username
                }

        })

        presenter.description.observe(viewLifecycleOwner, Observer { description ->
            if (description.isEmpty()){
                binding.textView12.text = getString(R.string.emptydescript)
            }else{
                binding.textView12.text = description
            }
        })

        presenter.favcharacter.observe(viewLifecycleOwner, Observer {

            if (it.toString().equals("-1")){
                binding.linearFAV.visibility = View.GONE
            }else {
                binding.linearFAV.visibility = View.VISIBLE
                viewModel.rickAndMortyData.observe(viewLifecycleOwner, Observer { rickmorty ->
                    binding.textView20.text = rickmorty[it].name
                    binding.textView21.text = rickmorty[it].species
                    binding.textView22.text = rickmorty[it].gender

                    val string = "Localización: "
                    val string1 = rickmorty[it].location.name

                    val spannableString = SpannableString(string + string1)

                    val foregroundSpan = ForegroundColorSpan(Color.BLACK)

                    val backgroundSpan = BackgroundColorSpan(resources.getColor(
                        R.color.coloripple))

                    spannableString.setSpan(foregroundSpan, 0, string.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    spannableString.setSpan(
                        backgroundSpan,
                        string.length,
                        string.length+string1.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    binding.textView23.text = spannableString

                    activity?.let { it1 ->
                        Glide.with(it1).load(rickmorty[it].image)
                            .into(binding.characterIMG2)
                    }

                })
            }
        })

        //BINDING ONCLICK FOR REMOVE FAVORITE CHARACTER
        binding.imageView7.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                presenter.setFavCharacter(-1)
                Snackbar.make(binding.constraintProfile, "Personaje retirado como favorito", Snackbar.LENGTH_LONG)
                    .show()
            }
        })


    }

}