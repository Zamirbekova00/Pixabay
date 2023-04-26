package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var page = 1
    var adapter = PixaAdapter(arrayListOf())
    var word = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            nextPageBtn.setOnClickListener {
                page++
                requestImages(page)
            }
            btnSearch.setOnClickListener {
                page = 1
                adapter.list.clear()
                requestImages(page)
            }

        }
    }

    private fun ActivityMainBinding.requestImages(page: Int) {
        RetrofitService().api.getImage(searchWordEt.text.toString(), page)
            .enqueue(object : retrofit2.Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    response.body()?.let {
                        adapter.addImages(it.hits)
                        recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("kalbusha", "onFailure:${t.message}")
                }
            })
    }
}