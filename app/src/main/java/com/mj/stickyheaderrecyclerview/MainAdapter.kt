package com.mj.stickyheaderrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.byeol.lonnie.stickyheaderrecyclerviewkotlinsample.OuterListener
import kotlinx.android.synthetic.main.hold_item.view.*
import kotlinx.android.synthetic.main.list_item_1.view.*

class MainAdapter(var holderListener: HolderListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var outerListener: OuterListener
    private val recyclerItemList: ArrayList<AdapterItem> = ArrayList()

    init {

        outerListener = object : OuterListener {
            override fun isChanged(type: String) {
                when(type){
                    "1" -> {

                        val data = listOf(
                            Data("charge_tab", 17),
                            Data("charge_tab", 31),
                            Data("charge_tab", 26),
                            Data("charge_tab", 26),
                            Data("charge_tab", 24),
                            Data("charge_tab", 33),
                            Data("charge_tab", 40),
                            Data("charge_tab", 10),
                            Data("charge_tab", 10),
                            Data("charge_tab", 10),
                            Data("charge_tab", 10),
                            Data("charge_tab", 10),
                            Data("charge_tab", 10)
                        )
                        setListData(data)
                    }

                    "2" -> {
                        val data = listOf(
                            Data("spend_tab", 17),
                            Data("spend_tab", 31),
                            Data("spend_tab", 26),
                            Data("spend_tab", 26),
                            Data("spend_tab", 24),
                            Data("spend_tab", 33),
                            Data("spend_tab", 40),
                            Data("spend_tab", 10),
                            Data("spend_tab", 10),
                            Data("spend_tab", 10),
                            Data("spend_tab", 10),
                            Data("spend_tab", 10),
                            Data("spend_tab", 10)
                        )
                        setListData(data)
                    }

                    "3" -> {

                        val data = listOf(
                            Data("refund_tab", 17),
                            Data("refund_tab", 31),
                            Data("refund_tab", 26),
                            Data("refund_tab", 26),
                            Data("refund_tab", 24),
                            Data("refund_tab", 33),
                            Data("refund_tab", 40),
                            Data("refund_tab", 10),
                            Data("refund_tab", 10),
                            Data("refund_tab", 10),
                            Data("refund_tab", 10),
                            Data("refund_tab", 10),
                            Data("refund_tab", 10)
                        )
                        setListData(data)
                    }

                    "4" -> {
                        val data = listOf(
                            Data("ex_tab", 17),
                            Data("ex_tab", 31),
                            Data("ex_tab", 26),
                            Data("ex_tab", 26),
                            Data("ex_tab", 24),
                            Data("ex_tab", 33),
                            Data("ex_tab", 40),
                            Data("ex_tab", 10),
                            Data("ex_tab", 10),
                            Data("ex_tab", 10),
                            Data("ex_tab", 10),
                            Data("ex_tab", 10),
                            Data("ex_tab", 10)
                        )
                        setListData(data)
                    }
                }
            }
        }

        outerListener.isChanged("1")
    }

    companion object {
        const val TYPE_TOP = 0
        const val TYPE_HOLDER = 1
        const val TYPE_EMPTY = 2
        const val TYPE_LIST = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_TOP
            1 -> TYPE_HOLDER
            2 -> TYPE_EMPTY
            else -> TYPE_LIST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view: View
        return when (recyclerItemList[position].type) {
            TYPE_TOP -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.top_item, parent, false)
                TopViewHolder(view)
            }
            TYPE_HOLDER -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.hold_item, parent, false)
                HolderViewHolder(view)
            }
            TYPE_EMPTY -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_item, parent, false)
                EmptyViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_1, parent, false)
                ItemViewHolder(view)
            }
        }

    }

    fun isHolder(position: Int): Boolean {
        return recyclerItemList[position].type == TYPE_HOLDER
    }

    fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
        val lastIndex =
            if (position < recyclerItemList.size) position else recyclerItemList.size - 1
        for (index in lastIndex downTo 0) {
            val model = recyclerItemList[index]
            if (model.type == TYPE_HOLDER) {
                holderListener.isCover(true)
                return LayoutInflater.from(list.context)
                    .inflate(R.layout.hold_item, list, false)
            }
        }
        holderListener.isCover(false)
        return null
    }


     fun setListData(item: List<Data>) {
        recyclerItemList.clear()
        recyclerItemList.add(AdapterItem(TYPE_TOP, Data("", -1)))
        recyclerItemList.add(AdapterItem(TYPE_HOLDER, Data("", -1)))
        if (item.isEmpty()) {
            recyclerItemList.add(AdapterItem(TYPE_EMPTY, Data("", -1)))
        } else {
            for (data in item) {
                recyclerItemList.add(AdapterItem(TYPE_LIST, data))
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = recyclerItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopViewHolder -> {
                holder.bindView()
            }
            is HolderViewHolder -> {
                holder.bindView()
            }
            is EmptyViewHolder -> {
                holder.bindView()
            }
            is ItemViewHolder -> {
                val item: Data = recyclerItemList[position].objects
                holder.bindView(item)
            }
        }
    }

    inner class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {
        }
    }

    inner class HolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {



        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.charge_tab -> {
                    outerListener.isChanged("1")
                    Log.e("isChanged", "1")
                }
                R.id.spend_tab -> {
                    outerListener.isChanged("2")
                    Log.e("isChanged", "2")
                }
                R.id.refund_tab -> {
                    outerListener.isChanged("3")
                    Log.e("isChanged", "3")
                }
                R.id.ex_tab -> {
                    outerListener.isChanged("4")
                    Log.e("isChanged", "4r")
                }
            }
        }


        fun bindView() {
            Log.e("bindView", "bindView")

            itemView.charge_tab.setOnClickListener(this)
            itemView.spend_tab.setOnClickListener(this)
            itemView.refund_tab.setOnClickListener(this)
            itemView.ex_tab.setOnClickListener(this)
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Data) {
            itemView.tv_name.text = item.name
            itemView.tv_age.text = item.age.toString()
        }
    }
}