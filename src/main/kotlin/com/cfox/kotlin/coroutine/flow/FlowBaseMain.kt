package com.cfox.kotlin.coroutine.flow

import com.cfox.kotlin.coroutine.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

fun simple1() : List<Int> = listOf(1, 2,3)

/**
 * 如果使用一些消耗 CPU 资源的阻塞代码计算数字（每次计算需要 1000 毫秒）那么我们可以使用 Sequence 来表示数字：
 */
fun simple2() : Sequence<Int> = sequence {
        for (i in 1 .. 3) {
                Thread.sleep(100)
                yield(i)
        }
}

suspend fun simple3() : List<Int> {
        delay(100)
        return listOf(1, 2,3)
}

fun main() {

//        simple1().forEach {
//                Log.i("s1: $it")
//        }
//
//        simple2().forEach {
//                Log.i("s2: $it")
//        }

//        runBlocking {
//                simple3().forEach {
//                        Log.i("s3: $it")
//                }
//        }
//
//        runBlocking {
//                delay(5000)
//        }
        /**
         * 简单的flow
         */
//        runBlocking {
//                Log.i("get flow")
//                val fs = flowSimple()
//                Log.i("flow collect start")
//                fs.collect {
//                        Log.i("flow value :$it")
//                }
//                Log.i("flow collect end")
//        }
        /**
         * 使用 withTimeoutOrNull 取消一个Flow
         */
//        runBlocking {
//                withTimeoutOrNull(200) {
//                        flowSimple().collect {
//                                Log.i("flow value :$it")
//                        }
//                }
//        }
        /**
         *  flow 构建器
         */
//       runBlocking {
//               (1..6).asFlow().collect {
//                       Log.i("flow value :$it")
//               }
//       }
        /**
         * flow 中使用 map 进行过度控制，
         * 对一次数据进行映射处理后传给后面进行处理
         */
//        runBlocking {
//                (1.. 10).asFlow()
//                        .map { request ->
//                                Log.i("delay 1 s")
//                                delay(1000)
//                                Log.i("delay 1 s end")
//                                performRequest(request)
//                        }
//                        .collect {
//                               Log.i("print map value :$it")
//                        }
////                        .collect(object : FlowCollector<String> {
////                                override suspend fun emit(value : String) {
////                                }
////                        })
//        }
        /**
         * transform ：
         * 和map 相似，在transform 中可以多次发送数据，来完成数据的转换
         */
//        runBlocking {
//                flowLoop((1..10).toList())
//                        .transform { request ->
//                                emit("mark request :$request")
//                                emit(performRequest(request))
//                        }
//                        .collect {
//                                Log.i("print transform value :$it")
//                        }
//        }
        /**
         *  take
         *  限长操作， 获取前几个数据
         *  限长过渡操作符（例如 take）在流触及相应限制的时候会将它的执行取消。协程中的取消操作总是通过抛出异常来执行
         */
//        runBlocking {
//                takeFlow()
//                        .take(2)
//                        .collect {
//                                Log.i("print take value :$it")
//                        }
//        }
        /**
         * 末端操作符
         * 末端操作符是在流上用于启动流收集的挂起函数。 collect 是最基础的末端操作符，但是还有另外一些更方便使用的末端操作符：
         * 转化为各种集合，例如 toList 与 toSet。
         * 获取第一个（first）值与确保流发射单个（single）值的操作符。
         * 使用 reduce 与 fold 将流规约到单个值。
         */
//        runBlocking {
////                val last = flowLoop((1..9).toList())
////                        .last()
////                Log.i("last: $last")
//
////                val first = flowLoop((1..9).toList())
////                        .first()
////                // 使用first  只执行一次，即使设置多次，也只会执行一次
////                Log.i("first : $first")
//
//                val reduce = flowLoop((1..9).toList())
//                        .map {
//                                delay(100)
//                                it
//                        }.reduce { accumulator, value ->
//                                Log.i("accumulator : $accumulator    value: $value")
//                                accumulator + value
//                        }
//                Log.i("reduce : $reduce")
//        }

//        runBlocking {
//                flowChangeContext().collect {
//                        Log.i("print flow change context value :$it")
//                }
//        }
        /**
         * 改变flow 的上下文
         *  flowOn 函数，该函数用于更改流发射的上下文。
         */
//        runBlocking {
//                flowSimple()
//                        .flowOn(Dispatchers.IO)
//                        .collect {
//                                Log.i("print flowOn value :$it")
//                        }
//        }
        /**
         *  下面这个说明收集一次流的时间是 4 s ,每一次发送数据都要等上一次执行完
         *  所以，收集4 次后需要16s + 的时间
         */
//        runBlocking {
//               val time = measureTimeMillis {
//                        flowSimple().collect{
//                                delay(300)
//                                Log.i("print ----> value :$it")
//                        }
//                }
//                Log.i("print use time :$time")
//        }
        /**
         * 使用buffer 将不会阻塞发射器发送数据，数据会被buffer 进行收集，
         * 所以整个运行时间 ： 第一次发射的时间 1000ms + （3000ms）*  4
         */
//        runBlocking {
//                val time = measureTimeMillis {
//                        flowSimple()
//                                .buffer()
//                                .collect{
//                                delay(300)
//                                Log.i("print ----> value :$it")
//                        }
//                }
//                Log.i("print use time :$time")
//        }
        /**
         * 使用 conflate() 获取发射器中最新的数据
         * 当流代表部分操作结果或操作状态更新时，可能没有必要处理每个值，而是只处理最新的那个， conflate 操作符可以用于跳过中间值。
         */
//        runBlocking {
//                val time = measureTimeMillis {
//                        flowSimple()
//                                .conflate()
//                                .collect{
//                                        Log.i("print ----> value :$it")
//                                        delay(300)
//                                }
//                }
//                Log.i("print use time :$time")
//        }
        /**
         * 处理最新值
         * 当发射器和收集器都很慢的时候，合并是加快处理速度的一种方式。它通过删除发射值来实现。
         * 另一种方式是取消缓慢的收集器，并在每次发射新值的时候重新启动它。
         * 有一组与 xxx 操作符执行相同基本逻辑的 xxxLatest 操作符，但是在新值产生的时候取消执行其块中的代码。
         * 让我们在先前的示例中尝试更换 conflate 为 collectLatest：
         */
//        runBlocking {
//                val time = measureTimeMillis {
//                        flowSimple()
//                                .buffer()
//                                .collectLatest {
//                                        Log.i("print start value:$it")
//                                        delay(300) // 挂起到当前的位置，恢复后拿最新的值
////                                        Thread.sleep(300)
//                                        Log.i("print end value:$it")
//                                }
//                }
//                Log.i("print use time:$time")
//        }
        /**
         * =============================================================
         */
        /**
         * 使用 zip 合并多个flow , 且发射size 是最短的为准
         */
//        runBlocking {
//                val num = (1..9).asFlow()
//                val strs = flowOf("a","b","c", "d")
//                num.zip(strs) {
//                        a, b ->
//                        "$a -> $b"
//                }.collect {
//                        Log.i("print zip value : $it")
//                }
//        }
        /**
         * Combine
         * 当流表示一个变量或操作的最新值时（请参阅相关小节 conflation），可能需要执行计算，这依赖于相应流的最新值，
         * 并且每当上游流产生值的时候都需要重新计算。这种相应的操作符家族称为 combine。
         *
         * Combine 和 zip 不同
         * zip 是一一对应的关系， 只有接收到两个flow 发射的值才会合并向下执行。 所以，发射的最大次数是flow 中size最小的
         * Combine ， 是只要有一个flow 发射了新的数据，就会重新合并向下执行，所以，发射的最大次数是flow 中size最大的
         */
//        runBlocking {
//                val nums = (1..9).asFlow().onEach { delay(300) } // 发射数字 1..3，间隔 300 毫秒
//                val strs = flowOf("one", "two", "three").onEach { delay(400) } // 每 400 毫秒发射一次字符串
//                val startTime = System.currentTimeMillis() // 记录开始的时间
////                nums.zip(strs) { a, b -> "$a -> $b" } // 使用“zip”组合单个字符串
////                        .collect { value -> // 收集并打印
////                                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
////                        }
//
//                nums.combine(strs) { a, b -> "$a -> $b" } // 使用“zip”组合单个字符串
//                        .collect { value -> // 收集并打印
//                                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//                        }
//        }

        runBlocking {

        }
        Log.i("main run end ====>>>>")
}

