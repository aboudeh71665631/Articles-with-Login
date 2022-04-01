package com.example.euriskocodechallenge.ui.home.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.euriskocodechallenge.databinding.FragmentNewsDetailsBinding
import com.example.euriskocodechallenge.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()

    val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val position = args.newsItemPosition

        viewModel.response.observe(viewLifecycleOwner) {
            if (it != null){
                binding.titleTv.text = it.news[position].title
                binding.articleImage.load(it.news[position].media[0].mediaMetadata[2].url)
                binding.abstractTv.text = it.news[position].abstract
                binding.authorTv.text = it.news[position].byline
                binding.updatedTv.text = it.news[position].updated
                binding.urlTv.text = it.news[position].url
            }
        }



        return view
    }

}