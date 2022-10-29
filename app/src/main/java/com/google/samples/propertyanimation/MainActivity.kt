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

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


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
    }

    private fun fader() {
    }

    private fun colorizer() {
    }

    private fun shower() {
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
