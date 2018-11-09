# DynamicArray

## Description

DynamicArray<E>

DynamicArray is a kotlin class that simulates an array, but is dynamic. The array initial has no elements allocated and only allocates elements in the array when either that element is being acceded or certain elements after it are being accessed.

## Methods

```kotlin
fun get(index: Long): E
```

```kotlin
fun set(index: Long, data: E)
```

## Notes

- I'm using Longs just because they're bigger, which is probably completely unnecessary and will probably be changed back to Int later

- This may not be ready for use in java code as it does not yet have the @JVM annotations yet

- I wrote this in kotlin because it is my coding comfort food
