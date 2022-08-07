//Client
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
    private val turnOnLightCommand: TurnOnLightCommand
    private val turnOffLightCommand: TurnOffLightCommand,
) {
    private val lastExecutedCommand: Command? = null

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

    public fun undoButtonClick()
    {
        lastExecutedCommand.unexecute()
        lastExecutedCommand = null
    }
}

private interface Command {
    fun execute()
    fun unexecute()
}

private class TurnOnLightCommand(private val lamp: Lamp) : Command {
    override fun execute() {
        lamp.turnOn()
    }

    override fun unexecute() {
        lamp.turnOff()
    }
}

private class TurnOffLightCommand(private val lamp: Lamp) : Command {
    override fun execute() {
        lamp.turnOff()
    }

    override fun unexecute() {
        lamp.turnOn()
    }
}

private class Lamp {
    fun turnOn() {}
    fun turnOff() {}
}
