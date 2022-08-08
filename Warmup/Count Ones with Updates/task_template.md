<%css "/util/common.css" %>

# Count Ones with Updates

Implement an interface

```Kotlin
interface CountOnesWithUpdates {
    fun countOnes(): Int
    fun flip(index: Int)
}
```

### Example 1

<div class="sample">

| Input                      | Returns                                                             |
|----------------------------|---------------------------------------------------------------------|
| `createInstance("001001")` | [CountOnesWithUpdates](psi_element://CountOnesWithUpdates) instance |
| `countOnes()`              | 2                                                                   |
| `flip(0)`                  |                                                                     |
| `countOnes()`              | 3                                                                   |
| `flip(1)`                  |                                                                     |
| `countOnes()`              | 4                                                                   |
| `flip(2)`                  |                                                                     |
| `countOnes()`              | 3                                                                   |

</div>
