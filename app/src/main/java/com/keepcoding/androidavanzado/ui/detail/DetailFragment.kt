package com.keepcoding.androidavanzado.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.keepcoding.androidavanzado.R
import com.keepcoding.androidavanzado.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var googleMap: GoogleMap
    private var heroLocation: MutableList<Double> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Llenando el Detail Fragment
        binding.superheroName.text = args.superhero.name
        binding.superheroImage.load(args.superhero.photo)
        binding.superheroDescription.text = args.superhero.description
        viewModel.getHeroLocations(args.superhero.id)

        viewModel.isFavoriteHero.observe(viewLifecycleOwner){
            if(viewModel.isFavoriteHero.value!!){
                binding.addFavorite.text = "Remove from favorites"
            }else{
                binding.addFavorite.text = "Add to favorites"
            }
        }
        viewModel.isFavorite(args.superhero.id)

        binding.addFavorite.setOnClickListener {
            viewModel.favorite(args.superhero.id)
            if(binding.addFavorite.text == "Add to favorites"){
                Toast.makeText(requireContext(), "Added to Favorites", Toast.LENGTH_LONG).show()
                //binding.addFavorite.setBackgroundColor(Color.LTGRAY)
                binding.addFavorite.text = "Remove favorites"
            }else{
                Toast.makeText(requireContext(), "Removed Favorites", Toast.LENGTH_LONG).show()
                binding.addFavorite.text = "Add to favorites"
            }
        }

        binding.goToFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_DetailFragment_to_FavoriteSuperHeroFragment)
        }

//        viewModel.hero.observe(viewLifecycleOwner) {
//            print(it.toString())
//        }


//            addFavorite.setOnClickListener{
//                viewModel.favorite(args.superhero.id)
//            }


        //Agregando favorito
//        viewModel.favorite(args.superhero.id)
//        viewModel.favorite.observe(viewLifecycleOwner){
//            val isFavorite = viewModel.favorite
//
//        }
        val mapFragment = childFragmentManager.findFragmentById((R.id.map)) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        viewModel.location.observe(viewLifecycleOwner) {
            heroLocation = viewModel.location.value!!
            for (location in 0 until heroLocation.size step 2) {
                val place = LatLng(heroLocation[location], heroLocation[location + 1])
                googleMap.addMarker(
                    MarkerOptions()
                        .position(place)
                        .title("${binding.superheroName.text} visited lat: ${heroLocation[location]}, lon: ${heroLocation[location + 1]}")

                )
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(place))
            }
        }
    }
}