/**
 * withContext 发出错误
 * 然而，长时间运行的消耗 CPU 的代码也许需要在 Dispatchers.Default 上下文中执行，并且更新 UI 的代码也许需要在 Dispatchers.Main 中执行。
 * 通常，withContext 用于在 Kotlin 协程中改变代码的上下文，但是 flow {...} 构建器中的代码必须遵循上下文保存属性，并且不允许从其他上下文中发射（emit）。
 * Exception in thread "main" java.lang.IllegalStateException: Flow invariant is violated:
 */
fun flowChangeContext() : Flow<Int> = flow {
        withContext(Dispatchers.Default) {
                for (i in 1..9) {
                        Thread.sleep(100)
                        emit(i)
                }
        }
}

fun takeFlow () : Flow<Int> = flow {
        try {
                emit(1)
                emit(2)
                Log.i("This line will not execute")
                emit(3)
        } catch (e : Exception) {
                Log.i("take throw exception $e")
        } finally {
                Log.i("take finally")
        }
}

fun <T>  flowLoop (ts : List<T>) : Flow<T> {
        val flow = flow {
                ts.forEach {
                        Log.i("flow loop value :$it ")
                        emit(it)
                }
        }
        return flow
}

/**
 * Flow 是一种类似于序列的冷流 — 这段 flow 构建器中的代码直到流被收集的时候才运行。
 * 冷流
 */
fun flowSimple() :  Flow<Int> = flow {
        Log.i("flow start run ===>")
        for (i in 0 .. 3) {
                delay(100)
                Log.i("flow emit : $i")
                emit(i)
        }
        Log.i("flow end run ===>")
}

suspend fun performRequest(value : Int) : String{
        return "perform value to String : $value"
}

fun requestFlow(i : Int) :Flow<String> = flow {
        emit("$i : First")
        delay(500)
        emit("$i : Second")
}
