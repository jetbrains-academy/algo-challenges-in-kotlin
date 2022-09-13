<%css "/util/common.css" %>

# Median in Two Sorted Lists

<div class="logo">
    <img src="../../images/opposite_values_logo.png">
</div>

### Input

You are given two `List<T>`'s $\mathit{first}$ and $\mathit{second}$ where `T`
implements `Comparable<T>`. Both lists are sorted. 

The lists' `get` random-access method works in $O(1)$ time.

The lists contain $n$ elements each ($0 \le n \le 10^7$).

### Output

Consider $r_0, r_1, \ldots, r_{2n - 1}$ is the sorted list of size $2n$ 
that was produced by merging the given lists.

You are required to return any element that could result in $r_{n - 1}$.  


<div class="samples">

| Input                                      | Return value |
|--------------------------------------------|--------------|
| `[1, 2, 5]` <br/> `[3, 4, 6]`              | 3            |
| `["a", "z", "zz"]` <br/> `["a", "a", "zz]` | `"a"`        |

</div>

<div class="hint">
<%include "solution.md" %>
</div>
