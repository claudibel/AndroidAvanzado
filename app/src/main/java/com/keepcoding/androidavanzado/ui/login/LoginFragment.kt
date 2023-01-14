package com.keepcoding.androidavanzado.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.keepcoding.androidavanzado.R
import com.keepcoding.androidavanzado.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(){

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    companion object{
        var TAG_USERNAME = ""
        var TAG_PASSWORD = ""
        var isUserLoggedIn = false
        var TOKEN = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.username.doAfterTextChanged {
            TAG_USERNAME = binding.username.text.toString()
            binding.login.isEnabled = TAG_PASSWORD.isNotEmpty()and(TAG_USERNAME.isNotEmpty()and(TAG_USERNAME.contains("@")and(TAG_PASSWORD.length>=3)))
        }
        binding.password.doAfterTextChanged {
            TAG_PASSWORD = binding.password.text.toString()
            binding.login.isEnabled = TAG_PASSWORD.isNotEmpty()and(TAG_USERNAME.isNotEmpty()and(TAG_USERNAME.contains("@")and(TAG_PASSWORD.length>=3)))
        }

        binding.login.setOnClickListener{
            binding.loginProgressbar.visibility = View.VISIBLE
            viewModel.token.observe(viewLifecycleOwner){
                TOKEN = it
                if(it!=null){
                    isUserLoggedIn = true
                findNavController().navigate(R.id.action_LoginFragment_to_SuperHeroListFragment)
                }else{
                Toast.makeText(requireContext(), "Incorrect Username or Password", Toast.LENGTH_LONG).show()
                }
            }
            viewModel.login()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}