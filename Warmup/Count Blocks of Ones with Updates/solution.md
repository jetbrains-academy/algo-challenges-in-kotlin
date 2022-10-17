
### Solution

Recall that an index $0 \le i < n$
is a starting index of a block of ones
of a binary string $s[0\dotsc n)$ if and only if
$$s[i]=1 \text{ and } (i=0 \text{ or } s[i-1]=0).$$
This expression reveals that this "status" of the $i$-th index 
depends on $s[i-1]$
and $s[i]$ only. 
When the $i$-th bit is flipped, it affects the status
of the indices $i$ and $i+1$. Thus, to update the number of blocks of
ones after the flip of the $i$-th bit, it suffices to compute the status
of the indices $i$ and $i+1$ before and after the flip.