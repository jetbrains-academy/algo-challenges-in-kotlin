<%css "/util/common.css" %>

# Count Ones with Updates
<div class="logo">
    <img src="../../images/count_ones_with_updates_logo.png">
</div>

Implement a class that supports the following two operations
for a given binary string: get the number of 1's in the string
and flip a given bit of the string.

```Kotlin
class CountOnesWithUpdates {
    fun countOnes(): Int
    fun flip(index: Int)
}
```

### Example 1

<div class="sample">

| Input                            | Returns                                                             |
|----------------------------------|---------------------------------------------------------------------|
| `CountOnesWithUpdates("001001")` | [CountOnesWithUpdates](psi_element://CountOnesWithUpdates) instance |
| `countOnes()`                    | 2                                                                   |
| `flip(0)`                        |                                                                     |
| `countOnes()`                    | 3                                                                   |
| `flip(1)`                        |                                                                     |
| `countOnes()`                    | 4                                                                   |
| `flip(2)`                        |                                                                     |
| `countOnes()`                    | 3                                                                   |

</div>

<div class="hint">
How could you avoid 
counting the number of ones from scratch 
each time <code>countOnes</code> is called?
</div>

<div class="hint">
<%include "solution.md" %>
</div>