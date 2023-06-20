package com.example.bmicalculator

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var gender: String = "male"
    private var height: Int = 183
    private var weight: Int = 74
    private var age: Int = 24


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserGender()
        getUserWeight()
        getUserHeight()
        getUserAge()
        onBtnClicked()
    }

    private fun getUserAge() {
        binding.mainActivityTvAddAge.setOnClickListener {
            age++
            binding.mainActivityTvAge.text = age.toString()
        }
        binding.mainActivityTvDecAge.setOnClickListener {
            age--
            binding.mainActivityTvAge.text = age.toString()
        }
    }

    private fun getUserWeight() {
        binding.mainActivityTvAddWeight.setOnClickListener {
            weight++
            binding.mainActivityTvWeight.text = weight.toString()
        }
        binding.mainActivityTvDecWeight.setOnClickListener {
            weight--
            binding.mainActivityTvWeight.text = weight.toString()
        }
    }

    private fun getUserHeight() {
        binding.mainActivitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.mainActivityTvHeight.text = progress.toString()
                height = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun getUserGender() {
        binding.mainActivityLayoutMale.setOnClickListener{
            binding.mainActivityLayoutMale.setBackgroundResource(R.drawable.rectangle_purple_outline)
            binding.mainActivityLayoutFemale.setBackgroundResource(R.drawable.rectangle_black_7dp)
            gender = "male"
        }
        binding.mainActivityLayoutFemale.setOnClickListener{
            binding.mainActivityLayoutFemale.setBackgroundResource(R.drawable.rectangle_purple_outline)
            binding.mainActivityLayoutMale.setBackgroundResource(R.drawable.rectangle_black_7dp)
            gender = "female"
        }
    }

    private fun onBtnClicked() {
        binding.mainActivityBtnCalculateBmi.setOnClickListener {
            showBmiResult()
        }
    }

    private fun showBmiResult() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialog)
        val imgClose: ImageView = dialog.findViewById(R.id.dialogImgClose)
        val bmiValue: TextView = dialog.findViewById(R.id.dialogTvBmiValue)
        imgClose.setOnClickListener{
            dialog.dismiss()
        }
        bmiValue.text = String.format("%.1f", calculateBmi())
        dialog.show()
    }

    private fun calculateBmi(): Double {
        return (weight / (height * height).toDouble()) * 10000
    }
}
