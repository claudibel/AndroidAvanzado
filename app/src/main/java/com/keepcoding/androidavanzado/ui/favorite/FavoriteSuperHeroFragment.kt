package com.keepcoding.androidavanzado.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.androidavanzado.databinding.FragmentFavoriteSuperheroBinding
import com.keepcoding.androidavanzado.ui.commons.SuperHeroListAdapter
import com.keepcoding.androidavanzado.ui.superherolist.SuperHeroListFragmentDirections
import com.keepcoding.androidavanzado.ui.superherolist.SuperHeroListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteSuperHeroFragment : Fragment() {

    private var _binding: FragmentFavoriteSuperheroBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteSuperHeroViewModel by viewModels()

    private val adapter = FavoriteSuperHeroAdapter()

//    private val adapter = SuperHeroListAdapter{
//        findNavController().navigate(SuperHeroListFragmentDirections.actionSuperHeroListFragmentToDetailFragment(it))
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteSuperheroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            favSuperheroList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            favSuperheroList.adapter = adapter
            viewModel.favorites.observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
        }
        viewModel.getFavoriteList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}