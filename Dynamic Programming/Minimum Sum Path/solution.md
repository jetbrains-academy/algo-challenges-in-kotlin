
# Solution

The character can reach point $i$ either from point $i-1$ or
from point $i-2$ (if he reached it from point $i-3$). This
motivates introducing the following functions. For $0 \le i <n$
and $1 \le d \le 2$, 
$\operatorname{path}(i, d)$
is equal to the minimum sum of a path that ends at point $i$,
with the last move being made from point $i-d$. Then,

$$\operatorname{path}(i, 1)=a_i+\min\lbrace \operatorname{path}(i-1, 1), \operatorname{path}(i-1, 2)\rbrace$$

$$\operatorname{path}(i, 2)=a_i+\operatorname{path}(i-2, 1)$$

Using this recurrence relation and an appropriate base case,
one can compute the value of $\operatorname{path}(i, d)$
for all $i$ and $d$ in time $O(n)$. Then, the final answer
is $\min \lbrace \operatorname{path}(n-1, 1), \operatorname{path}(n-1, 2) \rbrace$.
