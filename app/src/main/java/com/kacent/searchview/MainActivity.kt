package com.kacent.searchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.kacent.widget.view.KingSearchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchView = findViewById<KingSearchView>(R.id.search_view)
        searchView.setQueryListener(object : KingSearchView.OnQueryListener {
            override fun onQuery(value: String) {
                if (TextUtils.isEmpty(value)) {
                    Toast.makeText(this@MainActivity, "没有输入相关搜索内容", Toast.LENGTH_SHORT).show()
                }
                Log.e("搜索内容", value)
            }
        })
        searchView.setOnClearTextListener(object :KingSearchView.OnClearTextListener{
            override fun onClear() {
                Log.e("搜索内容", "清洁")
            }
        })
    }
}
