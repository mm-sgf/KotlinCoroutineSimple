package com.cfox.kotlin.coroutine.core

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun test() = suspendCoroutine<Int> {
        Thread.sleep(3000)
        it.resume(3)

}

suspend fun main() {
        val result = test()
        println(result)

        println("========")
}
