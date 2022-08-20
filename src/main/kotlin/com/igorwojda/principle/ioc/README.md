# Inversion Of Control (IoC) Pattern

Inversion and/or redirecting control to external handler/controller.

## Dependency Injection (DI) Example

The `Dependency Injection` pattern is a more specific version of `Inversion Of Control ` pattern, and is all about
removing dependencies from the code.

Let's consider example where application has a text-editor component that provides spell checking. The
standard code would look something like this:

```kotlin
// Class
class TextEditor {
    private val spellChecker = SpellChecker() // Class creates the Dependency internally

    // use the spellChecker
}

// Client using the class
val textEditor = TextEditor(spellChecker)
```

The `TextEditor` class has control over which `SpellChecker` implementation to use, because the `SpellChecker` is
instantiated directly in the `TextEditor` class.

> **Note**: Dependency between the TextEditor and the SpellChecker exists.

```kotlin
// Class
class TextEditor(private val spellChecker: SpellChecker) {

    // use the spellChecker
}

// Client using the class
val spellChecker = SpellChecker()
val textEditor = TextEditor(spellChecker)  // Client is passing the dependency
```

The client creating the `TextEditor` class has control over which `SpellChecker` implementation to use because it is
providing (injecting) the dependency into the `TextEditor` instance.

Control over dependency creation was transferred (inverted) from the class (`TextEditor`) to the client.

| Tables   |  Class  |  Client |
|----------|:-------:|--------:|
| Standard | Control |         |
| IoC      |   no    | Control |
