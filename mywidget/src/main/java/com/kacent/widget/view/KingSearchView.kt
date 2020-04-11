package com.kacent.widget.view

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.kacent.widget.R
import com.kacent.widget.view.utils.AnimationUtils

class KingSearchView(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    @JvmOverloads
    constructor(
        context: Context?,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : this(context, attrs, defStyleAttr, 0) {
    }

    private var editText: EditText
    private var icon: ImageView
    private var cleanBtn:ImageView

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        LayoutInflater.from(context).inflate(R.layout.search_layout, this, true)
        editText = findViewById(R.id.search_edit)
        icon = findViewById(R.id.icon)
        cleanBtn=findViewById(R.id.clean_btn)
        AnimationUtils.addLayoutTransition(cleanBtn.rootView as ViewGroup)
        val a = context?.obtainStyledAttributes(attrs, R.styleable.KingSearchView)
        a?.getString(R.styleable.KingSearchView_hint_text)?.let { setHint(it) }
        a?.getResourceId(R.styleable.KingSearchView_icon_res, R.drawable.search_icon)
            ?.let { setIcon(it) }
        a?.getDimension(R.styleable.KingSearchView_text_size, 13f)?.let { setTextSize(it) }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_icon_padding, 0)
            ?.let {
                if (it==0)
                    return@let
                setIconPadding(it, it, it, it) }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_icon_padding_top, 0)
            ?.let {
                if (it==0)
                    return@let
                setIconPadding(icon.paddingLeft, it, icon.paddingRight, icon.paddingBottom) }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_icon_padding_bottom, 0)
            ?.let {
                if (it==0)
                    return@let
                setIconPadding(icon.paddingLeft, icon.paddingTop, icon.paddingRight, it) }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_icon_padding_start, 0)
            ?.let {
                if (it==0)
                    return@let
                setIconPadding(it, icon.paddingTop, icon.paddingRight, icon.paddingBottom) }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_icon_padding_end, 0)
            ?.let {
                if (it==0)
                    return@let
                setIconPadding(icon.paddingLeft, icon.paddingTop, it, icon.paddingBottom) }

        a?.getResourceId(R.styleable.KingSearchView_search_view_background, R.drawable.search_shape)
            ?.let { setSearchViewBackground(it) }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_search_padding, 0)

            ?.let {
                if (it==0)
                    return@let
                setSearchViewPadding(it, it, it, it) }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_search_padding_top, 0)?.let {
            if (it==0)
                return@let
            setSearchViewPadding(
                editText.paddingLeft,
                it,
                editText.paddingRight,
                editText.paddingBottom
            )
        }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_search_padding_bottom, 0)?.let {
            if (it==0)
                return@let
            setSearchViewPadding(
                editText.paddingLeft,
                editText.paddingTop,
                editText.paddingRight,
                it
            )
        }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_search_padding_start, 0)?.let {
            if (it==0)
                return@let
            setSearchViewPadding(
                it,
                editText.paddingTop,
                editText.paddingRight,
                editText.paddingBottom
            )
        }
        a?.getDimensionPixelOffset(R.styleable.KingSearchView_search_padding_end, 0)?.let {
            if (it==0)
                return@let
            setSearchViewPadding(
                editText.paddingLeft,
                editText.paddingTop,
                it,
                editText.paddingBottom
            )
        }

        cleanBtn.setOnClickListener {
            editText.setText("")
        }
        editText.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val toString = s.toString()
                if (toString.isNotEmpty()){
                    cleanBtn.visibility= View.VISIBLE
                }else{
                    cleanBtn.visibility=View.GONE
                }
            }
        })
    }

    //设置搜索框背景
    fun setSearchViewBackground(res: Int) {
        editText.setBackgroundResource(res)
    }

    //设置icon
    fun setIcon(drawableRes: Int) {
        icon.setImageResource(drawableRes)
    }

    //设置icon位置
    @RequiresApi(Build.VERSION_CODES.M)
    fun setIconGravity(gravity: Int) {
        icon.foregroundGravity = gravity
    }

    //设置字体大小
    fun setTextSize(size: Float) {
        editText.textSize = size
    }
    fun getText():String{
        return editText.text.toString()
    }

    //设置hint
    fun setHint(text: String) {
        editText.hint = text
    }

    //设置iconPadding
    fun setIconPadding(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        icon.setPadding(left, top, right, bottom)
    }

    fun setSearchViewPadding(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        editText.setPadding(left, top, right, bottom)
    }

    interface OnQueryListener {
        fun onQuery(value: String)
    }

    private var onQueryListener: OnQueryListener? = null

    fun setQueryListener(listener: OnQueryListener) {
        onQueryListener = listener
        editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    onQueryListener?.apply {
                        val value = v?.text.toString()
                        onQuery(value)
                    }
                }

                return true
            }

        })
    }
}