package com.ferosburn.mybookshelf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class AboutActivity : AppCompatActivity() {
    private lateinit var ivProfile: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.apply {
            title = getString(R.string.about)
            setDisplayHomeAsUpEnabled(true)
        }

        ivProfile = findViewById(R.id.iv_profile)
        tvName = findViewById(R.id.tv_name)
        tvEmail = findViewById(R.id.tv_email)

        ivProfile.setImageResource(R.mipmap.ic_profile_round)
        tvName.text = getString(R.string.name)
        tvEmail.text = getString(R.string.email)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}