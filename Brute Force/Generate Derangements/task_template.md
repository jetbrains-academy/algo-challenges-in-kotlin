<%css "/util/common.css" %>

# Generate Derangements

Given an integer $n$, generate all permutations that have no fixed point.
A fixed point in permutation $p$ is such $i$ that $p_i = i$.

TODO think about defining what permutation is

### Input

Given an `Int` $n$ — the length of permutation ($0 \le n \le 10$).

### Output

Return a `List<Derangement>` that contains the required permutations.
The list should be lexicographically ordered.

TODO think about defining what lex ordered is

### Example


<div class="samples">

| Input | Returns                  |
|-------|--------------------------|
| `2`   | `[[1, 0]]`               |
| `3`   | `[[1, 2, 0], [2, 0, 1]]` |

</div>

<div class="hint">
<%include "solution.md" %>
</div>
