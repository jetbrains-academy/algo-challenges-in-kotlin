<%css "/util/common.css" %>

# Cound Inversions

<div class="logo">
    <img src="../../images/inversions_logo.png">
</div>

Given a sequence $a_0, a_1, \dotsc a_{n - 1}$, 
find for each $i$ the number of inversions $a_i$
participates in, that is, the number of $0 \le j < i$
such that $a_j > a_i$.

The number of inversions in a sequence measures how close the 
sequence is to being sorted. For example, a sequence sorted in 
the non-descending order contains no inversions, while a 
sequence sorted in the descending order contains $n(n-1)/2$ inversions 
(every two elements form an inversion).

### Input

The given sequence is `List<T>`, where `T` implements `Comparable<T>`.

The `List<T>`'s random-access `get` method works in $O(1)$ time.

The sequence contains at most $10^6$ elements.

### Output

Return an `IntArray` whose $i$-th element is the number of inversions of $a_i$.

<div class="samples">

| Input             | Return value      |
|-------------------|-------------------|
| `[3, 1, 3, 4, 2]` | `[0, 1, 0, 0, 3]` |

</div>

<div class="hint">
A naive algorithm for the Number of Inversions Problem 
goes through all possible pairs $(i,j)$ and has running time $O(n^2)$. 
To solve this problem in time $O(n\log n)$ using the divide-and-conquer technique, split the input array
into two halves and make a recursive call on both halves. What remains to be done is computing the 
number of inversions formed by two elements from different halves. If we do this naively, 
it will bring us back to $O(n^2)$ running time, since the total number of such pairs is $\frac{n}{2} \cdot \frac{n}{2}=\frac{n^2}{4}=O(n^2)$. It turns out that one can compute the number of inversions formed by two elements from different halves in time $O(n)$ if both halves are already sorted. This suggests that instead of solving the original problem, we solve a more general problem: compute the number of inversions in the given array and sort it at the same time.
</div>

<div class="hint">
<%include "solution.md" %>
</div>
