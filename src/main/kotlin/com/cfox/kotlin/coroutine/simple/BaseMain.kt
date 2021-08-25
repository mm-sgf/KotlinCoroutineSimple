package com.cfox.kotlin.coroutine.simple

import com.cfox.kotlin.coroutine.Log
import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

fun main() {
        /**
         * 一个简单协成
         *
         */
//        GlobalScope.launch {
//                val result = runBlocking() {
//                        Log.i("run launch Blocking")
//                        delay(2000)// 挂起
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
        /**
         * repeat(n) 创建n 个协成
         */
//        runBlocking {
//                repeat(1000) {// 每间隔2 s 执行一个协成
//                        delay(2000)
//                        Log.i("--")
//                }
//        }


//        runBlocking {
//                coroutineScope {
//                        /**
//                         * 下面内容是运行在同一个线程中，
//                         * 线运行 repeate ， 在repeat 中 delay 挂起后 运行下面的runBlocking
//                         */
//                        GlobalScope.launch (this.coroutineContext) {
//                                repeat(10) {
//                                        Log.i("launch  repeat coroutine")
//                                        delay(2000)
//                                }
//                        }
//
//                        runBlocking {
//                                Log.i("run block ")
//                                delay(10_000)
//                        }
//                }
//        }

//        runBlocking {
//                coroutineScope {
//                        val job = launch {
//                                repeat(10) {
//                                        Log.i("repeat  message ===>")
//                                        delay(1000)
//                                }
//                        }
//
//                        delay(3000)
//                        Log.i("cancel job")
//                        /**
//                         * 一旦 main 函数调用了 job.cancel，我们在其它的协程中就看不到任何输出，因为它被取消了。
//                         * 这里也有一个可以使 Job 挂起的函数 cancelAndJoin 它合并了对 cancel 以及 join 的调用。
//                         *
//                         * 协程的取消是 协作 的。一段协程代码必须协作才能被取消。 所有 kotlinx.coroutines 中的挂起函数都是 可被取消的 。
//                         * 它们检查协程的取消， 并在取消时抛出 CancellationException。
//                         * 然而，如果协程正在执行计算任务，并且没有检查取消的话，那么它是不能被取消的
//                         *
//                         * 总结：只能取消挂起的协成
//                         */
////                        job.cancel()
//                        job.cancelAndJoin()
//                }
//        }
        /**
         * 通过状态进行取消运行中的
         * 调用 cancel 后 isActivity = false
         */
//        runBlocking {
//                val job = launch (Dispatchers.Default){
//                        var i = 0
//                        while (isActive) {
//                                Thread.sleep(500)
//                                Log.i("is activity i : $i  time : ${i * 500 }")
//                                i++
//                        }
//                }
//
//                delay(3000)
//                Log.i("start cancel")
//                job.cancelAndJoin()
//        }
        /**
         * 使用finally 添加取消时必须运行的代码
         */
//        runBlocking {
//                val job = launch {
//                        Log.i("repeat start ")
//                        try {
//                                repeat(10) {  // repeat  只会反复执行 repeat 的fun
//                                        Log.i("repeat ===>>>")
//                                        delay(1000)
//                                }
//                        } finally {
//                                // 调用 cancel 方法时，取消挂起的协成后，代码继续执行完毕
//                                // 所以使用try finally 添加结束任务必执行代码块， 使用这种方式释放资源
//                                Log.i("must run code ....")
//                        }
//                }
//
//                Log.i("delay ")
//                delay(3000)
//                job.cancelAndJoin()
//        }

//        runBlocking {
//                /**
//                 * 使用 withTimeout 进行超时处理， withTimeout 抛出了 TimeoutCancellationException
//                 */
//                try {
//                        withTimeout(3000) {
//                                repeat(1000){ i ->
//                                        Log.i("with time out  : $i")
//                                        delay(500)
//                                }
//                        }
//                } catch (e : Exception) {
//                        Log.i("with time out throw exception ")
//                } finally {
//                        Log.i("with time out finally ")
//                }
//
//                withTimeout(2000) {
//                        repeat(1000){ i ->
//                                Log.i("with time out  : $i")
//                                delay(500)
//                        }
//                }
//        }

//        runBlocking {
//                /**
//                 * 如果超出time , 则返回null , 不会抛出异常
//                 * 如果在time 时间内完成，则返回指定内容
//                 */
//                val result = withTimeoutOrNull(2000) {
//                        repeat(10) { i ->
//                                Log.i("with time out  : $i")
//                                delay(500)
//                        }
//                        "Done"
//                }
//                Log.i("result:$result")
//        }

//        runBlocking {
//                /**
//                 * 获取拿到的执行时间
//                 */
//                val time = measureTimeMillis {
//                        val resultOne = doSomethingOne()
//                        val resultTwo = doSomethingTwo()
//                        Log.i("result one:$resultOne  two:$resultTwo")
//                }
//                Log.i("use time : $time")
//        }
        /**
         * 使用 async 并发
         * async 就类似于 launch。它启动了一个单独的协程，这是一个轻量级的线程并与其它所有的协程一起并发的工作。
         * 不同之处在于 launch 返回一个 Job 并且不附带任何结果值，而 async 返回一个 Deferred —— 一个轻量级的非阻塞 future，
         * 这代表了一个将会在稍后提供结果的 promise。你可以使用 .await() 在一个延期的值上得到它的最终结果， 但是 Deferred 也是一个 Job，
         * 所以如果需要的话，你可以取消它。
         */
//        runBlocking {
//                val time = measureTimeMillis {
//                        /**
//                         * 注意，如果我们只是在 println 中调用 await，而没有在单独的协程中调用 start，这将会导致顺序行为，
//                         * 直到 await 启动该协程 执行并等待至它结束，这并不是惰性的预期用例。 在计算一个值涉及挂起函数时，
//                         * 这个 async(start = CoroutineStart.LAZY) 的用例用于替代标准库中的 lazy 函数。
//                         *
//                         * start 使用 start = CoroutineStart.LAZY ： 当只有调用的时候才会执行， 如果不调用是不会执行
//                         */
//                        val resultOne = async (start = CoroutineStart.LAZY){
//                                Log.i("async one ==> 111")
//                                doSomethingOne()
//                                Log.i("async one ==> 222")
//                        }
////                        resultOne.start() // 启动一部协成
//
//                        val resultTwo = async {
//                                Log.i("async two ===>111")
//                                doSomethingTwo()
//                                Log.i("async two ===>222")
//                        }
//                        Log.i("result one:${resultOne.await()}  two:${resultTwo.await()}")
//                }
//
//                Log.i("use time:$time")
//                /**
//                 * 上面异步协成是运行在同一线程，且都是挂起
//                 */
//        }

//        runBlocking {
//                val result = async (Dispatchers.IO, CoroutineStart.LAZY){
//                        Log.i("io async lazy")
//                }
//
//                Log.i("result : ${result.await()}")
//        }

//        runBlocking {
//                Log.i("coroutine : ${coroutineContext[Job]}")
//        }

        Log.i("main run end ====>>>>")
}

suspend fun doSomethingOne() : Int {
       delay(3000)
//        Thread.sleep(2000)
        return 20
}

suspend fun doSomethingTwo() : Int {
        delay(2000)
//        Thread.sleep(4000)
        return 30
}