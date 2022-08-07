# Command pattern 

Command pattern decouples the invoker and the receiver by encapsulating an action (command). 
It wraps the request into a specific object (command) that has all the information necessary to perform its task.

You can think of it as the next stage of refactoring where at first the code is extracted to a separate method, and 
then moved to a separate object (taking the arguments needed to execute the request).


## Entities

- Command interface - contains execute methods. It is the core of the contract.
- Concrete Command - creates a binding between the `Receiver` and an action (and optionally undo action)
- Invoker - uses the `Command` to perform an action. May keep track of the commands, so they can be undone 
  (unexeuted)
- Receiver - object used by the `Command` to complete its task - contains the actual steps to perform the action.
- Client - creates a concrete command instance and binds it with the `Receiver` and sets it in the `Invoker`

## When to use the Command Pattern?

- When the requests need to be handled in certain time occurrences and according to different triggers situations
- When the client and the service provider needs to be decoupled
- When there is a need for undo\rollback functionality for certain operations
- When the history of requests required
- When there is a need to add new commands
- When there is a need for parameterizing objects according to an action

## Variations

- Command may contain unexecute method to allow operation undo 
- Command may contain multiple receivers

## Examples

- [Command Abstract](command-abstract.kt) - minimal code sample demonstrating command pattern  
- [Universal Remote](command-universal-remote.kt) - example of command pattern with undo functionality
- 
