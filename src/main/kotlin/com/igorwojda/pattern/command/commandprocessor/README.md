# Command processor 

Command pattern is often used with command processor.

The Processor can queue and execute commands according to its internal logic, but from the Invoker
point of view, it doesn’t really matter. The Receiver can be moved from a command to the Processor,
thus the commands will only take its parameters, and the rest will be handled by Processor when
calling the execute().


