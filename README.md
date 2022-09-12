# Application Environment

## The purpose

This is a micro-library for access to environment variables the easy way.

The purpose of creation this library is maintainability and testability applications, which use system/environment
variables.

## Get started

This library compatible with modular java. You need to include into your module-into.java the module with library:

```java
requires madzi.application.environment;
```

and after that library ready for use.

First of all, you need to create environment:

```java
final var environment = Environment.builder().build();
```

To get some variable from environment you can use one of three methods:

1. `String value = getProperty(variable);`
1. `String value = getProperty(variable, defaultValue);`
1. `String value = getRequiredProperty(variable);`
