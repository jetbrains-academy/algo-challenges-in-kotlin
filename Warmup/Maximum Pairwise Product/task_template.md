<%css "/util/common.css" %>

# Maximum Pairwise Product
<div class="logo">
    <img src="../../images/max_pairwise_product_logo.png">
</div>

Given a sequence of non-negative integers
$a_1, \dots, a_{n}$,
compute $$\max\limits_{1 \le i \neq j \le n}a_i \cdot a_j.$$
Note that $i$ and $j$ should be different, though it may be the case
that $a_i=a_j$.

Constraints: $2 \le n \le 2\cdot10^5$; $0 \le a_1, \dots, a_{n} \le 2\cdot 10^5$.

TODO: Niyaz, please adjust the maximum value (so that a naive solution 
has two issues: a time limit and an an integer overflow)

### Example 1

<div class="sample">

| Input     | Returns |
|-----------|---------|
| `1, 2, 3` | `6`     |

</div>

### Example 2

<div class="sample">

| Input                            | Returns |
|----------------------------------|---------|
| `7, 5, 14, 2, 8, 8, 10, 1, 2, 3` | `140`   |

</div>

<div class="hint">
<%include "solution.md" %>
</div>