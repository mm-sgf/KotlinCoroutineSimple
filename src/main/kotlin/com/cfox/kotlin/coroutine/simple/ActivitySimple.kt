package com.cfox.kotlin.coroutine.simple

import com.cfox.kotlin.coroutine.Log
import kotlinx.coroutines.*

class ActivitySimple  {
//        private val scope = CoroutineScope(Dispatchers.Main)
        private val scope = CoroutineScope(newSingleThreadContext("main-t"))
        fun doSomething1() {
                scope.launch {
                        repeat(1000){ i->
                                delay(1000)
                                Log.i("background thread -1-   $i")
                        }
                }
        }

        fun doSomething2() {
                scope.launch {
                        repeat(1000){ i->
                                delay(1000)
                                Log.i("background thread -2-  $i")
                        }
                }
        }

        fun onDestroy() {
                Log.i("activity onDestroy")
                scope.cancel()
        }
}