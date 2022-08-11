<%css "/util/common.css" %>

# Count Blocks of Ones

<div class="logo">
    <img src="../../images/count_blocks_of_ones_logo.png">
</div>

Implement an interface that supports the following two operations
for the given binary string: get the number of 1's in the string
and get the number of blocks of 1's in the string.

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
