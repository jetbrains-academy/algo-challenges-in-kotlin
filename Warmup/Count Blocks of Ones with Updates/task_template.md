<%css "/util/common.css" %>

# Count Blocks of Ones with Updates

Implement an interface

```Kotlin
interface Blocks {
    fun countOnes(): Int
    fun countBlocksOfOnes(): Int
    fun flip(index: Int)
}
```

### Example

<div class="sample">

| Input                            | Returns                                 |
|----------------------------------|-----------------------------------------|
| `createBlocksInstance("001001")` | [Blocks](psi_element://Blocks) instance |
| `countOnes()`                    | 2                                       |
| `countBlocksOfOnes()`            | 2                                       |
| `flip(3)`                        |                                         |
| `countOnes()`                    | 3                                       |
| `countBlocksOfOnes()`            | 2                                       |
| `flip(4)`                        |                                         |
| `countOnes()`                    | 4                                       |
| `countBlocksOfOnes()`            | 1                                       |

</div>
