
### Solution

The elements we are looking for form a segment $a[l..r-1]$. If we find the
indices $l$ and $r$, we can calculate the number of elements in the segment
as $r-l$.

Let's find the index $l$: $a[l]$ is the leftmost element that satisfy the
condition $a[l] \ge L$. We can use the function `lowerBound` from the 
previous task to find it in $O(\log n)$ time: `l = lowerBound(a, L)`.

Now let's find the index $r$: $a[r-1]$ is the rightmost element that satisfy the
condition $a[r-1] \le R$, that means that $a[r-1]$ is the leftmost element
that is $a[r] > R$. We can use the same function `lowerBound` to find it 
in $O(\log n)$ time. The easiest way is to see that since the element are integers,
$a[r] > R$ is the same as $a[r] \ge R+1$, so we can make `r = lowerBound(a, R + 1)`.






