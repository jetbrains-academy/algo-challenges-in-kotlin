
### Solution

Start by computing all prefix sums, i.e., 
compute $\operatorname{range}(0,k)=a_0+\dotsb+a_{k-1}$ for all $0 \le k \le n$.
This can be done in time $O(n)$, since the value of $\operatorname{range}(0,k+1)$
can be easily found from $\operatorname{range}(0,k)$: 
$$\operatorname{range}(0,k+1)=\operatorname{range}(0,k)+a_k.$$

Using these values, one compute the value of 
$\operatorname{range}(l,r)$ in time $O(1)$, for any $0 \le l \le r \le n$.
Indeed, combining the prefix range $(0,l)$ with the range $(l,r)$
gives the prefix range $(0,r)$. Thus,
$$\operatorname{range}(l,r)=\operatorname{range}(0,r)-\operatorname{range}(0,l).$$

Overall, the running time of the resulting algorithm is $O(n+q)$: 
compute (and store) all prefix sum queries, 
then answer each of $q$ range sum queries in time $O(1)$.  