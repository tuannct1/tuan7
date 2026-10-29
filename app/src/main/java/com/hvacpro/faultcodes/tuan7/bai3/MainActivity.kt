package com.hvacpro.faultcodes.tuan7.bai3

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.hvacpro.faultcodes.tuan7.R
import com.hvacpro.faultcodes.tuan7.databinding.ActivityMain3Binding
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : AppCompatActivity() {
    var cong = 0
    var tru = 0
    var nhan = 1
    var random = 0
    var isCongRunning = true
    var isTruRunning = true
    var isNhanRunning = true
    var isRandomRunning = true
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            lifecycleScope.launch {
                while(true){
                    delay(1000.milliseconds)
                    if(isCongRunning){
                        cong++
                        binding.tvAdd.text = cong.toString()
                    }
                }
            }
                    lifecycleScope.launch {
                        while(true){
                            delay(1000.milliseconds)
                            if(isTruRunning){
                                tru --
                                binding.tvDeduct.text = tru.toString()
                            }
                        }
            }
                    lifecycleScope.launch {
                        while (true){
                            delay(5000.milliseconds)
                            if(isNhanRunning){
                                nhan *=2
                                binding.tvMultiplication.text = nhan.toString()
                            }
                        }
            }
                    lifecycleScope.launch {
                        while(true){
                            delay(3000.milliseconds)
                            if(isRandomRunning){
                                random = Random.nextInt(1000)
                                binding.tvRandom.text = random.toString()
                            }
                        }
            }
        binding.btnStopAdd.setOnClickListener {
            binding.btnStopAdd.visibility = View.GONE
            binding.btnReadd.visibility = View.VISIBLE
            isCongRunning = !isCongRunning
        }
        binding.btnReadd.setOnClickListener {
            binding.btnStopAdd.visibility = View.VISIBLE
            binding.btnReadd.visibility = View.GONE
            isCongRunning = !isCongRunning
        }

        binding.btnStopDeduct.setOnClickListener {
            binding.btnStopDeduct.visibility = View.GONE
            binding.btnRededuct.visibility = View.VISIBLE
            isTruRunning = !isTruRunning
        }

        binding.btnRededuct.setOnClickListener {
            binding.btnStopDeduct.visibility = View.VISIBLE
            binding.btnRededuct.visibility = View.GONE
            isTruRunning = !isTruRunning
        }
        binding.btnStopMultiplication.setOnClickListener {
            binding.btnStopMultiplication.visibility = View.GONE
            binding.btnRemultiplication.visibility = View.VISIBLE
            isNhanRunning = !isNhanRunning
        }
        binding.btnRemultiplication.setOnClickListener {
            binding.btnStopMultiplication.visibility = View.VISIBLE
            binding.btnRemultiplication.visibility = View.GONE
            isNhanRunning = !isNhanRunning
        }
        binding.btnStopRandom.setOnClickListener {
            binding.btnStopRandom.visibility = View.GONE
            binding.btnRandom.visibility = View.VISIBLE
            isRandomRunning = !isRandomRunning
        }
        binding.btnRandom.setOnClickListener {
            binding.btnStopRandom.visibility = View.VISIBLE
            binding.btnRandom.visibility = View.GONE
            isRandomRunning = !isRandomRunning
        }
    }
}