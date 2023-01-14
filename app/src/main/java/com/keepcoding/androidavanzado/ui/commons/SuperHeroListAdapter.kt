package com.keepcoding.androidavanzado.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keepcoding.androidavanzado.R
import com.keepcoding.androidavanzado.data.local.model.SuperHeroLocal
import com.keepcoding.androidavanzado.databinding.ItemListBinding
import com.keepcoding.androidavanzado.domain.SuperHero

class SuperHeroListAdapter(private val clickListener : (SuperHero) -> (Unit)) :
    ListAdapter<SuperHero, SuperHeroListAdapter.SuperHeroViewHolder>(SuperHeroDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SuperHeroViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            val binding = ItemListBinding.bind(view)
            return SuperHeroViewHolder(binding)
        }

        override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

    inner class SuperHeroViewHolder(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var superHero: SuperHero
        init{
            binding.root.setOnClickListener{
                clickListener(superHero)
            }
        }
        fun bind(superHero: SuperHero){
            this.superHero = superHero
            with(binding){
                superheroName.text = superHero.name
                superheroImage.load(superHero.photo)
            }
        }
    }

    class SuperHeroDiffCallback : DiffUtil.ItemCallback<SuperHero>() {
        override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
            return oldItem == newItem
        }

    }
}