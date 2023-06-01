
### Solution

Start by computing all prefix sums, i.e., 
compute $\operatorname{range}(0,k)=a_0+\dotsb+a_{k-1}$ for all $0 \le k \le n$.
This can be done in linear time (more precisely, in $n$
arithmetic operations), since the value of 
$\operatorname{range}(0,k+1)$
can be easily found from $\operatorname{range}(0,k)$: 
$$\operatorname{range}(0,k+1)=\operatorname{range}(0,k)+a_k.$$

Using these values, one can compute the value of 
$\operatorname{range}(l,r)$ in constant time, for any $0 \le l \le r \le n$.
Indeed, combining the prefix range $(0,l)$ with the range $(l,r)$
gives the prefix range $(0,r)$. Thus,
$$\operatorname{range}(l,r)=\operatorname{range}(0,r)-\operatorname{range}(0,l).$$

Overall, the resulting algorithm 
makes $n+q$ arithmetic operations:
compute (and store) all prefix sum queries, 
then answer each of $q$ range sum queries with a single arithmetic 
operation.  
