package com.igorwojda.pattern.command.pure

// Client
fun main() {
    val receiver = Receiver()

    val command1 = Command1(receiver)
    val command2 = Command2(receiver)

    val invoker = Invoker(
        command1,
        command2
    )

    // Usage
    invoker.performAction1()
    invoker.performAction2()
}

private class Invoker(
    private val command1: Command1,
    private val command2: Command2,
) {

    fun performAction1() {
        command1.execute()
    }

    fun performAction2() {
        command2.execute()
    }
}

private interface Command {
    fun execute()
}

private class Command1(private val receiver: Receiver) : Command {
    override fun execute() {
        receiver.performAction1()
    }
}

private class Command2(private val receiver: Receiver) : Command {
    override fun execute() {
        receiver.performAction2()
    }
}

private class Receiver {
    fun performAction1() {
        println("performAction1")
    }

    fun performAction2() {
        println("performAction2")
    }
}
