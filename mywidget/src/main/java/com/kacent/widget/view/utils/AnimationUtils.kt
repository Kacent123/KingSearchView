package com.kacent.widget.view.utils

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.ViewGroup


object AnimationUtils {
    fun addLayoutTransition(viewGroup: ViewGroup) {
        val anim = LayoutTransition()
        val scaleInX: PropertyValuesHolder = PropertyValuesHolder.ofFloat("translationX", 100f, 0f)
        val scaleInY: PropertyValuesHolder = PropertyValuesHolder.ofFloat("translationY", 0f, 0f)
        val alphaIn: PropertyValuesHolder = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
        val scaleOutX: PropertyValuesHolder = PropertyValuesHolder.ofFloat("translationY", 0f, -100f)
        val scaleOutY: PropertyValuesHolder = PropertyValuesHolder.ofFloat("translationX", 0f, 0f)
        val alphaOut: PropertyValuesHolder = PropertyValuesHolder.ofFloat("alpha", 1f, 0f)
        val `in`: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(null as View?, scaleInX, scaleInY, alphaIn)
        val out: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(null as View?, scaleOutX, scaleOutY, alphaOut)
        anim.setAnimator(LayoutTransition.APPEARING, `in`)
        anim.setAnimator(LayoutTransition.DISAPPEARING, out)
        anim.setDuration(150)
        viewGroup.layoutTransition = anim
    }
}