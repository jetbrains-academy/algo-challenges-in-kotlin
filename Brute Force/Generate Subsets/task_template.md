<%css "/util/common.css" %>

# Generate Subsets

Given integers $n$ and $k$, generate all subsets of set $\{0, 1, 2, \ldots, n - 1\}$ 
with cardinality $k$.

### Input

Given `Int`'s $n$ and $k$ — the sizes of the set and the subsets, respectively ($0 \le k \le n \le 200$).

The number of required subsets doesn't exceed $10^6$.

### Output

Return a `List<BitSet>` that contains the required subsets.
The list should be lexicographically ordered.

### Example

<div class="samples">

| Input     | Returns                    |
|-----------|----------------------------|
| `2`, `1`  | `[[0], [1]]`               |
| `3`, `2`  | `[[0, 1], [0, 2], [1, 2]]` |

</div>

<div class="hint">
<%include "solution.md" %>
</div>
