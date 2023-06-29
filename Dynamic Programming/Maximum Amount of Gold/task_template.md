<%css "/util/common.css" %>

# Maximum Amount of Gold

Given a set of gold bars of various weights and a backpack that can hold at most $\mathit{capacity}$ pounds, place
as much gold as possible into the backpack.

```Kotlin
fun findMaximumAmountOfGold(capacity: Int, w: IntArray): Int
```

### Input

An `Int` $\mathit{capacity}$ — the backpack capacity,
and an `IntArray` $w$ — a set of $n$ gold bars of integer weights $w_1, \ldots, w_n$.

### Output

An `Int` — the maximum total weight of gold bars that fit into a backpack of capacity $W$.


<div class="samples">

| Input             | Return value |
|-------------------|--------------|
| `10` `[1, 4, 8]`  | `9`          |

</div>

<div class="hint">
<%include "solution.md" %>
</div>
