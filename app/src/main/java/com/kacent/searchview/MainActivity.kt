package com.kacent.searchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.kacent.widget.view.KSearchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchView = findViewById<KSearchView>(R.id.search_view)
        searchView.setQueryListener(object : KSearchView.OnSearchListener {
            override fun onSearch(value: String) {
                if (TextUtils.isEmpty(value)) {
                    Toast.makeText(this@MainActivity, "没有输入相关搜索内容", Toast.LENGTH_SHORT).show()
                }
                Log.e("搜索内容", value)
            }
        })
    }
}
