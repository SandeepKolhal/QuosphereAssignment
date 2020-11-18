package com.sk.quosphereassignment.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.sk.quosphereassignment.R
import com.sk.quosphereassignment.adapters.ImageListAdapter
import com.sk.quosphereassignment.models.ImageData
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity(), ImageListAdapter.OnClickListener {

    private val imageListAdapter: ImageListAdapter by lazy {
        ImageListAdapter(this, this)
    }

    private var isNormalList = true

    private val imageList = mutableListOf<ImageData>()
    private var lastPosition = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        imageListView.adapter = imageListAdapter
        setDataToList()
    }

    private fun setDataToList() {
        updateSpanCount(isNormalList)
        imageList.add(ImageData(R.drawable.element1, 0))
        imageList.add(ImageData(R.drawable.element2, 1))
        imageList.add(ImageData(R.drawable.element3, 2))
        imageList.add(ImageData(R.drawable.element4, 3))
        imageList.add(ImageData(R.drawable.element6, 4))

        imageListAdapter.setData(imageList)
    }

    private fun updateSpanCount(isNormal: Boolean) {
        val layoutManager = GridLayoutManager(this, 2);

        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (isNormal) {
                    1
                } else if (position == 0) {
                    2
                } else {
                    1
                }
            }
        }

        imageListView.layoutManager = layoutManager
    }

    override fun onClick(data: ImageData, position: Int) {

        if (position == 0 && !isNormalList) {
            isNormalList = true
            updateSpanCount(isNormalList)
            Handler(Looper.getMainLooper()).postDelayed({
                Collections.swap(imageList, 0, lastPosition)
                imageListAdapter.notifyItemMoved(0, lastPosition)
            }, 100)
        } else if (isNormalList) {
            isNormalList = false


            Collections.swap(imageList, position, 0)
            imageListAdapter.notifyItemMoved(position, 0)

            Handler(Looper.getMainLooper()).postDelayed({
                updateSpanCount(isNormalList)
            }, 50)

            lastPosition = position
        } else {
            Collections.swap(imageList, position, 0)
            imageListAdapter.notifyItemMoved(position, 0)
            lastPosition = position
        }
    }
}