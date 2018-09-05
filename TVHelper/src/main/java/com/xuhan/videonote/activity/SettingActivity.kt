package com.xuhan.videonote.activity

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.xuhan.videonote.R
import com.xuhan.videonote.adapter.DiscoverRecyclerAdapter
import com.xuhan.videonote.adapter.MyDividerItemDecoration
import com.xuhan.videonote.adapter.SettingRecyclerAdapter
import com.xuhan.videonote.contants.Contants
import com.xuhan.videonote.movielistsubject.MovieListSubjectActivity
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import kotlinx.android.synthetic.main.activity_setting.view.*
import java.util.ArrayList

/**
 * @author xuhan on 18-8-30.
 */
class SettingActivity : AppCompatActivity(), SettingRecyclerAdapter.OnItemClickListener {

    private var mAdapter: SettingRecyclerAdapter? = null
    private var dataList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initToolbar()
        setSupportActionBar(toolbar)
        initData()
        initView()
        initListener()
    }

    private fun initToolbar() {
        toolbar.title = "设置"
        toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite))
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        toolbar.navigationIcon = getDrawable(R.drawable.icon_back_white)
        toolbar.overflowIcon = null
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.addItemDecoration(object : MyDividerItemDecoration() {
            override fun getItemOffsets(position: Int): MyDividerItemDecoration.Decoration {
                val decoration = MyDividerItemDecoration.ColorDecoration()
                decoration.bottom = 2
                decoration.decorationColor = resources.getColor(R.color.colorGrayLight)
                return decoration
            }
        })
        mAdapter = SettingRecyclerAdapter(this.dataList!!)
        mRecyclerView.adapter = mAdapter
        mAdapter?.setOnItemClickListener(this)
    }

    private fun initData() {
        dataList = ArrayList()
        dataList?.add("账号安全")
        dataList?.add("主题设置")
        dataList?.add("关于")
        dataList?.add("切换账号")
        dataList?.add("退出")
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, dataList?.get(position), Toast.LENGTH_SHORT).show()
    }

    private fun initListener() {
        toolbar.setNavigationOnClickListener { finish() }
    }

}