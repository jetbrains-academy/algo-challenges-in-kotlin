<%css "/util/common.css" %>

# Count Ones
<div class="logo">
    <img src="../../images/count_ones_logo.png">
</div>

Implement an interface that allows one to get the number of
1's in the given binary string.

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
Implement a linear scan.
</div>

<div class="hint">
<iframe width="560" height="315" src="https://www.youtube.com/embed/a2s9QaKF5lU" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>

<div class="hint">
<%include "solution.md" %>
</div>