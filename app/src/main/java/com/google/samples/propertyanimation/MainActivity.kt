/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.propertyanimation

import android.animation.*
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView


class MainActivity : AppCompatActivity() {

    lateinit var star: ImageView
    lateinit var rotateButton: Button
    lateinit var translateButton: Button
    lateinit var scaleButton: Button
    lateinit var fadeButton: Button
    lateinit var colorizeButton: Button
    lateinit var showerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        star = findViewById(R.id.star)
        rotateButton = findViewById<Button>(R.id.rotateButton)
        translateButton = findViewById<Button>(R.id.translateButton)
        scaleButton = findViewById<Button>(R.id.scaleButton)
        fadeButton = findViewById<Button>(R.id.fadeButton)
        colorizeButton = findViewById<Button>(R.id.colorizeButton)
        showerButton = findViewById<Button>(R.id.showerButton)

        rotateButton.setOnClickListener {
            rotater()
        }

        translateButton.setOnClickListener {
            translater()
        }

        scaleButton.setOnClickListener {
            scaler()
        }

        fadeButton.setOnClickListener {
            fader()
        }

        colorizeButton.setOnClickListener {
            colorizer()
        }

        showerButton.setOnClickListener {
            shower()
        }
    }

    private fun rotater() {

        // It will rotate the targeted object "star" from -360 to 0
        // Note since 0deg is default value, that's why we want it to be the same once the animation
        // will be completed, that's why we are starting from -360 instead of 360deg
        val animator = ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f)

        // Defines the duration, 300 milliseconds is default
        animator.duration = 1000

        // Disable the Button while the animation is being completed
        animator.disableViewDuringAnimation(rotateButton)

        // Initialize the animation
        animator.start()

    }

    private fun translater() {

        // Moves 200px in the X direction, from the "current" position
        val animator = ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f)

        // It controls how many times it repeats after the first run
        animator.repeatCount = 1

        // .RESTART - Same Direction (for animating from the original start value to the original end value)
        // .REVERSE - Towards Opposite Direction (for reversing the direction every time it repeats)
        animator.repeatMode = ObjectAnimator.REVERSE

        // Disable the Button while the animation is being completed
        animator.disableViewDuringAnimation(translateButton)

        // Initialize the animation
        animator.start()

    }

    private fun scaler() {

        // Scale Ratio: 4x in x,y directions
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)

        // Scales the Object, from the "current" position
        val animator = ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY)

        // It controls how many times it repeats after the first run
        animator.repeatCount = 1

        // .RESTART - Same Direction (for animating from the original start value to the original end value)
        // .REVERSE - Towards Opposite Direction (for reversing the direction every time it repeats)
        animator.repeatMode = ObjectAnimator.REVERSE

        // Disable the Button while the animation is being completed
        animator.disableViewDuringAnimation(scaleButton)

        // Initialize the animation
        animator.start()

    }

    private fun fader() {

        // Fully Transparent/Alpha value = 0f
        val animator = ObjectAnimator.ofFloat(star, View.ALPHA, 0f)

        // It controls how many times it repeats after the first run
        animator.repeatCount = 1

        // .RESTART - Same Direction (for animating from the original start value to the original end value)
        // .REVERSE - Towards Opposite Direction (for reversing the direction every time it repeats)
        animator.repeatMode = ObjectAnimator.REVERSE

        // Disable the Button while the animation is being completed
        animator.disableViewDuringAnimation(fadeButton)

        // Initialize the animation
        animator.start()

    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun colorizer() {

        // ofArgs() for Color Values
        /**
         *  When you do this, the system searches for setters and getters with that exact spelling
         *  using reflection. It caches references to those methods and calls them during the animation,
         *  instead of calling the ofInt() set/get functions.
         */
        var animator = ObjectAnimator.ofArgb(
            star.parent,
            "backgroundColor", Color.BLACK, Color.RED
        )

        // Duration: 5 milliseconds
        animator.duration = 500

        // It controls how many times it repeats after the first run
        animator.repeatCount = 1

        // .RESTART - Same Direction (for animating from the original start value to the original end value)
        // .REVERSE - Towards Opposite Direction (for reversing the direction every time it repeats)
        animator.repeatMode = ObjectAnimator.REVERSE

        // Disable the Button while the animation is being completed
        animator.disableViewDuringAnimation(colorizeButton)

        // Initialize the animation
        animator.start()

    }

    private fun shower() {

        /**
         * 01 These variables will hold the state, which we will need later
         */
        val container = star.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height
        var starW: Float = star.width.toFloat()
        var starH: Float = star.height.toFloat()

        /**
         * 02 Create a new star
         */
        // Purpose of this View is to hold the star graphic
        // Because the star is a VectorDrawable asset, use an AppCompatImageView, which has the ability to host that kind of resource.
        val newStar = AppCompatImageView(this)
        newStar.setImageResource(R.drawable.ic_star)
        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        container.addView(newStar)

        /**
         * 03 Assign random size to the star
         */
        newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
        newStar.scaleY = newStar.scaleX

        // New Scale Factor: Width, Height
        starW *= newStar.scaleX
        starH *= newStar.scaleY

        /**
         * 04 Random Position of the newly generated star
         */
        newStar.translationX = Math.random().toFloat() * containerW - starW / 2

        /**
         * 05 Falling Animation
         */

        val mover = ObjectAnimator.ofFloat(
            newStar, View.TRANSLATION_Y,
            -starH, containerH + starH
        )
        mover.interpolator = AccelerateInterpolator(1f)


        /**
         * 06 Rotating Animation: 360 * 3 = 1080 (up to: 3x rotation)
         */
        val rotator = ObjectAnimator.ofFloat(
            newStar, View.ROTATION,
            (Math.random() * 1080).toFloat()
        )
        rotator.interpolator = LinearInterpolator()

        /**
         * 07 Running Animation in parallel with AnimatorSet
         */
        val set = AnimatorSet()
        set.playTogether(mover, rotator)
        set.duration = (Math.random() * 1500 + 500).toLong()

        /**
         * 08
         */
        set.addListener(object : AnimatorListenerAdapter() {

            // Take action in the end
            override fun onAnimationEnd(animation: Animator) {

                // Remove the completed animation from the container
                container.removeView(newStar)

            }

        })

        /**
         * 09
         */
        set.start()

    }

    /**
     * disableViewDuringAnimation()
     */
    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        // Event based actions
        addListener(object : AnimatorListenerAdapter() {

            // State: Start
            override fun onAnimationStart(animation: Animator) {
                view.isEnabled = false
            }

            // State: End
            override fun onAnimationEnd(animation: Animator) {
                view.isEnabled = true
            }

        })
    }

}
