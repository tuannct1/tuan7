package com.hvacpro.faultcodes.tuan7

import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hvacpro.faultcodes.tuan7.databinding.ActivityMainBinding
import android.os.Handler

class MainActivity : AppCompatActivity() {
    var cong = true
    var tru = true
    var nhan = true
    var random = true
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
                binding.cong.text = a.toString()
            }
        })
        congThread?.start()
        //tao luong tru
        truThread = MyThread("tru", onResult = { a ->
            handler.post {
                binding.tru.text = a.toString()
            }
        })
        truThread?.start()
        //tao luong nhan
        nhanThread = MyThread("tich", onResult = { a ->
            handler.post {
                binding.nhan.text = a.toString()
            }
        })
        nhanThread?.start()
        //tao luong random
        randomThread = MyThread("random", onResult = { a ->
            handler.post {
                binding.random.text = a.toString()
            }
        })
        randomThread?.start()

        binding.dungCong.setOnClickListener {
            if (cong) {
                congThread?.interrupt()
                cong = false
            } else {
                congThread = MyThread("cong", onResult = { a ->
                    handler.post {
                        binding.cong.text = a.toString()
                    }
                })
                congThread?.tong = binding.cong.text.toString().toInt()
                congThread?.start()
                cong = true
            }
        }
            binding.dungtru.setOnClickListener {
                if (tru) {
                    truThread?.interrupt()
                    tru = false
                } else {
                    truThread = MyThread("tru", onResult = { a ->
                        handler.post {
                            binding.tru.text = a.toString()
                        }
                    })
                    truThread?.hieu = binding.tru.text.toString().toInt()
                    truThread?.start()
                    tru = true
                }
            }
            binding.dungnhan.setOnClickListener {
                if (nhan) {
                    nhanThread?.interrupt()
                    nhan = false
                } else {
                    nhanThread = MyThread("tich", onResult = { a ->
                        handler.post {
                            binding.nhan.text = a.toString()
                        }
                    })
                    nhanThread?.tich = binding.nhan.text.toString().toInt()
                    nhanThread?.start()
                    nhan = true
                }
            }
            binding.dungrandom.setOnClickListener {
                if (random) {
                    randomThread?.interrupt()
                    random = false
                } else {
                    randomThread = MyThread("random", onResult = { a ->
                        handler.post {
                            binding.random.text = a.toString()
                        }
                    })
                    randomThread?.start()
                    random = true
                }
            }
        }
    }
