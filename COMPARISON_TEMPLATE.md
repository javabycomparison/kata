# *actionable name starting with a verb*

## BEFORE

```java
class Greeter {
    public void greet(String name) {
        // BEGIN
        System.out.println("Hello, " + name + "!");
        // END
    }
}
```
*why this code has an issue in detail*

## AFTER

```java
class Greeter {
    public void greet(String name) {
        // BEGIN
        var greeting = "Hello, " + name + "!";
        System.out.println(greeting);
        // END
    }
}
```

*why this code is better, can be short*
