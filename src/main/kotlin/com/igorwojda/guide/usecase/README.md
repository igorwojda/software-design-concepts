# Use Case Definition Guidelines

- [Use Case Definition Guidelines](#use-case-definition-guidelines)
  - [Use Case Defines A Single Operation](#use-case-defines-a-single-operation)
  - [Use Case Must Be Stateless](#use-case-must-be-stateless)
  - [Use Case Composition](#use-case-composition)
  - [Use Case Inheritance](#use-case-inheritance)
  - [Use Case Naming Convention](#use-case-naming-convention)
    - [One-time Operation](#one-time-operation)
    - [Stream Subscription](#stream-subscription)

The “use case” objects, also known as “interactors”, are the most recognizable aspects of Robert Martin’s “Clean Architecture” (part or the `domain` layer).

Use case contains the application business logic. Use case describes the way which a user interacts with the application e.g.

- Create order
- Login user
- Open new bank account
- Transfer funds
- Filter adults users
- Update email address



There are multiple rules worth following to define a high quality use case.

## Use Case Defines A Single Operation

Use case should have a single public method representing a single interaction with the system. This method is called
`execute`:

```kotlin
class GetAccountBalanceUseCase {
    fun execute(accountName: String): Double {
    }
}

// usage
val getAccountBalanceUseCase = GetAccountBalanceUseCase()
getAccountBalanceUseCase.execute("Personal")
```

In Kotlin this single method can be defined using Kotlin
[invoke](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/invoke.html) operator:

```kotlin
class GetAccountBalanceUseCase {
    operator fun invoke(accountName: String): Double {
    }
}
```

The usage of the `invoke` operator allows to "directly" invoke use case without explicit method call:

```kotlin
val getAccountBalanceUseCase = GetAccountBalanceUseCase()
getAccountBalanceUseCase("Personal")
```

## Use Case Must Be Stateless

Use case should not hold the state. If data is requred to perform operation it can be provided in the arguments and/or retrieved the from `repository`:

```kotlin
class GetAccountBalanceUseCase(
    private val accountRepository: AccountRepository,
) {
    // data provided in the arguments, but not saved in the use case
    operator fun invoke(accountName: String): Double {
        // data is retrieved from repository, but not saved in the use case
        accountRepository.getAccountBalance(accountName)
    }
}
``` 

## Use Case Composition

Use case another Use Case to simplify logic. In below example `GetAccountBalanceUseCase` is using `GetCurrentUserUseCase` to retrieve the current user:

```kotlin
class GetAccountBalanceUseCase(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val accountRepository: AccountRepository,
) {
    operator fun invoke(accountName: String): Double {
        val user = getCurrentUserUseCase()
        repository.getAccountBalance(user, accountName)
    }
}
```

## Use Case Inheritance

Use Case can inherit From another Use Case to simplify logic.

## Use Case Naming Convention

Use case name is derived from the type of the operation.

### One-time Operation

Name of the use case providing one-time operation (data retrieval, data update, etc.) should start with the
verb prefix eg.`Get`, `Set`, `Increment`.

```kotlin
class GetAccountBalanceUseCase {
    operator fun invoke(accountName: String): Double {
    }
}
```

The one-time operation can be synchronous (above example) or asynchronous. When one-time operation is asynchronous then use case name does not change, but the `suspended` modifier is added to the function:

```kotlin
class GetAccountBalanceUseCase {
    suspend operator fun invoke(accountName: String): Double {
    }
}
```

### Stream Subscription

By its definition data stream (Kotlin Flow stream, RxJava stream etc.) is always asynchronous. Name of the use case
providing data stream should start with the `Observe` prefix.
