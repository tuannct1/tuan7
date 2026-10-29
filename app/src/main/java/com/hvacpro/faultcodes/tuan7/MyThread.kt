package com.hvacpro.faultcodes.tuan7

import kotlin.random.Random


public class MyThread(private val loai:String,private val onResult: ((Int) -> Unit)) : Thread() {
    var tong = 0
    var hieu = 0
    var tich = 1
    var random = 0
    var run = true
    override fun run() {
        val delay = when (loai) {
            "cong", "tru" -> 1000L
            "random" -> 3000L
            else -> 5000L
        }
        try {
            while (true) {
                sleep(delay)
                when (loai) {
                    "cong" -> {
                        if(run){
                            tong++
                            onResult(tong)
                        }
                    }
                    "tru" -> {
                        if(run){
                            hieu--
                            onResult(hieu)
                        }
                    }
                    "tich" -> {
                        if(run){
                            tich *= 2
                            onResult(tich)
                        }
                    }
                    "random" -> {
                        if(run){
                            random = Random.nextInt(1000)
                            onResult(random)
                        }
                    }
                }
            }
        } catch (e: InterruptedException){
        }
        }
    fun check(){
        run = !run
    }
}