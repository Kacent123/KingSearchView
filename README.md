# KSearchView
自定义搜索控件

#布局示例代码
```xml
 <com.kacent.widget.view.KSearchView
        android:id="@+id/search_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginEnd="5dp"
        app:hint_text="输入搜索内容"
        app:icon_padding_bottom="5dp"
        app:search_padding_bottom="10dp"
        app:search_padding_start="30dp"
        app:search_padding_top="10dp"
        app:search_view_background="@drawable/my_search_shape"
        app:text_size="8sp" />
```
#设置搜索监听器

```kotlin
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

```
