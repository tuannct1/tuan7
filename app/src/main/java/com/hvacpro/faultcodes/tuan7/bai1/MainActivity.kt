package com.hvacpro.faultcodes.tuan7.bai1

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hvacpro.faultcodes.tuan7.MyThread
import com.hvacpro.faultcodes.tuan7.R
import com.hvacpro.faultcodes.tuan7.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val congThread = MyThread("cong", onResult = {cong-> runOnUiThread { binding.tvAdd.text = cong.toString() }})
        congThread.start()

        binding.btnStopAdd.setOnClickListener {
            binding.btnStopAdd.visibility = View.GONE
            binding.btnReadd.visibility = View.VISIBLE
            congThread.check()
        }
        binding.btnReadd.setOnClickListener {
            binding.btnStopAdd.visibility = View.VISIBLE
            binding.btnReadd.visibility = View.GONE
            congThread.check()
        }

        val truThread = MyThread("tru",onResult = {tru-> binding.tvDeduct.post { binding.tvDeduct.text = tru.toString() }})
        truThread.start()

        binding.btnStopDeduct.setOnClickListener {
            binding.btnStopDeduct.visibility = View.GONE
            binding.btnRededuct.visibility = View.VISIBLE
            truThread.check()
        }
        binding.btnRededuct.setOnClickListener {
            binding.btnStopDeduct.visibility = View.VISIBLE
            binding.btnRededuct.visibility = View.GONE
            truThread.check()
        }

        val nhanThread = MyThread("tich",onResult = {tich-> binding.tvMultiplication.post { binding.tvMultiplication.text = tich.toString() }})
        nhanThread.start()

        binding.btnStopMultiplication.setOnClickListener {
            binding.btnStopMultiplication.visibility = View.GONE
            binding.btnRemultiplication.visibility = View.VISIBLE
            nhanThread.check()
        }
        binding.btnRemultiplication.setOnClickListener {
            binding.btnStopMultiplication.visibility = View.VISIBLE
            binding.btnRemultiplication.visibility = View.GONE
            nhanThread.check()
        }

        val randomThread = MyThread("random",onResult = {random-> binding.tvRandom.post { binding.tvRandom.text = random.toString() }})
        randomThread.start()

        binding.btnStopRandom.setOnClickListener {
            binding.btnStopRandom.visibility = View.GONE
            binding.btnRandom.visibility = View.VISIBLE
            randomThread.check()
        }
        binding.btnRandom.setOnClickListener {
            binding.btnStopRandom.visibility = View.VISIBLE
            binding.btnRandom.visibility = View.GONE
            randomThread.check()
        }
    }
}