<%css "/util/common.css" %>

# Count Blocks of Ones

Implement an interface

```Kotlin
interface CountBlocksOfOnes {
    fun countOnes(): Int
    fun countBlocksOfOnes(): Int
}
```

### Example 1

<div class="sample">

| Input                      | Returns                                                       |
|----------------------------|---------------------------------------------------------------|
| `createInstance("001001")` | [CountBlocksOfOnes](psi_element://CountBlocksOfOnes) instance |
| `countOnes()`              | 2                                                             |
| `countBlocksOfOnes()`      | 2                                                             |

</div>

### Example 2

<div class="sample">

| Input                        | Returns                                                       |
|------------------------------|---------------------------------------------------------------|
| `createInstance("11100101")` | [CountBlocksOfOnes](psi_element://CountBlocksOfOnes) instance |
| `countOnes()`                | 5                                                             |
| `countBlocksOfOnes()`        | 3                                                             |

</div>
