package com.cfox.kotlin.coroutine.simple

import com.cfox.kotlin.coroutine.Log
import kotlinx.coroutines.*

fun main() {

//        GlobalScope.launch {
//                val result = runBlocking() {
//                        Log.i("run launch Blocking")
//                        delay(2000)
//                        200
//                }
//              delay (2000)
//                Log.i("非阻塞的等待 2 秒钟（默认时间单位是毫秒） $result")
//        }

//        val job = GlobalScope.launch {
//                Log.i("job launch 1=== ")
//                Thread.sleep(1000)
//                Log.i("job launch 2=== ")
//        }

//        GlobalScope.launch {
//                Log.i("before join job")
//                job.join()// 将在这个位置等待 job launch  执行完后继续执行
//                Log.i("after join job")
//        }

        /**
         * block 当前线程
         * 如果设置 Dispatchers.IO ，将在 新线程中执行。如果默认，则在当前线程执行
         * 是否设置 IO都有 block  功能
         */
//        val result = runBlocking(Dispatchers.IO) {
//                Log.i("runBlocking")
//                delay(5000)
//                300
//        }
//        Log.i("result ====>>>> $result")

//        runBlocking {
//                Log.i("run block start ===> ")
//                launch(Dispatchers.IO) {
//                        delay(0) // 会挂起。 使下面被执行
//                        runBlocking {
//                                for (i in 0 .. 10) {
//                                        Thread.sleep(500)
//                                }
//                        }
//
//
//                        Log.i("run block ===> launch")
//                }
//                coroutineScope {
//                        launch {
//                                delay(2000)
//                                Log.i("run block  coroutineScope  ==>launch")
//                        }
//
//                        delay(1000)
//                        Log.i("run block  coroutineScope  end==>")
//
//                }
//                Log.i("run block   end==>")
//        }

        runBlocking {
                repeat(1000) {// 每间隔2 s 执行一个协成
                        delay(2000)
                        Log.i("--")
                }
        }

        Log.i("main run end ====>>>>")
}