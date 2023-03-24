package com.ferosburn.mybookshelf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.window.SplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvBooks: RecyclerView
    private var list = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBooks = findViewById(R.id.rv_books)
        rvBooks.setHasFixedSize(true)

        list.addAll(getListBooks())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutPageIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutPageIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListBooks(): ArrayList<Book> {
        val dataCover = resources.obtainTypedArray(R.array.data_cover)
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)

        val listBooks = ArrayList<Book>()
        for (i in dataTitle.indices) {
            val book = Book(
                title = dataTitle[i],
                description = dataDescription[i], cover = dataCover.getResourceId(i, -1)
            )
            listBooks.add(book)
        }
        return listBooks
    }

    private fun showRecyclerList() {
        rvBooks.layoutManager = LinearLayoutManager(this)
        val listBookAdapter = ListBookAdapter(list)
        rvBooks.adapter = listBookAdapter

        listBookAdapter.setOnItemClickCallback(object: ListBookAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                val bookDetailIntent = Intent(this@MainActivity, BookDetailActivity::class.java)
                bookDetailIntent.putExtra(BookDetailActivity.BOOK_DETAIL_EXTRA, data)
                startActivity(bookDetailIntent)
            }
        })
    }
}