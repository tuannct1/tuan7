package com.hvacpro.faultcodes.tuan7

import kotlin.random.Random


class MyThread(private val loai:String,private val onResult: ((Int) -> Unit)) : Thread() {
    var tong = 0
    var hieu = 0
    var tich = 1
    var random = 0
    override fun run() {
        val delay = when (loai) {
            "cong", "tru" -> 1000L
            "random" -> 3000L
            else -> 5000L
        }
        try {
            while (true) {
                sleep(delay)
                tong++
                hieu--
                tich *= 2
                when (loai) {
                    "cong" -> onResult(++tong)
                    "tru" -> onResult(--hieu)
                    "tich" -> { tich *= 2; onResult(tich) }
                    "random" -> onResult(Random.nextInt(1000))
                }
            }
        } catch (e: InterruptedException){
        }
        }
}