<%css "/util/common.css" %>

# Range Sum Queries
<div class="logo">
    <img src="../../images/range_sum_queries.png">
</div>

Implement an interface that allows one to compute
the sum of any subrange of a given integer sequence.

For an integer sequence $a_0, \dotsc, a_{n-1}$ and indices 
$0 \le l \le r \le n$, by $\operatorname{query}(l,r)$
denote the sum $\sum_{l \le i <r}a_i=a_l+a_{l+1}+\dotsb+a_{r-1}$.

```Kotlin
<%include "src/RangeSumQueries.kt" %>
```

As a range may be as long as $n$, answering a single range sum query takes 
time $O(n)$. Hence, the total running time for answering $q$ range 
sum queries
is $O(qn)$.
Your goal is to design a faster algorithm.

### Input

An `IntArray` of size not exceeding $10^7$.

### Output

- `createRSQInstance` function should return
an instance of class implementing [RangeSumQueries](psi_element://RangeSumQueries) interface.
- `getSum(l, r)` should return the value of $\operatorname{query}(l, r)$

TODO: Niyaz, add sample tests

<div class="hint">
Assume that you know the value of $\operatorname{range}(0,k)$ 
for all $0 \le k \le n$.
Using these values, can you compute the value of 
$\operatorname{range}(l,r)$ in time $O(1)$, for any $0 \le l \le r \le n$?
</div>

<div class="hint">
Computing $\operatorname{range}(0,k)$ for all $0 \le k \le n$ naively 
takes time $0+1+2+\dotsb+n=O(n^2)$. Can you do it faster?
</div>

<div class="hint">
<%include "solution.md" %>
</div>

