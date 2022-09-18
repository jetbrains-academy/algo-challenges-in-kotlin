<%css "/util/common.css" %>

# Median of Two Sorted Lists

<div class="logo">
    <img src="../../images/median_two_lists_logo.png">
</div>

### Input

You are given two sorted `List<T>`'s 
$a_0 \le a_1 \le \dotsb a_{n-1}$ 
and
$b_0 \le b_1 \le \dotsb b_{n-1}$, where `T`
implements `Comparable<T>`
and $0 \le n \le 10^7$.
The lists' `get` random-access method works in constant time.

TODO: Niyaz, perhaps n should be positive (rather than non-negative)?
(It is not clear what to return in case $n=0$.)

### Output

Let $c_0 \le c_1 \le \dotsb c_{2n-1}$ be a sorted union of $a$ and $b$
(since $a$ and $b$ may contain duplicates, there may exist many ways to
merge $a$ and $b$). Return $c_{n-1}$.

<div class="samples">

| Input                                      | Return value |
|--------------------------------------------|--------------|
| `[1, 2, 5]` <br/> `[3, 4, 6]`              | 3            |
| `["a", "z", "zz"]` <br/> `["a", "a", "zz]` | `"a"`        |

</div>

<div class="hint">
Consider two sorted lists
$a_0 \le a_1 \le a_2 \le a_3 \le a_4 \le a_5 \le a_6$ and
$b_0 \le b_1 \le b_2 \le b_3 \le b_4 \le b_5 \le b_6$
and let $c_0 \le c_1 \le \dotsb \le c_{13}$ be their sorted 
union. Our goal is to find $c_6$. Assume further that 
$a_3 \ge b_3$. Taking this into account, can you 
discard some of $a_i$'s and $b_i$'s from further consideration?
</div>

<div class="hint">
<%include "solution.md" %>
</div>
