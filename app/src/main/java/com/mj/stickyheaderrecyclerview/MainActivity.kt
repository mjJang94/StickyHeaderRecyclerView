package com.mj.stickyheaderrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mj.stickyheaderrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setupAdapter()

        binding.flCover.setOnClickListener {
            Toast.makeText(this@MainActivity,"tr",Toast.LENGTH_SHORT).show()
        }

    }

    private fun setupAdapter() {
        adapter = MainAdapter(object: HolderListener {
            override fun isCover(bool: Boolean) {

                when(bool){
                    true ->{
                        binding.flCover.visibility = View.VISIBLE

                    }

                    false -> {
                        binding.flCover.visibility = View.GONE
                    }
                }
            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
        val sectionItemDecoration = RecyclerSectionItemDecoration(getSectionCallback())
        binding.recycler.addItemDecoration(sectionItemDecoration)
    }

    private fun getSectionCallback(): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            override fun isSection(position: Int): Boolean {
                return adapter.isHolder(position)
            }

            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                return adapter.getHeaderLayoutView(list, position)
            }
        }
    }

}
