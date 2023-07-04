Now, assume that in the Maximum Pairwise Product problem,
the integers $a_i$'s can be as large as $10^6$.
In such a case, perhaps surprisingly, our previous 
solution does not work. You may want to check that
by pressing the "Check" button.

**Stop and think.** Do you see why the solution fails?

The reason is that the `Int` type in Kotlin 
occupies 4 bytes and ranges from $2^{-31}$ to $2^{31}-1$,
where
$$2^{31}=2147483648 .$$
Thus, even though $a_i$'s fit into the `Int` type in this problem,
the product of two $a_i$'s can be too large: say, if $a_1=a_2=10^6$,
then 
$$a_1 \cdot a_2 = 10^{12}=1000000000000>2147483648.$$

To fix the solution, when computing the product of $a_i$ and 
$a_j$, store the result in the `Long` type: `a[i].toLong() * a[j]`.

An important takeaway: make sure to always test your code for 
various boundary conditions.
