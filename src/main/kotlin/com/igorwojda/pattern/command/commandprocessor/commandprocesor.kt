package com.igorwojda.pattern.command.commandprocessor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import java.util.concurrent.Executors

/**
 * Src https://asvid.github.io/kotlin-command-pattern
 */

fun main() {
    val firstInvoker = Invoker()
    val secondInvoker = Invoker()

    // no Receiver here, just command parameters
    firstInvoker.setCommand(FirstCommand(1))
    secondInvoker.setCommand(SecondCommand("2"))

    // invoking actions 10x
    repeat(10) {
        firstInvoker.performAction()
        secondInvoker.performAction()
    }
}

// util to generate random delays in suspended methods
fun randomDelay() = Random.nextLong(1000, 3000)

private interface Command {
    // Receiver is passed at the execute() call, not sooner
    suspend fun execute(receiver: Receiver)
}

// commands take only parameters, without Receiver
private class FirstCommand(private val param: Int) : Command {
    override suspend fun execute(receiver: Receiver) {
        println("executing FirstCommand $param")
        receiver.action()
    }
}

private class SecondCommand(private val param: String) : Command {
    override suspend fun execute(receiver: Receiver) {
        println("executing SecondCommand $param")
        receiver.action()
    }
}

private class Receiver {
    // running Receivers action may take a while, thus `suspend` and `delay()` to mimic that
    suspend fun action() {
        println("performing action in Receiver")
        delay(randomDelay())
        println("action finished!")
    }
}

// Commands are stored in FIFO queue in the form of `Channel`
private object CommandProcessor {
    private val commands = Channel<Command>()

    // using separate Scopes for adding and executing commands solves blocking one by another
    private val processScope = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    private val executeScope = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    private val receiver = Receiver()

    fun process(command: Command) {
        processScope.launch {
            println("adding $command to the queue")
            commands.send(command)
        }
    }

    init {
        // waiting for new commands in the queue and executing them as soon as they come
        executeScope.launch {

            // 1 command executed at the same time
            launchProcessor(commands)

            // there can be 5 commands executed at the same time
//            repeat(5) {
//                launchProcessor(commands)
//            }
        }
    }

    // private `extension function` that allows multiple consumers take commands from the same queue
    // each command is executed only once after execution is done, the processor is taking next item from the queue
    private fun CoroutineScope.launchProcessor(channel: ReceiveChannel<Command>) = launch {
        for (command in channel) {
            command.execute(receiver)
        }
    }
}

// Invoker without changes
private class Invoker {
    private lateinit var command: Command

    fun setCommand(command: Command) {
        this.command = command
    }

    fun performAction() {
        CommandProcessor.process(command)
    }
}
