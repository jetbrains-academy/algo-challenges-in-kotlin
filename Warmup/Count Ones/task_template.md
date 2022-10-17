<%css "/util/common.css" %>

# Count Ones
<div class="logo">
    <img src="../../images/count_ones_logo.png">
</div>

Implement an interface that allows one to get the number of
1's in a given binary string.

```Kotlin
interface CountOnes {
    fun countOnes(): Int
}
```


### Example 1

<div class="sample">

| Input                      | Returns                                       |
|----------------------------|-----------------------------------------------|
| `createInstance("001001")` | [CountOnes](psi_element://CountOnes) instance |
| `countOnes()`              | 2                                             |

</div>

### Example 2

<div class="sample">

| Input                        | Returns                                       |
|------------------------------|-----------------------------------------------|
| `createInstance("11100101")` | [CountOnes](psi_element://CountOnes) instance |
| `countOnes()`                | 5                                             |

</div>

<div class="hint">
A linear scan of the string is sufficient.
</div>

<div class="hint">
<%include "solution.md" %>
</div>