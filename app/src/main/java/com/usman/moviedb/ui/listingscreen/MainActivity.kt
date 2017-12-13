package com.usman.moviedb.ui.listingscreen

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.usman.moviedb.R
import com.usman.moviedb.adapters.MovieAdapter
import com.usman.moviedb.application.MovieApplication
import com.usman.moviedb.models.ListingResponse
import com.usman.moviedb.models.Movies
import com.usman.moviedb.ui.BaseActivity
import com.usman.moviedb.utility.Utils
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), ListingContract.IListingView {

    override fun inject() {
        (applicationContext as MovieApplication).component.inject(this)
    }

    @Inject
    lateinit var presenter: ListingContract.IListingPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var tvMessage: TextView
    private lateinit var adapter: MovieAdapter
    private lateinit var listingData: ListingResponse
    private lateinit var filterView: ConstraintLayout

    private lateinit var minDate: TextView
    private lateinit var maxDate: TextView
    private lateinit var filterBtn: Button

    private var isLoading: Boolean = false

    private lateinit var linearLayoutManager: LinearLayoutManager

    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.setView(this)
        presenter.getMovies(1)
    }


    override fun initializeViews() {

        filterView = findViewById<ConstraintLayout>(R.id.filterView);
        recyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = linearLayoutManager

        progress = findViewById<ProgressBar>(R.id.mProgressBar)
        tvMessage = findViewById<TextView>(R.id.tvMessage)

        minDate = findViewById<TextView>(R.id.minDate)
        maxDate = findViewById<TextView>(R.id.maxDate)

        filterBtn = findViewById<Button>(R.id.filterBtn)

    }


    private fun setClickListeners() {

        filterBtn.setOnClickListener(View.OnClickListener {
            presenter.filterResults(listingData, minDate.text.toString(), maxDate.text.toString())
        })

        minDate.setOnClickListener(View.OnClickListener {
            showDatePicker(minDate)
        })

        maxDate.setOnClickListener(View.OnClickListener {
            showDatePicker(maxDate)
        })


    }

    private fun setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = if(linearLayoutManager.itemCount > 20)linearLayoutManager.itemCount else 20
                val validPage = listingData.page < listingData.total_pages
                if (!isLoading && validPage && totalItemCount == lastVisibleItemPosition + 1) {
                    presenter.getMovies(++listingData.page)
                    isLoading = true
                }
            }
        })
    }

    override fun showToast(msg: String?) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressDialog() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        progress.visibility = View.GONE
    }

    override fun setDataOnViews(response: ListingResponse) {

        listingData = response
        minDate.text = listingData.minDate
        maxDate.text = listingData.maxDate
        tvMessage.visibility = View.GONE

    }

    override fun setListeners() {

        setClickListeners()
        setRecyclerViewScrollListener()
    }

    override fun setDataInAdapter(response: ListingResponse) {

        adapter = MovieAdapter(listingData.movies, this)
        recyclerView.adapter = adapter

        if (adapter.itemCount > 0)
            filterView.visibility = View.VISIBLE
    }


    override fun appendDataInAdapter(response:ListingResponse) {

        listingData.minDate = response.minDate
//        listingData.page = response.page
        minDate.text = listingData.minDate

        isLoading = false

        listingData.movies.addAll(response.movies)
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(msg: String) {
        tvMessage.visibility = View.VISIBLE
        tvMessage.setText(msg)
    }

    override fun showFilteredMovies(movies: ArrayList<Movies>) {
        adapter.setData(movies)
    }

    fun showDatePicker(textView: TextView) {

        val c = Calendar.getInstance()
        c.time = Utils.getDateFromString(textView.text.toString())

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            textView.setText("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth)
        }, year, month, day)

        dpd.show()
    }
}
