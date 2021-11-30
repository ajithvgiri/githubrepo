package com.ajithvgiri.githubrepo.ui

import android.R
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajithvgiri.githubrepo.adapter.RepositoriesAdapter
import com.ajithvgiri.githubrepo.databinding.ActivityMainBinding
import com.ajithvgiri.offline.NoInternetConnectionBuilder
import com.ajithvgiri.offline.NoInternetConnectionSnackBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    var noInternetConnectionSnackBar: NoInternetConnectionSnackBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = RepositoriesAdapter()
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
        }

        viewModel.repositories.observe(this, {
            if (it?.isNotEmpty() == true) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.layoutEmptyResults.visibility = View.GONE

                binding.recyclerView.apply {
                    with(adapter as RepositoriesAdapter) {
                        repositories = it
                        notifyDataSetChanged()
                    }
                }
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.layoutEmptyResults.visibility = View.VISIBLE
            }
        })

        viewModel.error.observe(this, {
            Snackbar.make(findViewById(android.R.id.content),"$it",Snackbar.LENGTH_LONG).show()
            binding.recyclerView.visibility = View.GONE
            binding.layoutEmptyResults.visibility = View.VISIBLE
        })

        binding.buttonTryAgain.setOnClickListener {
            viewModel.loadRepositories()
        }

        viewModel.isLoading.observe(this, Observer{
            if (it){
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.layoutEmptyResults.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        noInternetConnectionSnackBar = NoInternetConnectionBuilder(applicationContext, findViewById(
            R.id.content)).build()
    }

    override fun onDestroy() {
        super.onDestroy()
        noInternetConnectionSnackBar?.destroy()
    }
}