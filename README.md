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

```kotlin
fun hasData(index: Long): Boolean
```
