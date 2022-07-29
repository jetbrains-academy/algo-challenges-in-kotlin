<%css util/common.css %>

# Minimum Unchangeable Amount

Given $n$ coins of denominations $c_1, \dotsc, c_n$,
find the minimum amount that cannot be changed using these coins.

<div class="samples">

| Input           | Return value |
|-----------------|--------------|
| `[1, 2, 4, 10]` | 8            |
| `[1, 1]`        | 3            |
| `[1, 2, 10]`    | 4            |

</div>

<div class="hint">
What can you say about $c_1$ if the smallest unchangeable amount is $1$?
</div>

<div class="hint">
What can you say about $c_1, c_2$ if the smallest unchangeable amount is $2$?
</div>

<div class="hint">

### Solution

For a collection of coins $S$, by $\operatorname{changable}(S)$ denote the set of all amounts 
that can be changed using coins from $S$. Our goal then is to find the smallest positive integer
that does not belong to $\operatorname{changable}(\\{c_1, \dotsc, c_n\\})$.

As usual, it is instructive to play around with toy examples to see how 
the set $\operatorname{changable}(S)$ behaves when we add a new coin to $S$.
Let us focus on the first sample test ($c_1=1$, $c_2=2$, $c_3=4$, $c_4=10$) and
add coins one by one.

$$
\begin{align*}
\operatorname{changable}(\\{1\\})&=\\{1\\},\newline
\operatorname{changable}(\\{1, 2\\})&=\\{1, 2, 3\\},\newline
\operatorname{changable}(\\{1, 2, 4\\})&=\\{1, 2, 3, 4, 5, 6, 7\\},\newline
\operatorname{changable}(\\{1, 2, 4, 10\\})&=\\{1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 16, 17\\}.
\end{align*}
$$

The example reveals the following property: at least in the beginning, the set $\operatorname{changable}$
is equal to a continuous range of integers. At some point, a hole appears in this range and our 
goal is to find this first hole.

Assume that, for some $1 \le k \le n$, $\operatorname{changable}(\lbrace c_1, \dotsc, c_k\rbrace)=[m]$
(here, $[m]=\lbrace 1, \dotsc, m \rbrace$ is the set of the first $m$ positive integers). 
Now, how does adding $c_{k+1}$ change this set? Clearly, if $c_{k+1}>m+1$, then $m+1$ is the smallest 
unchangeable amount: using the first $k$ coins one cannot get any amount larger than $m$, all other coins
are larger than $m+1$. If, on the other hand, $c_{k+1} \le m+1$, then 
$\operatorname{changable}(\lbrace c_1, \dotsc, c_{k+1}\rbrace)=[m+c_{k+1}]$: to change any amount 
$m + 1 < t \le m + c_{k+1}$, take a coin $c_{k+1}$, then it remains to change $1 \le t-c_{k+1} \le m$
using coins $c_1, \dotsc, c_k$ 
(and this is doable since $\operatorname{changable}(\lbrace c_1, \dotsc, c_k\rbrace)=[m]$).

This suggests the following algorithm. If $c_1>1$, then return $1$. If $c_1=1$, then 
$\operatorname{changable}(\lbrace c_1 \rbrace)=[1]$. Start adding coins $c_2, \dotsc, c_n$
one by one. Then, you either find a hole or conclude that 
$\operatorname{changable}(\lbrace c_1, \dotsc, c_n\rbrace)=[p]$, in which case the answer is $p+1$.
</div>