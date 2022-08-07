# Command pattern 

Command pattern decouples the invoker and the receiver by encapsulating an action (command).

## Entities

- Command interface - contains execute methods. It is the core of the contract.
- Command implementation - creates a binding between the receiver(s) and an action (and optionally undo action)
- Invoker - instructs the command to perform an action. May keep track of the commands, so they can be undone 
  (unexeuted)
- Receiver - contains the actual steps to perform the action.

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
