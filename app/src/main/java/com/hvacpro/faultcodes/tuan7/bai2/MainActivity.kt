package com.hvacpro.faultcodes.tuan7.bai2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hvacpro.faultcodes.tuan7.MyThread
import com.hvacpro.faultcodes.tuan7.R
import com.hvacpro.faultcodes.tuan7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var congThread: MyThread? = null
    private var truThread: MyThread? = null
    private var nhanThread: MyThread? = null
    private var randomThread: MyThread? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //tao handler
        val handler = Handler(Looper.getMainLooper())
        //tao luong cong
        congThread = MyThread("cong", onResult = { a ->
            handler.post {
                binding.tvAdd.text = a.toString()
            }
        })
        congThread?.start()
        //tao luong tru
        truThread = MyThread("tru", onResult = { a ->
            handler.post {
                binding.tvDeduct.text = a.toString()
            }
        })
        truThread?.start()
        //tao luong nhan
        nhanThread = MyThread("tich", onResult = { a ->
            handler.post {
                binding.tvMultiplication.text = a.toString()
            }
        })
        nhanThread?.start()
        //tao luong random
        randomThread = MyThread("random", onResult = { a ->
            handler.post {
                binding.tvRandom.text = a.toString()
            }
        })
        randomThread?.start()

        binding.btnStopAdd.setOnClickListener {
            binding.btnStopAdd.visibility = View.GONE
            binding.btnReadd.visibility = View.VISIBLE
                congThread?.interrupt()
        }
        binding.btnReadd.setOnClickListener {
            binding.btnStopAdd.visibility = View.VISIBLE
            binding.btnReadd.visibility = View.GONE
            congThread = MyThread("cong", onResult = { a ->
                handler.post {
                    binding.tvAdd.text = a.toString()
                }
            })
            congThread?.tong = binding.tvAdd.text.toString().toInt()
            congThread?.start() }
            binding.btnStopDeduct.setOnClickListener {
                    binding.btnStopDeduct.visibility = View.GONE
                    binding.btnRededuct.visibility = View.VISIBLE
                    truThread?.interrupt()
            }
        binding.btnRededuct.setOnClickListener {
            binding.btnStopDeduct.visibility = View.VISIBLE
            binding.btnRededuct.visibility = View.GONE
            truThread = MyThread("tru", onResult = { a ->
                handler.post {
                    binding.tvDeduct.text = a.toString()
                }
            })
            truThread?.hieu = binding.tvDeduct.text.toString().toInt()
            truThread?.start()
        }

            binding.btnStopMultiplication.setOnClickListener {
                    binding.btnStopMultiplication.visibility = View.GONE
                    binding.btnRemultiplication.visibility = View.VISIBLE
                    nhanThread?.interrupt()
            }
            binding.btnRemultiplication.setOnClickListener {
                binding.btnStopMultiplication.visibility = View.VISIBLE
                binding.btnRemultiplication.visibility = View.GONE
                nhanThread = MyThread("tich", onResult = { a ->
                    handler.post {
                        binding.tvMultiplication.text = a.toString()
                    }
                })
                nhanThread?.tich = binding.tvMultiplication.text.toString().toInt()
                nhanThread?.start()
            }

            binding.btnStopRandom.setOnClickListener {
                    binding.btnStopRandom.visibility = View.GONE
                    binding.btnRandom.visibility = View.VISIBLE
                    randomThread?.interrupt()
            }

        binding.btnRandom.setOnClickListener {
            binding.btnStopRandom.visibility = View.VISIBLE
            binding.btnRandom.visibility = View.GONE
            randomThread = MyThread("random", onResult = { a ->
                handler.post {
                    binding.tvRandom.text = a.toString()
                }
            })
            randomThread?.start()
        }
        }
    }