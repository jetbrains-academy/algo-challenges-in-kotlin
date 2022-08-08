<%css "/util/common.css" %>

# Count Blocks of Ones with Updates

Implement an interface

```Kotlin
interface CountBlocksOfOnesWithUpdates {
    fun countOnes(): Int
    fun countBlocksOfOnes(): Int
    fun flip(index: Int)
}
```

### Example

<div class="sample">

| Input                      | Returns                                                                             |
|----------------------------|-------------------------------------------------------------------------------------|
| `createInstance("001001")` | [CountBlocksOfOnesWithUpdates](psi_element://CountBlocksOfOnesWithUpdates) instance |
| `countOnes()`              | 2                                                                                   |
| `countBlocksOfOnes()`      | 2                                                                                   |
| `flip(3)`                  |                                                                                     |
| `countOnes()`              | 3                                                                                   |
| `countBlocksOfOnes()`      | 2                                                                                   |
| `flip(4)`                  |                                                                                     |
| `countOnes()`              | 4                                                                                   |
| `countBlocksOfOnes()`      | 1                                                                                   |

</div>
