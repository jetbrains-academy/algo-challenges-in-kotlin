Now, assume that in the Maximum Pairwise Product problem
the integers $a_i$'s can be as large as $10^6$.
In this case, perhaps surprisingly, our previous 
solution does not work. You may want to check this
by pressing the "Check" button.

**Stop and think.** Do you see why the solution fails?

The reason is that the `int` type in Kotlin 
occupies 4 bytes and ranges from $2^{-31}$ to $2^{31}-1$,
where
$$2^{31}=2147483648 .$$
Thus, even though $a_i$'s fit into the `int` type in this problem,
the product of two $a_i$'s can be too large: say, if $a_1=a_2=10^6$,
then $a_1 \cdot a_2 = 10^{12}$.

To fix the solution, ...

An important takeaway: make sure to always test your code for 
various boundary conditions.