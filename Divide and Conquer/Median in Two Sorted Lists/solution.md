
### Solution

Intuitively, comparing the largest elements of two prefixes of total length $n+1$
allows one to find the largest element among the union of these prefixes
(that can be put into the right half of their sorted union).

Since $a_2 \le b_5$, we conclude that
\[a_0 \le a_1 \le a_2 \le b_5 \le b_6 \le b_7 \, .\].
There are only six elements that can be smaller than $a_1$: $a_0, b_0, b_1, b_2, b_3, b_4$.
Hence, $a_1$ can be among the first seven elements of $c$. Thus, we can discard
$a_0$ and $a_1$. Similarly, there are only six elements that can be larger than $b_6$:
$b_7, a_3, a_4, a_5, a_6, a_7$. Hence, $b_6$ can be among the last seven elements of $c$.
Thus, we can discard $b_6$ and $b_7$.

This way, we reduce the problem of finding the median of two sorted lists of length eight
to the problem of finding the median of two sorted lists of length six.

Generalizing this toy example, consider two sorted lists $a_0 \le \dotsb \le a_{n-1}$ and $b_0 \le \dotsb \le b_{n-1}$ of length $n$. Our goal is to find the median of their sorted union
$c_0 \le \dotsb \le c_{2n-1}$, that is, the element $c_{n-1}$. In $c$, there are $n-1$ elements
before $c_{n-1}$ and $n$ elements after $c_{n-1}$.
For a parameter $m$ to be chosen later, consider the following two prefixes of total length $n+1$: $a_0 \le \dotsb \le a_m$ and $b_0 \le  \dotsb \le b_{n-m-1}$.
Compare the elements $a_m$ and $b_{n-m+1}$.
If $a_m \le b_{n-m-1}$,
one can discard the first $m$ elements of $a$ ($a_0, \dotsc, a_{m-1}$)
and the last $m$ element of $b$ ($b_{n-m}, \dotsc, b_{n-1}$). Indeed,
the only elements that can be smaller than $a_{m-1}$
are $a_0, \dotsc, a_{m-2}$ and $b_0, \dotsc, b_{n-m}$ ($n-2$ elements),
whereas the only elements that can be larger than $b_{n-m}$
are $b_{n-m+1}, \dotsc, b_{n-1}$ and $a_{m+1}, \dotsc, a_{n-1}$ ($n-2$ elements).
This allows one to reduce the problem to finding the median of two sorted lists of length $n-m$.
The case $a_m > b_{n-m-1}$ is treated similarly: one reduces the problem to two sorted
lists of length $m+1$.

To make sure that the problem size reduces significantly in both cases, one takes $m=\lfloor \frac{n-1}{2} \rfloor$. This way, one halves the problem size at every iteration. Hence, the number
of recursive calls is $O(\log n)$. The base case of recursion is $n=1$: one then returns the minimum of the two elements. 


