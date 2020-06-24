package com.blogspot.soyamr.thelonging2.bookshelf

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.thelonging2.R
import com.example.kaushiknsanji.bookslibrary.BookSearchActivity
import kotlinx.android.synthetic.main.activity_library_shelf.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibraryShelf : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_shelf)


        val intent = Intent(this, BookSearchActivity::class.java)
        startActivity(intent)

    }
}