package com.cfox.kotlin.coroutine

object Log {
        fun i(msg: String) {
                println("t-id : ${Thread.currentThread().id }  t-name : ${Thread.currentThread().name}   \nmsg : $msg  \n ")
        }
}