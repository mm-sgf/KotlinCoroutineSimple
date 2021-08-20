package com.cfox.kotlin.coroutine.core

import kotlin.coroutines.*

class CoroutineSingle(override val context : CoroutineContext = EmptyCoroutineContext) : Continuation<Int>{


        fun runContinuation() {
                val coroutineBlock :suspend ()->Int = {
//                        testMethod()
                        testMethod2()
                }
                val start = coroutineBlock.createCoroutine(this)
                start.resume(Unit)
        }

        override fun resumeWith(result : Result<Int>) {
                println("resumeWith ${result.isSuccess}  ${result.getOrNull()}")
        }
}

suspend fun testMethod2() = suspendCoroutine<Int> {
        it.resume(3)

}

class BaseCoroutine<R >(override val context : CoroutineContext = EmptyCoroutineContext, block : suspend () ->R ) : Continuation<R?> {

        val start : Continuation<Unit>
        init {
                val block : suspend () ->R =  {
                        block()
                }

                start =  block.createCoroutine(this )
                Thread {
                        start.resume(Unit)
                }.start()

        }

        override fun resumeWith(result : Result<R?>) {
                result.getOrNull()
        }



}


fun main() {

//        val coroutineSingle = com.cfox.kotlin.coroutinecore.CoroutineSingle()
//        coroutineSingle.runContinuation()


        BaseCoroutine{

                val n = tests()
                println("n == $n   ${Thread.currentThread().id}" )
        }

        println("=========${Thread.currentThread().id}")
}

fun tests() : Int {
        Thread.sleep(3000)
        return  3
}