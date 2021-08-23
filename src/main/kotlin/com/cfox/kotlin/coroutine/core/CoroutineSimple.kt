package com.cfox.kotlin.coroutine.core

import com.cfox.kotlin.coroutine.Log
import kotlin.coroutines.*

class CoroutineCompletion(override val context : CoroutineContext = EmptyCoroutineContext) : Continuation<Unit> {
        override fun resumeWith(result : Result<Unit>) {
                result.getOrThrow()
        }
}

/**
 * 一个简单协成
 */
fun coroutineSingleMain(run :  () ->Unit) {
        val block : suspend ()-> Unit = {
                Log.i("start run coroutine method ")
                run()
                Log.i("end run coroutine method ")
        }

        /**
         * 一个最简单的协成：
         *  1. 一个 suspend 函数
         *  2. 使用 createCoroutine 创建一个协成
         *  3. 挂起创建协成的 resume 方法启动协成
         */
        val start = block.createCoroutine(CoroutineCompletion())
        start.resume(Unit)
}

 fun  myLaunch(block :suspend  ()-> Unit)  {
        val block : suspend ()-> Unit = {
                block()
        }

        /**
         * 一个最简单的协成：
         *  1. 一个 suspend 函数
         *  2. 使用 createCoroutine 创建一个协成
         *  3. 调用创建协成的 resume 方法启动协成
         */
        val start = block.createCoroutine(CoroutineCompletion())
        start.resume(Unit)
}

suspend fun<R> resultMethod(run : () -> R)  = suspendCoroutine<R> {
        val result = run()
        it.resume(result)
}


fun main() {
//        coroutineSingleMain()
        coroutineSingleMain {
                // 这里使能是普通fun
                /**
                 * 虽然这个方法执行在协成中，也有挂起，
                 * 但是这个方法是运行在主线程中，sleep 会阻塞，
                 * 所以这里会阻塞当前线程， 和同步执行没有区别
                 */
//                Thread.sleep(2000)
                Log.i("this run in coroutine method")
        }


        myLaunch {
                val result = resultMethod {
                        Thread.sleep(2000)
                        3
                }
                Log.i("get Result :$result")
        }

        Log.i("===== main run end ======")
}
