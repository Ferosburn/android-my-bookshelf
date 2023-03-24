package com.ferosburn.mybookshelf

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class BookDetailActivity : AppCompatActivity() {
    private lateinit var ivDetailCover: ImageView
    private lateinit var tvDetailTitle: TextView
    private lateinit var tvDetailDescription: TextView
    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        ivDetailCover = findViewById(R.id.iv_detail_cover)
        tvDetailTitle = findViewById(R.id.tv_detail_title)
        tvDetailDescription = findViewById(R.id.tv_detail_description)

        @Suppress("DEPRECATION")
        book = intent.getParcelableExtra(BOOK_DETAIL_EXTRA)!!

        supportActionBar?.apply {
            title = book.title
            setDisplayHomeAsUpEnabled(true)
        }

        book.let {
            ivDetailCover.setImageResource(it.cover)
            tvDetailTitle.text = it.title
            tvDetailDescription.text = it.description
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val body = "Judul: ${book.title}\n${book.description}"
                val subject = getString(R.string.book_recommendation) + " " + "(${book.title})"
                shareIntent.putExtra(Intent.EXTRA_TEXT, body)
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_with)))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val BOOK_DETAIL_EXTRA = "book_detail_extra"
    }
}