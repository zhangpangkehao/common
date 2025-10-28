package com.xiaojinzi.tally.module.imagepreview.lib.util

import androidx.compose.runtime.*
import java.util.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @program: ImageViewer
 *
 * @description:
 *
 * @author: JVZIYAOYAO
 *
 * @create: 2023-05-24 12:15
 **/
class Ticket {

    private var ticket by mutableStateOf("")

    private val ticketMap = mutableMapOf<String, Continuation<Unit>>()

    suspend fun awaitNextTicket() = suspendCoroutine<Unit> { c ->
        ticket = UUID.randomUUID().toString()
        ticketMap[ticket] = c
    }

    private fun clearTicket() {
        ticketMap.forEach {
            it.value.resume(Unit)
            ticketMap.remove(it.key)
        }
    }

    @Composable
    fun Next() {
        LaunchedEffect(ticket) {
            clearTicket()
        }
    }

}