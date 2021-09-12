package com.gadidev.momomovies.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gadidev.momomovies.adapter.ComingSoonAdapter
import com.gadidev.momomovies.databinding.FragmentHomeBinding
import com.gadidev.momomovies.utils.ChangeToolbarTitle
import com.gadidev.momomovies.utils.Resource
import com.gadidev.momomovies.utils.autoCleared
import com.gadidev.momomovies.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(), ComingSoonAdapter.MovieItemListener {
    private var binding: FragmentHomeBinding by autoCleared()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ComingSoonAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ChangeToolbarTitle).showToolbar(true)
        (activity as ChangeToolbarTitle).updateTitle("Momomovies")
        setupRecyclerView()
        setupObservers()
    }

//    private fun initViewModel() {
//        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)
//
//        viewModel.movieList.observe(viewLifecycleOwner, {
//            val adapter = ComingSoonAdapter()
//            binding.rvComingSoon.adapter = adapter
//            adapter.setMovies(it)
//        })
//
//        viewModel.errorMessage.observe(viewLifecycleOwner, {
//            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//        })
//
//        viewModel.loading.observe(viewLifecycleOwner, Observer {
//            if (it) {
//                binding.progressDialog.visibility = View.VISIBLE
//            } else {
//                binding.progressDialog.visibility = View.GONE
//            }
//        })
//
//        viewModel.getComingSoon()
//
//    }

    @SuppressLint("WrongConstant")
    private fun setupRecyclerView() {
        adapter = ComingSoonAdapter(this)
        binding.rvComingSoon.layoutManager = LinearLayoutManager(requireContext(),LinearLayout.HORIZONTAL,false)
        binding.rvComingSoon.adapter = adapter
    }

    private fun setupObservers() {
        Timber.tag("dapetGa").d("%s", viewModel.movies)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            if (it.data.isNullOrEmpty()) {
                viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
                    binding.progressDialog.visibility = if (isLoading) View.VISIBLE else View.GONE
                })
            } else {
            Timber.tag("status resource").d("%s", it.status)
            when (it.status) {
                Resource.Status.LOADING ->
                    binding.progressDialog.visibility = View.VISIBLE

                Resource.Status.SUCCESS -> {
                    binding.progressDialog.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                    Timber.tag("CekData").d("%s", ArrayList(it.data))
                }

                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
            }
        })
    }

    override fun onClickedMovie(movieId: String) {
        //        findNavController().navigate(
//            R.id.action_charactersFragment_to_characterDetailFragment,
//            bundleOf("id" to characterId)
//        )
    }

}