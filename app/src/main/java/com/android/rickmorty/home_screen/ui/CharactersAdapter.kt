package com.android.rickmorty.home_screen.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.android.data.models.RickMorty
import com.android.rickmorty.R
import com.android.rickmorty.databinding.CharacterModelBinding
import com.android.rickmorty.profile_screen.vm.ViewModelProfile
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class CharactersAdapter(private var mValues: List<RickMorty>?,
                        private val favClick: CellClickListener,
                        private val detailClick: CellClickListener,
                        private val presenter: ViewModelProfile,
                        private val lifecycleOwner: LifecycleOwner
                        ): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private lateinit var binding: CharacterModelBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.ViewHolder {
        binding = CharacterModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        mValues?.let {

            presenter.favcharacter.observe(lifecycleOwner, Observer {id ->
                if (id == holder.adapterPosition){
                    holder.characterFAV.setColorFilter(Color.parseColor("#ffd54f"))
                }else{
                    holder.characterFAV.setColorFilter(Color.parseColor("#808080"))
                }
            })

            if (it[position].name.toString().isEmpty()){
                holder.characterNAME.visibility = View.GONE
            }else{
                holder.characterNAME.visibility = View.VISIBLE
                holder.characterNAME.text = it[position].name
            }

            if (it[position].species.toString().isEmpty()){
                holder.characterSPECIES.visibility = View.GONE
            }else{
                holder.characterSPECIES.visibility = View.VISIBLE
                holder.characterSPECIES.text = it[position].species
            }

            if (it[position].gender.toString().isEmpty()){
                holder.characterGENDER.visibility = View.GONE
            }else{
                holder.characterGENDER.visibility = View.VISIBLE
                holder.characterGENDER.text = it[position].gender
            }

            if (it[position].location.toString().isEmpty()){
                holder.characterLOCATION.visibility = View.GONE
            }else{
                holder.characterLOCATION.visibility = View.VISIBLE

                val string = "Localización: "
                val string1 = it[position].location.name

                val spannableString = SpannableString(string + string1)

                val foregroundSpan = ForegroundColorSpan(Color.BLACK)

                val backgroundSpan = BackgroundColorSpan(holder.itemView.context.resources.getColor(
                    R.color.coloripple))

                spannableString.setSpan(foregroundSpan, 0, string.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(
                    backgroundSpan,
                    string.length,
                    string.length+string1.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.characterLOCATION.text = spannableString
            }

            holder.characterFAV.setOnClickListener { _ ->
                favClick.onClickListener(it[position], holder.adapterPosition)
            }

            holder.itemView.setOnClickListener { _ ->
                favClick.onDetailClickListener(it[position])
            }

            Glide.with(holder.itemView.context)
                .load(it[position].image)
                .into(holder.characterIMG)

        } ?: clearList()
    }

    override fun getItemCount(): Int {
        return mValues?.size ?: 0
    }

    inner class ViewHolder (mView: View): RecyclerView.ViewHolder(mView){
        val characterIMG = binding.characterIMG
        val characterNAME = binding.textView13
        val characterSPECIES = binding.textView14
        val characterGENDER = binding.textView15
        val characterLOCATION = binding.textView16
        val characterFAV = binding.imageView6
    }
    private fun clearList() {
        val emptyList = listOf<RickMorty>()
        mValues = emptyList
        notifyItemRangeRemoved(0, 0)
    }
}

interface CellClickListener{
    fun onClickListener(rickMorty: RickMorty, pos: Int)
    fun onDetailClickListener(rickMorty: RickMorty)
}



