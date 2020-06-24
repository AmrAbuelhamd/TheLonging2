package com.blogspot.soyamr.thelonging2.bookshelf

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.thelonging2.R
import kotlinx.android.synthetic.main.activity_library_shelf.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibraryShelf : AppCompatActivity(), OnListFragmentInteractionListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var myDataset: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_shelf)

        initializeREcyclerView()
        startretrofitRequist()


    }

    private fun initializeREcyclerView() {
        myDataset = listOf()
        viewManager = LinearLayoutManager(this)
        viewAdapter = MybookRecyclerViewAdapter(myDataset, this)

        recyclerView = findViewById<RecyclerView>(R.id.list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    fun startretrofitRequist() {
        val request = GoogleApiService.ServiceBuilder.buildService(GoogleApiService::class.java)
        val call = request.getBooksList("amor")

        call.enqueue(object : Callback<JsonResponse> {
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                if (response.isSuccessful) {
                    val jsonResponse = response.body()
                    myDataset = jsonResponse?.items!!
                    viewAdapter = MybookRecyclerViewAdapter(myDataset, this@LibraryShelf)
                    recyclerView.adapter = viewAdapter

                    group.visibility = View.GONE
//                    viewAdapter.notifyDataSetChanged()
                    Toast.makeText(
                        this@LibraryShelf,
                        "${jsonResponse?.items?.get(0)?.volumeInfo?.authors} ${myDataset.size}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                Toast.makeText(this@LibraryShelf, "${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
    override fun onListFragmentInteraction(item: Item?) {
        Toast.makeText(this@LibraryShelf, "fadsdf", Toast.LENGTH_LONG).show()
    }

}

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 *
 *
 * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
 */
interface OnListFragmentInteractionListener {
    // TODO: Update argument type and name
    fun onListFragmentInteraction(item: Item?)
}