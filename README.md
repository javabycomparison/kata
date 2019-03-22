# Java by Comparison Kata

The [Java by Comparison Kata](https://github.com/javabycomparison/kata) requires you to have a copy of [Java by Comparison](https://java.by-comparison.com) because you need to apply the refactorings from the book.

With this kata, you'll train yourself to spot problematic code and make it better.

## Prerequisites

[Java by Comparison](https://java.by-comparison.com), the Java 8 compiler, and a curious mind.

## Story

> *Today is your first day at a new job.*
>
> **Boss**: Great to have you here. 
>
> **You**: Yes, I'm excited to dive into the world of software metrics.
>
> **Boss**: Awesome. Let me show you the software we are currently making a ton of money with.
>
> *Boss points you to the code of this kata*
>
> **Boss**: It's calculating metrics for Java and even Python source code. We have literally thousands of customers that work with this release everyday. Thing is, our backlog is packed and we need you to pump out features quickly, otherwise, our customers will leave.
>
> **Boss**: But, of course, you just started. So have a look at the code. You may clean it up so you can start those features. 
>
> **Boss**: Let's say, you have 2 hours? Should be fine, I think. 
>
> **You**: ...
>
> *So you start cleaning that legacy code up.*

## House Rules

- You may only change the code according to a comparison from the book [Java by Comparison](https://java.by-comparison.com).
- The commit message must be the name of the comparison, e.g., "Avoid Negations". 
- You may apply a comparison multiple times. (Within the same or separate commits, both is fine)

## Alternative Rules

- Any other Kata or Code Retreat rules may apply.

## Usage

```bash
# download kata
$ git clone git@github.com:javabycomparison/kata
$ cd kata

# execute tests
$ ./mvnw test

# run
$ ./mvnw exec:java
# run with parameters
$ ./mvnw exec:java -Dexec.args="src smry"
```
