package com.igorwojda.pattern.command.universalremote

// Client
fun main() {
    // Receiver
    val lamp = Lamp()

    // Commands
    val turnOnLightCommand = TurnOnLightCommand(lamp)
    val turnOffLightCommand = TurnOffLightCommand(lamp)

    // Invoker
    val universalRemote = UniversalRemote(
        turnOnLightCommand,
        turnOffLightCommand
    )

    // Usage
    universalRemote.turnOnButtonClick()
    universalRemote.turnOffButtonClick()

    // Undo
    universalRemote.undoButtonClick()
}

private class UniversalRemote(
    private val turnOnLightCommand: TurnOnLightCommand,
    private val turnOffLightCommand: TurnOffLightCommand,
) {
    private var lastExecutedCommand: Command? = null

    fun turnOnButtonClick() {
        executeCommand(turnOnLightCommand)
    }

    fun turnOffButtonClick() {
        executeCommand(turnOffLightCommand)
    }

    private fun executeCommand(command: Command) {
        command.execute()
        lastExecutedCommand = command
    }

    fun undoButtonClick()
    {
        lastExecutedCommand?.unexecute()
        lastExecutedCommand = null
    }
}

private interface Command {
    fun execute()
    fun unexecute()
}

private class TurnOnLightCommand(private val lamp: Lamp) : Command {
    override fun execute() {
        lamp.switch(true)
    }

    override fun unexecute() {
        lamp.switch(false)
    }
}

private class TurnOffLightCommand(private val lamp: Lamp) : Command {
    override fun execute() {
        lamp.switch(false)
    }

    override fun unexecute() {
        lamp.switch(true)
    }
}

private class Lamp {
    fun switch(on: Boolean) {
        val state = if (on) "ON" else "OFF"
        println("turning lamp $state")
    }
}
