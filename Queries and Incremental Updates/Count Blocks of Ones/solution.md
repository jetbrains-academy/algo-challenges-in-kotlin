
### Solution

An index $0 \le i < n$
is a starting index of a block of ones
of a binary string $s[0\dotsc n)$ if and only if
$$s[i]=1 \text{ and } (i=0 \text{ or } s[i-1]=0).$$
Thus, it suffices to count the number of $0 \le i < n$
satisfying the condition above.