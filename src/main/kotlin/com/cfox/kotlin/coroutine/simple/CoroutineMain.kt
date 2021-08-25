package com.cfox.kotlin.coroutine.simple

import com.cfox.kotlin.coroutine.Log
import kotlinx.coroutines.*

fun main() {

//        runBlocking {
//                /**
//                 * 当调用 launch { …… } 时不传参数，它从启动了它的 CoroutineScope 中承袭了上下文（以及调度器）。
//                 * 在这个案例中，它从 main 线程中的 runBlocking 主协程承袭了上下文。
//                 */
//                launch {
//                        Log.i("run parent thread")
//                }
//                /**
//                 * Dispatchers.Unconfined 协程调度器在调用它的线程启动了一个协程，但它仅仅只是运行到第一个挂起点。挂起后，它恢复线程中的协程，
//                 * 而这完全由被调用的挂起函数来决定。非受限的调度器非常适用于执行不消耗 CPU 时间的任务，以及不更新局限于特定线程的任何共享数据（如UI）的协程。
//                 *
//                 * 另一方面，该调度器默认继承了外部的 CoroutineScope。 runBlocking 协程的默认调度器，特别是， 当它被限制在了调用者线程时，
//                 * 继承自它将会有效地限制协程在该线程运行并且具有可预测的 FIFO 调度
//                 */
//                launch (Dispatchers.Unconfined){
////                        delay(3000)// 如果delay ， 会运行在新的线程中，如果没有delay 会运行在父进程中
//                        Log.i("run worker thread")
//                }
//                /**
//                 * 当协程在 GlobalScope 中启动时，使用的是由 Dispatchers.Default 代表的默认调度器。 默认调度器使用共享的后台线程池。
//                 * 所以 launch(Dispatchers.Default) { …… } 与 GlobalScope.launch { …… } 使用相同的调度器。
//                 */
//                launch(Dispatchers.Default) {
//                        Log.i("run default worker")
//                }
//                /**
//                 * newSingleThreadContext 为协程的运行启动了一个线程。 一个专用的线程是一种非常昂贵的资源。
//                 * 在真实的应用程序中两者都必须被释放，当不再需要的时候，使用 close 函数，或存储在一个顶层变量中使它在整个应用程序中被重用。
//                 */
//                launch(newSingleThreadContext("new Thread")) {
//                        Log.i("run new thread")
//                }
//
//        }

        /**
         * 模拟 Android activity 销毁
         */
        runBlocking {
                val activitySimple = ActivitySimple()
                activitySimple.doSomething1()
                activitySimple.doSomething2()
                delay(5000)
                activitySimple.onDestroy()
                delay(5000)
        }





        Log.i("main run end ====>>>>")
}