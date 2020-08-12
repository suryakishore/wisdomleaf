package com.example.wisdomleaf

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.daytoday.searchreponse.PicSumPhotosResponse
import com.example.wisdomleaf.databinding.ActivityMainBinding

/**
 * This activity is used to show the picsum photos which are coming from server using recyclerview.
 */
class MainActivity : AppCompatActivity(), SelectItem {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private var mPhotosResponse = ArrayList<PicSumPhotosResponse>()
    lateinit var adapter: PicSumAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
        subscribePhotosData()
    }


    /**
     * initialize data related to user interface  and view model and initially
     * i am calling with an api to get the picsum photos.
     */
    private fun initializeData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = mainViewModel
        adapter = PicSumAdapter(mPhotosResponse, this)
        binding.rvPicsumPhotos.adapter = adapter
        mainViewModel.initializeRetrofit()
        mainViewModel.getPicSumPhotos()
        binding.tvRefreshPhotos.setOnClickListener {
            binding.pbLoadData.visibility = View.VISIBLE
            mainViewModel.getPicSumPhotos()
        }
    }

    /**
     * subscribe to photos data which is coming from server
     */
    private fun subscribePhotosData() {
        mainViewModel.onPhotosData().observe(this, Observer {
            binding.pbLoadData.visibility = View.GONE
            if (it != null) {
                mPhotosResponse.clear()
                mPhotosResponse.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onSelectItem(pos: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.app_name))
        builder.setMessage(
            """${resources.getString(R.string.author)} ${mPhotosResponse.get(pos).author}
${resources.getString(R.string.width)} ${mPhotosResponse.get(pos).width}
${resources.getString(R.string.height)} ${mPhotosResponse.get(pos).height}"""
        )
        builder.show()
    }

}