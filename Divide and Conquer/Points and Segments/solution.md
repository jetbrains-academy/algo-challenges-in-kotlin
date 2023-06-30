
# Solution

Let $\operatorname{before}(p)$ be the number of segments that end 
before a point $p$, $\operatorname{after}(p)$ be the number of 
segments that start after $p$, and $\operatorname{cover}(p)$ be 
the number of segments covering $p$.

**Exercise break.** Prove that for each point $p$, 
$$\operatorname{before}(p)+\operatorname{after}(p)+\operatorname{cover}(p)$$ 
is equal to the total number of segments.  

Hence, to count the number of segments that do not cover the 
given point $p$, it is sufficient to count the number of right-ends of 
segments that are smaller than $p$ and the number of left-ends of 
segments that are greater than $p$. If all left-ends and right-ends 
are sorted, one can use the binary search algorithm to perform such a 
check in $O(\log m)$ time. The corresponding solution has running 
time $O(m\log m + n\log m)$.