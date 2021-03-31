package com.android.rickmorty.home_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.rickmorty.commons.BaseActivity
import com.android.rickmorty.databinding.HomeActivityBinding

class HomeActivity : BaseActivity() {

    private var _binding: HomeActivityBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}