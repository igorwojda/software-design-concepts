fun main() {
    // Receiver
    val light = Light()

    // Commands
    val turnOnLightCommand = TurnOnLightCommand(light)
    val turnOffLightCommand = TurnOffLightCommand(light)

    // Invoker
    val universalRemote = UniversalRemote(
        turnOnLightCommand,
        turnOffLightCommand
    )

    // Usage
    universalRemote.turnOnButtonClick()
    universalRemote.turnOffButtonClick()

    // Undo
    universalRemote.undoLastCommand()
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

    public undoLastCommand()
    {
        lastExecutedCommand.unexecute()
        lastExecutedCommand = null
    }
}

private interface Command {
    fun execute()
    fun unexecute()
}

private class TurnOnLightCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOn()
    }

    override fun unexecute() {
        light.turnOff()
    }
}

private class TurnOffLightCommand(private val light: Light) : Command {
    override fun execute() {
        light.turnOff()
    }

    override fun unexecute() {
        light.turnOn()
    }
}

private class Light {
    fun turnOn() {}
    fun turnOff() {}
}
