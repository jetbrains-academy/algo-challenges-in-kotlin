<%css "/util/common.css" %>

# Minimum Sum Path

You are playing a video game.
There is a line with $n$ points denoted as $0, 1, \ldots, n - 1$.
Your character starts at point $0$.
Each turn, if he is at point $i$, you can make him move either to point
$i + 1$ or to point $i + 2$. The character has low stamina, so he cannot make
moves from $i$ to $i + 2$ twice in a row.

The game ends on reaching the point $n - 1$.
Each point has a number associated with it: $a_0, a_1, \ldots, a_{n - 1}$.

Implement a function for computing the minimum total sum of the numbers 
in the visited points.

```Kotlin
fun minimumSumPath(a: IntArray): Long
```

### Input

You are given an `IntArray` $a$ of length $n$ ($2 \le n \le 10^6$).

Each $|a_i| \le 10^9$.

### Output

Return a `Long` representing the minimum total sum.

<div class="samples">

| Input                  | Return value |
|------------------------|--------------|
| `[3, 1, 3, 4, 2]`      | `9`          |
| `[1, 2, 3, -2, -2, 5]` | `4`          |

</div>

<div class="hint">
<%include "solution.md" %>
</div>
