<%css "/util/common.css" %>

# Count Inversions

Given a sequence $a_0, a_1, \ldots a_{n - 1}$. 

For each $i$ ($0 \le i < n$) find an integer $r_i$ — the number of $j$ ($0 \le j < i$) such that
$a_i < a_j$.

### Input

The given sequence is `List<T>` where `T` implements `Comparable<T>`.

The `List<T>`'s `get` random-access method works in $O(1)$ time.

The sequence contains at most $10^6$ elements.

### Output

Return an `IntArray` — $r_0, r_1, \ldots r_{n-1}$.

<div class="samples">

TODO add more samples

| Input             | Return value      |
|-------------------|-------------------|
| `[3, 1, 3, 4, 2]` | `[0, 1, 0, 0, 3]` |

</div>

<div class="hint">
<%include "solution.md" %>
</div>
