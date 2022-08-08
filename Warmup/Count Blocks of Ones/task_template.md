<%css "/util/common.css" %>

# Count Blocks of Ones

Implement an interface

```Kotlin
interface Blocks {
    fun countOnes(): Int
    fun countBlocksOfOnes(): Int
}
```

### Example 1

<div class="sample">

| Input                            | Returns                                 |
|----------------------------------|-----------------------------------------|
| `createBlocksInstance("001001")` | [Blocks](psi_element://Blocks) instance |
| `countOnes()`                    | 2                                       |
| `countBlocksOfOnes()`            | 2                                       |

</div>

### Example 2

<div class="sample">

| Input                              | Returns                                 |
|------------------------------------|-----------------------------------------|
| `createBlocksInstance("11100101")` | [Blocks](psi_element://Blocks) instance |
| `countOnes()`                      | 5                                       |
| `countBlocksOfOnes()`              | 3                                       |

</div>
