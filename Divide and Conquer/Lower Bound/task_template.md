## Lower Bound

Now we will adopt the *binary search* method for slightly
different problem. In this problem we once again have the
*sorted* array, and we are looking for element $x$.
But now if there is no element equal to $x$, we want to
find the number close to $x$. Formally, we want to
find the minimum $i$ such that $a_i \ge x$.

For example, if we have array $a = (2, 4, 7, 11, 12)$, and
we are looking for element $x=9$, we want to find element
$a_3 = 11$.

What should we return if there is no element $a_i \ge x$?
Let's assume there is a virtual element after the last element
of the array, and this element is greater than any possible $x$.
Then, if there is no element $a_i \ge x$ in the array, this
virtual element will be the one we will find.

Another way to think about this problem is the following.
Imagine you have a sorted list, and you want to insert one
new element $x$ in it. Then our function will return the
position the element $x$ should be in.

The idea of the solution is again to try to half the search space.
But this time we will do it little differently.

Let's have two pointers $lower$ and $upper$, and maintain the
following invariants: $a_{lower} < x$, $a_{upper} \ge x$.

Initially we put these pointers beyond the borders of the
array: $lower = -1$, $upper = a.size$. Again, imagine there
are two virtual elements, one very small in the left, and
one very large in the right.

Now in every step we will check the element in the $middle$
position between $lower$ and $upper$. If $a_{middle} < x$,
we can move $lower$ pointer to this position, else we
can move $upper$ pointer to this position. Every time
the distance between $lower$ and $upper$ will decrease
twice.

We end when these pointers meet, i.e. when $upper = lower + 1$.
When it happens, we have the element we are looking for
in position $upper$.
