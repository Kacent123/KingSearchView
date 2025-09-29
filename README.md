<img width="1864" height="298" alt="截图 2025-09-22 14-06-35" src="https://github.com/user-attachments/assets/4918be0a-0a0b-4909-94c6-ab6dc0f2adf3" />
# KSearchView
# 自定义搜索控件



# 默认样式
![image](https://github.com/Kacent123/KSearchView/blob/master/image/WX20200326-172933%402x.png)

# 布局示例代码

```xml
 <com.kacent.widget.view.KingSearchView
        android:id="@+id/search_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginEnd="5dp"
       <!-提示文本->
        app:hint_text="输入搜索内容"
       <!-icon的padding->
        app:icon_padding_bottom="5dp"
       <!-searchView输入框的padding->
        app:search_padding_bottom="10dp"
        app:search_padding_start="30dp"
        app:search_padding_top="10dp"
       <!-searchView背景设置->
        app:search_view_background="@drawable/my_search_shape"
        app:text_size="8sp" />
```

# 设置搜索监听器

```kotlin
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
    }
}


```
