package com.hvacpro.faultcodes.tuan7

class MyThread(private val onResult: ((Int) -> Unit)) : Thread() {
    override fun run() {
        var tong = 0
        while (true){
            sleep(1000)
            tong++
            onResult(tong)
        }
    }
}