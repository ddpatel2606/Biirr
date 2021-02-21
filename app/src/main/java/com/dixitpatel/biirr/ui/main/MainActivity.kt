package com.dixitpatel.biirr.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dixitpatel.biirr.R
import com.dixitpatel.biirr.constant.DEFAULT_IMAGE
import com.dixitpatel.biirr.databinding.ActivityMainBinding
import com.dixitpatel.biirr.databinding.ItemLoadMoreBinding
import com.dixitpatel.biirr.databinding.RowItemAllBinding
import com.dixitpatel.biirr.extension.recyclerViewAnimate
import com.dixitpatel.biirr.model.BeerModel
import com.dixitpatel.biirr.network.ApiInterface
import com.dixitpatel.biirr.network.AuthStatus
import com.dixitpatel.biirr.ui.base.BaseActivity
import com.dixitpatel.biirr.ui.detail.DetailViewActivity
import com.dixitpatel.biirr.utils.Alerts
import com.dixitpatel.biirr.utils.CommonAdapter
import com.dixitpatel.biirr.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 *  Main Activity : beer Listing Activity
 */
class MainActivity : BaseActivity<MainActivityViewModel?>(), SwipeRefreshLayout.OnRefreshListener
{
    // Binding view and initialized as lazy
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    @Inject
    lateinit var model: MainActivityViewModel

    @Inject
    lateinit var apiInterface : ApiInterface

    override fun getViewModel(): MainActivityViewModel {
        return model
    }

    private var backPressedTime: Long = 0

    private var offset = 1
    private var isNextPage = false
    private var isLoadMore = false
    private var isRefresh = false

    var mAdapter: CommonAdapter<BeerModel>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.let {
            setSupportActionBar(it)
            it.title = getString(R.string.app_name)
            it.setNavigationOnClickListener {
                        onBackPressed()
            }
        }

        binding.viewModel = model
        binding.lifecycleOwner = this

        registerObserver()
        fetchBeerList()

    }

    // Register Listing observer
    private fun registerObserver()
    {
        binding.lifecycleOwner?.let {
            model.mainViewRepository.beerApiResult().observe(
                it, androidx.lifecycle.Observer
            { result ->

                when (result.status) {
                    AuthStatus.LOADING -> {

                        if (!isLoadMore) {
                            binding.shimmerLayout.showShimmer(true)
                            binding.shimmerLayout.visibility = View.VISIBLE
                        }
                        binding.tvNoResultTxt.visibility = View.GONE
                    }
                    AuthStatus.ERROR -> {

                        isRefresh = false
                        Alerts.showSnackBar(this, result.message)
                        binding.shimmerLayout.hideShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                    }
                    AuthStatus.SUCCESS -> {

                        isRefresh = false

                        binding.shimmerLayout.hideShimmer()
                        binding.shimmerLayout.visibility = View.GONE

                        if (result.data?.data?.isNotEmpty()!!) {

                            if (result.data.currentPage != result.data.numberOfPages) {
                                isNextPage = true
                                offset += 1
                            } else {
                                isNextPage = false
                            }

                            Timber.e("Timber : offset $offset")

                            val arrData: ArrayList<BeerModel?> = result.data.data

                            if (isLoadMore) {
                                mAdapter?.stopLoadMore(arrData)
                            } else {
                                if (isRefresh) {
                                    if (arrData.size > 0) {
                                        binding.tvNoResultTxt.visibility = View.INVISIBLE
                                        binding.myRecyclerView.visibility = View.VISIBLE
                                        setAdapter(arrData)
                                    }
                                    onItemsLoadComplete()
                                } else {
                                    if (arrData.size > 0) {
                                        binding.myRecyclerView.visibility = View.VISIBLE
                                        setAdapter(arrData)
                                    } else {
                                        if (isRefresh) {
                                            onItemsLoadComplete()
                                        }
                                        binding.myRecyclerView.visibility = View.GONE
                                        binding.tvNoResultTxt.visibility = View.VISIBLE
                                    }
                                }
                            }
                            mAdapter?.setMoreDataAvailable(isNextPage)

                        } else {
                            binding.tvNoResultTxt.visibility = View.VISIBLE
                            binding.myRecyclerView.visibility = View.GONE
                        }
                    }
                }
            })
        }

        binding.swipeRefreshLayout.setOnRefreshListener(this)
    }

    // fetch beer Listing data
    private fun fetchBeerList()
    {
        if(Utils.isNetworkAvailable(this))
        {
            apiInterface.let { model.mainViewRepository.beerApiCall(offset, apiInterface) }
        } else {
            Alerts.showSnackBar(this, getString(R.string.internet_not_available))
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 2500 > System.currentTimeMillis()) {
            super.onBackPressed()
            ActivityCompat.finishAffinity(this)
        } else {
            Alerts.showSnackBar(
                    this@MainActivity, resources.getString(R.string.double_press_exit)
            )
        }
        backPressedTime = System.currentTimeMillis()
    }


    // onRefresh call when swipe to refresh called.
    override fun onRefresh() {
        try {
            offset = 1
            isLoadMore = false
            isRefresh = true
            mAdapter?.let {
                it.getData().clear()
                it.notifyDataSetChanged()
            }

            fetchBeerList()

            if (binding.swipeRefreshLayout.isRefreshing) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // item load completed
    private fun onItemsLoadComplete() {
        isRefresh = false
        binding.swipeRefreshLayout.isRefreshing = false
    }



    // set Adapter
    private fun setAdapter(arrData: ArrayList<BeerModel?>) {
        mAdapter = object : CommonAdapter<BeerModel>(arrData) {

            override fun getItemViewType(position: Int): Int {
                return if (getItem(position) == null) R.layout.item_load_more else R.layout.row_item_all
            }

            override fun onBindWithData(holder: CommonViewHolder, position: Int, item: BeerModel?) {

                try {
                    if (holder.binding is RowItemAllBinding)
                    {
                        val binding: RowItemAllBinding = holder.binding as RowItemAllBinding

                        binding.model = item
                        binding.ivBeerImage.tag = item?.labels?.mediumIcon ?: DEFAULT_IMAGE
                        binding.executePendingBindings()

                        binding.root.setOnClickListener {

                            // Detail Activity will open when click on beer Item
                            val intent = Intent(this@MainActivity, DetailViewActivity::class.java)

                            ViewCompat.setTransitionName(binding.ivBeerImage, binding.ivBeerImage.tag.toString())

                            intent.putExtra(DetailViewActivity.EXTRA_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName((holder.binding as RowItemAllBinding).ivBeerImage))

                            intent.putExtra(DetailViewActivity.SELECTION_BEER_MODEL, item)

                            val pair1 = Pair.create<View, String>(binding.ivBeerImage, ViewCompat.getTransitionName(binding.ivBeerImage))
                            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, pair1)


                            startActivity(intent, options.toBundle())


                        }
                    } else {
                        val binding: ItemLoadMoreBinding = holder.binding as ItemLoadMoreBinding
                        binding.progressBar.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        binding.myRecyclerView.layoutManager = LinearLayoutManager(
                    this, RecyclerView.VERTICAL, false)

        binding.myRecyclerView.adapter = mAdapter
        binding.myRecyclerView.recyclerViewAnimate()

        // Load more listener will fetch another data.
        mAdapter?.setLoadMoreListener(object : CommonAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                binding.myRecyclerView.post {
                    mAdapter?.startLoadMore()
                    CoroutineScope(Dispatchers.Main).launch {
                        isLoadMore = true
                        fetchBeerList()
                    }
                }
            }

        })
    }

}