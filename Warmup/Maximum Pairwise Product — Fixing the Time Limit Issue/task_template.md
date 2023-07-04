Finally, assume that in the Maximum Pairwise Product problem,
the $a_i$'s integers can be as large as $10^6$,
and $n$, the length of the input sequence, can also 
be as large as $10^6$.
Again, our previous
solution does not work in this case. 
You may want to check that
by pressing the "Check" button.

**Stop and think.** Do you see why our previous solution fails
with the "Time limit exceeded" error message?

The reason is that our previous solution performs of the order of
$n^2$ steps on a sequence of length $n$.
For the maximal possible value $n=10^6$,
the number of steps is of the order $10^{12}$.
Since many modern computers perform roughly $10^8$–$10^9$
basic operations per second (depending on the machine, of course),
it may take tens of seconds to execute this solution,
exceeding the time limit.

We need a faster algorithm!

In search of a faster algorithm, you can play with small examples like
$[5,6,2,7,4]$. Eureka—it suffices to multiply the two largest elements
of the array—7 and 6!

Implement an algorithm that scans an input sequence (once or twice)
to find the two largest elements and returns their product.
