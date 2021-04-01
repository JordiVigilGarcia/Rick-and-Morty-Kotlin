package com.android.rickmorty.home_screen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.data.models.RickMorty
import com.android.rickmorty.databinding.CharacterModelBinding
import com.bumptech.glide.Glide

class CharactersAdapter(private var mValues: List<RickMorty>?): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private lateinit var binding: CharacterModelBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.ViewHolder {
        binding = CharacterModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        mValues?.let {

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

            Glide.with(holder.itemView.context).load(it[position].image).into(holder.characterIMG)

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
    }
    private fun clearList() {
        val emptyList = listOf<RickMorty>()
        mValues = emptyList
        notifyItemRangeRemoved(0, 0)
    }
}
interface CellClickListener{
    fun onCellClickListener(transaction: RickMorty)
}