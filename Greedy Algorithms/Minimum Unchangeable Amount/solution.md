
### Solution

For a collection of coins $S$, $\operatorname{changeable}(S)$ denotes the set of all amounts
that can be changed using the coins from $S$. Our goal then is to find the smallest positive integer
that does not belong to $\operatorname{changeable}(c_1, \dotsc, c_n)$.

As usual, it is instructive to play around with toy examples to see how
the set $\operatorname{changeable}(S)$ behaves when we add a new coin to $S$.
Let us focus on the first sample test ($c_1=1$, $c_2=2$, $c_3=4$, $c_4=10$) and
add coins one by one.

$$
\begin{align*}
\operatorname{changeable}(1)&=\\{1\\},\newline
\operatorname{changeable}(1, 2)&=\\{1, 2, 3\\},\newline
\operatorname{changeable}(1, 2, 4)&=\\{1, 2, 3, 4, 5, 6, 7\\},\newline
\operatorname{changeable}(1, 2, 4, 10)&=\\{1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 16, 17\\}.
\end{align*}
$$

The example reveals the following property: at least in the beginning, the set $\operatorname{changeable}$
is equal to a continuous range of integers. At some point, a hole appears in this range, and our
goal is to find this first hole.

Assume that for some $1 \le k \le n$, 
$\operatorname{changeable}(c_1, \dotsc, c_k)=[m]$
(here, $[m]=\lbrace 1, \dotsc, m \rbrace$ is the set of the first $m$ positive integers).
Now, how does adding $c_{k+1}$ change this set? Clearly, if $c_{k+1}>m+1$, then $m+1$ is the smallest
unchangeable amount: using the first $k$ coins, one cannot get any amount larger than $m$, all other coins
are larger than $m+1$. If, on the other hand, $c_{k+1} \le m+1$, then
$\operatorname{changeable}(c_1, \dotsc, c_{k+1})=[m+c_{k+1}]$: to change any amount
$m + 1 \le t \le m + c_{k+1}$, take a coin $c_{k+1}$, then it remains to change $1 \le t-c_{k+1} \le m$
using coins $c_1, \dotsc, c_k$
(and this is doable, since $\operatorname{changeable}(c_1, \dotsc, c_k)=[m]$).

This suggests the following algorithm.
Start with $k=0$ and $m=0$ and add coins
$c_1, \dotsc, c_n$ one by one.
Then, you either find a hole or conclude that
$\operatorname{changeable}(c_1, \dotsc, c_n)=[p]$, inwhich case the answer
is $p+1$.
