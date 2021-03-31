package com.android.rickmorty.splash_screen.ui

import android.content.Intent
import android.os.Bundle
import com.android.rickmorty.home_screen.HomeActivity
import com.android.rickmorty.commons.BaseActivity
import com.android.rickmorty.commons.Constants
import com.android.rickmorty.databinding.SplashScreenBinding

class SplashScreen : BaseActivity() {

    private var _binding: SplashScreenBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        object: Thread(){
            override fun run(){
                try{
                    sleep(Constants.LOADING_SLEEP)
                }catch (ie: InterruptedException){
                    ie.printStackTrace()
                }finally {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                }
            }
        }.start()

    }
}