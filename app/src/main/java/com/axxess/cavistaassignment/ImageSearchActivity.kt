package com.axxess.cavistaassignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.axxess.cavistaassignment.`interface`.ItemCallback
import com.axxess.cavistaassignment.adapter.SearchAdapter
import com.axxess.cavistaassignment.customview.PagingRecyclerView
import com.axxess.cavistaassignment.ext.afterTextChangedDelayed
import com.axxess.cavistaassignment.model.*

import com.cavistatest.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_image_search.*


class ImageSearchActivity : AppCompatActivity() {
    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java);
    }
    private var searchItems: ArrayList<AppBean> = ArrayList<AppBean>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_search)
        et_search_image.afterTextChangedDelayed {
            recyclerview.loadingPagination();
            searchViewModel.search(recyclerview.page, it);
        }

        val gridLayoutManager = GridLayoutManager(this, 2)
        val searchAdapter = SearchAdapter(this, searchItems, object : ItemCallback<Data, View> {
            override fun OnClick(t: Data, view: View) {
                openDetailsActivity(t, view)
            }

        });
        recyclerview.apply {
            setHasFixedSize(true)
            setPageChangeCallback {
                searchViewModel.search(page, et_search_image.text.toString());
            }
            layoutManager = gridLayoutManager;
            adapter = searchAdapter;
        }


        searchViewModel.mResponse.observe(this, Observer {
            if (it is ArrayList<*>) {
                update(it as ArrayList<Data>)
            } else if (it is ServerException) {
                updateWithError(it)
            }
        });
        searchViewModel.loader.observe(this, Observer {
            progress.visibility = if (it.showing) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

    }

    private fun updateWithError(serverException: ServerException) {
        val state = recyclerview.layoutManager?.onSaveInstanceState();
        searchItems.remove(PagerProgressModel())
        searchItems.remove(ErrorPageModel())

        searchItems.add(ErrorPageModel());
        recyclerview.canCheckNextPage();
        recyclerview.adapter?.notifyDataSetChanged()
        recyclerview.layoutManager?.onRestoreInstanceState(state);
    }

    private fun update(it: ArrayList<Data>) {
        val state = recyclerview.layoutManager?.onSaveInstanceState();
        if (recyclerview.page == PagingRecyclerView.INITIAL_PAGE) {
            searchItems.clear()
        }
        if (it.isNotEmpty()) {
            searchItems.remove(PagerProgressModel())
            searchItems.remove(ErrorPageModel())
            searchItems.addAll(it);
            recyclerview.canCheckNextPage();
        } else {
            recyclerview.disableLoadMore();
        }
        recyclerview.adapter?.notifyDataSetChanged()
        recyclerview.layoutManager?.onRestoreInstanceState(state);
    }

//    override fun OnClick(t: Data) {
//
//    }

    fun openDetailsActivity(data: Data, imageView: View) {
        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                getString(R.string.txt_img)
            )
        val mIntet = Intent(this, ImageDetailsActivity::class.java)
        mIntet.putExtra("data", data)
        startActivity(mIntet, activityOptionsCompat.toBundle())
    }
}