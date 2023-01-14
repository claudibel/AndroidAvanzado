package com.keepcoding.androidavanzado.ui.superherolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.androidavanzado.databinding.FragmentSuperheroListBinding
import com.keepcoding.androidavanzado.ui.commons.SuperHeroListAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class SuperHeroListFragment : Fragment() {

    private var _binding: FragmentSuperheroListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SuperHeroListViewModel by viewModels()

    private val adapter = SuperHeroListAdapter{
        findNavController().navigate(SuperHeroListFragmentDirections.actionSuperHeroListFragmentToDetailFragment(it))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSuperheroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            superheroList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            superheroList.adapter = adapter
            viewModel.heros.observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
        }
        viewModel.getHeros()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}