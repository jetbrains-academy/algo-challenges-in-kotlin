<%css "/util/common.css" %>

# Primitive Calculator

Implement a function that finds the minimum number of operations needed
to get a positive integer $n$ from 1 by using only three operations:
- add 1,
- multiply by 2,
- multiply by 3.

```Kotlin
fun findMinimumOperations(n: Int): List<Int>
```

### Input

An `Int` $n$ ($1 \le n \le 10^6$), an integer to get from 1 by using the operations above.

### Output

Consider an optimal list of $k$ operations that transforms 1 into $n$.
The returned `List<Int>` should contain $k + 1$ integers that will appear while you perform these $k$ operations.
The integers should be listed in the order of their appearance, in other words, in increasing order.

<div class="samples">

| Input                                 | Return value                                                                  |
|---------------------------------------|-------------------------------------------------------------------------------|
| `1`                                   | `[1]`                                                                         |
| `96234`                               | `[1, 3, 9, 10, 11, 33, 99, 297, 891, 2673, 8019, 16038, 16039, 48117, 96234]` |

Note that in the second example, some other return lists may also be correct.

</div>


<div class="hint">
<%include "solution.md" %>
</div>
