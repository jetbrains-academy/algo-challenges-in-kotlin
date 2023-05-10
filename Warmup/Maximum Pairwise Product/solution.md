
### Solution

#### Naive Algorithm

A naive way to solve the Maximum Pairwise Product Problem is to go through 
all possible pairs of the input elements 
$A[1\dotsc n]=[a_1,\dotsc, a_n]$ and to find a pair of distinct elements 
with the largest product:

![](../../images/max_pairwise_product_1.png)

This code can be optimized and made more compact as follows.

![](../../images/max_pairwise_product_2.png)

In the file `task.kt`, you can find an implementation of this algorithm
in Kotlin. Submit it to the grading system by pressing the Check button
in the right pane.

TODO: Niyaz, please add a Kotlin implementation to task.kt

Surprisingly, this implementation fails:

`Failed case #4/17: time limit exceeded`

After you submit your program, we test it on dozens of carefully 
designed test cases to make sure the program is fast and error proof. 
As the result, we usually know what kind of errors you made. 
The message above tells that the submitted program exceeds the time 
limit on the 4th out of 17 test cases.

$MaxPairwiseProductNaive$ performs of the order of 
$n^2$ steps on a sequence of length $n\$. 
For the maximal possible value $n=2\cdot 10^5$, 
the number of steps is of the order $4\cdot 10^{10}$. 
Since many modern computers perform roughly $10^8$–$10^9$ 
basic operations per second (this depends on a machine, of course), 
it may take tens of seconds to execute ${MaxPairwiseProductNaive}$, 
exceeding the time limit for the Maximum Pairwise Product Problem.

We need a faster algorithm! 


#### Fast Algorithm

In search of a faster algorithm, you play with small examples like 
$[5,6,2,7,4]$. Eureka—it suffices to multiply the two largest elements 
of the array—7 and 6!

Since we need to find the largest and the second largest elements, 
we need only two scans of the sequence. During the first scan, we find 
the largest element. During the second scan, we find the largest element 
among the remaining ones by skipping the element found at the previous scan.

![](../../images/max_pairwise_product_3.png)

#### Testing and Debugging

Implement this algorithm and test it using an input $A=[1,2]$. 
It will output 2, as expected. Then, check the input $A=[2,1]$. 
Surprisingly, it outputs 4. By inspecting the code, you find out 
that after the first loop, $index_1=1$. The algorithm then 
initializes ${index}_2$ to $1$ and ${index}_2$ is never updated by 
the second for loop. As a result, 
${index}_1={index}_2$ before the return statement. 
To ensure that this does not happen, you modify the pseudocode as follows:

![](../../images/max_pairwise_product_4.png)

Check this code on a small dataset $[7,4,5,6]$ to ensure that it 
produces correct results. Then try an input $[100000, 90000]$.

TODO: Niyaz, please adjust the next few sentences accordingly

You may find out that the program outputs something like $410\,065\,408$ 
or even a negative number instead of the correct result $9\,000\,000\,000$. 
If it does, this is most probably caused by an *integer overflow*. 
In Kotlin programming language, a large number like $9\,000\,000\,000$
does not fit into the standard `int` type that on most modern machines occupies 
4 bytes and ranges from $-2^{31}$ to $2^{31}-1$, where $$2^{31}=2\,147\,483\,648.$$ 
Hence, instead of using the `int` type you need to use the `int64_t` type when computing the product 
and storing the result. This will prevent integer overflow as the `int64_t` type occupies 8 bytes and 
ranges from $-2^{63}$ to $2^{63}-1$, where $$2^{63}=9\,223\,372\,036\,854\,775\,808.$$

You then proceed to testing your program on large data sets, e.g., an array 
$A[1 \dotsc 2 \cdot 10^5]$, where $A[i]=i$ for all $1 \le i \le 2 \cdot 10^5$. 
This can be done as follows:

TODO: Niyaz, please provide code in Kotlin

Check that your program processes this dataset within time limit and returns the correct result: 
$39\,999\,800\,000$. You are now confident that the program finally works!

However, after submitting it to the testing system, it fails again...

`Failed case #5/17: wrong answer`


But how would you generate a test case that make your program fail and help you to figure out what went wrong?

#### Can You Tell Me What Error Have I Made?

You are probably wondering why we did not provide you with the 5th out of 17 test datasets that brought down 
your program. The reason is that nobody will provide you with the test cases in real life!

Since even experienced programmers often make subtle mistakes solving algorithmic problems, it is important to 
learn how to catch bugs as early as possible. When the authors of this book started to program, they 
naively thought that nearly all their programs are correct. By now, we know that our programs are 
*almost never* correct when we first run them.

When you are confident that your program works, you often test it on just a few test cases, 
and if the answers look reasonable, you consider your work done. However, this is a recipe for a disaster. 
To make your program *always* work, you should test it on a set of carefully designed test cases. 
Learning how to implement algorithms as well as test and debug your programs will be invaluable in your 
future work as a programmer.

When your program fails on the 5th out of 17 datasets, we typically do not reveal this dataset. 
However, we make an exception for all eight coding challenges in the "Algorithmic Warm Up"
section — when you go through this section, we provide you with one of the test cases that you failed to pass 
to make it easier to debug your code.

#### Stress Testing

We will now introduce *stress testing*—a technique for generating thousands of tests with the goal of 
finding a test case for which your solution fails.

A stress test consists of four parts:
1. Your implementation of an algorithm.
2. An alternative, trivial and slow, but correct implementation of an algorithm for the same problem.
3. A random test generator.
4. An infinite loop in which a new test is generated and fed into both implementations to compare the results. 
5. If their results differ, the test and both answers are output, and the program stops, otherwise the loop repeats.
\end{enumerate}

The idea behind stress testing is that two correct implementations
should give the same answer for each test (provided the answer to the problem is unique). 
If, however, one of the implementations is incorrect, then there exists a test on which their answers differ. 
The only case when it is not so is when there is the same mistake in both implementations, but that is unlikely 
(unless the mistake is somewhere in the input/output routines which are common to both solutions). 
Indeed, if one solution is correct and the other is wrong, then there exists a test case on which they differ. 
If both are wrong, but the bugs are different, then most likely there exists a test on which two solutions give 
different results.

Here is the stress test for $MaxPairwiseProductFast$
using $MaxPairwiseProductNaive$ as a trivial implementation:

![](../../images/max_pairwise_product_5.png)

The while loop above starts with generating the length of the input sequence $n$, 
a random number between $2$ and $N$. It is at least $2$, because the problem statement specifies that $n \ge 2$. 
The parameter $N$ should be small enough to allow us to explore many tests despite the fact that one 
of our solutions is slow.

After generating $n$, we generate an array $A$ with $n$ random numbers from $0$ to $M$ and output it so 
that in the process of the infinite loop we always know what is the current test; this will make it easier to 
catch an error in the test generation code. We then call two algorithms on $A$ and compare the results. 
If the results are different, we print them and halt. Otherwise, we continue the while loop. 

Let's run ${StressTest}(10, 100\,000)$ and keep our fingers crossed in a hope that it outputs "Wrong answer." 
We see something like this (the result can be different on your computer because of a 
different random number generator). 

```
...
OK
67232 68874 69499
OK
6132 56210 45236 95361 68380 16906 80495 95298
OK
62180 1856 89047 14251 8362 34171 93584 87362 83341 8784
OK
21468 16859 82178 70496 82939 44491
OK
68165 87637 74297 2904 32873 86010 87637 66131 82858 82935
Wrong answer: 7680243769 7537658370
```

Hurrah! We've found a test case where $MaxPairwiseProductNaive$ and 
$MaxPairwiseProductFast$ produce different results, so now we can check 
what went wrong. Then we can debug this solution on this test case, 
find a bug, fix it, and repeat the stress test again.

**Stop and Think!** Do you see anything suspicious in the found dataset?

Note that generating tests automatically and running stress test is easy, 
but debugging is hard. Before diving into debugging, let's try to 
generate a smaller test case to simplify it. To do that, we change 
$N$ from 10 to 5 and $M$ from $100\,000$ to $9$.

**Stop and Think!** Why did we first run $StressTest$ with large parameters 
$N$ and $M$ and now intend to run it with small $N$ and $M$?

We then run the stress test again and it produces the following.
```
...
7 3 6
OK
2 9 3 1 9
Wrong answer: 81 27
```

The slow $MaxPairwiseProductNaive$ gives the correct answer $81$ ($9 \cdot 9 = 81$), 
but the fast $MaxPairwiseProductFast$ gives an incorrect answer $27$.

**Stop and Think!** How $MaxPairwiseProductFast$ can possibly return $27$?

To debug our fast solution, let's check which two numbers it identifies as two largest ones. 
To do this, we add the following line before the return statement of the 
$MaxPairwiseProductFast$ function:

![](../../images/max_pairwise_product_6.png)

After running the stress test again, we see the following.
```
...
7 3 6
1 3
OK
5
2 9 3 1 9
2 3
Wrong answer: 81 27
```

Note that our solutions worked and then failed on exactly the same test cases as on the previous run of the stress test, 
because we didn't change anything in the test generator. The numbers it uses to generate tests
are pseudorandom rather than random—it means that the sequence looks random, but it is the same each time we run 
this program. It is a convenient and important property, and you should try to have your programs exhibit such 
behavior, because deterministic programs (that always give the same result for the same input) are easier to 
debug than non-deterministic ones.

Now let's examine ${index}_1=2$ and ${index}_2=3$. If we look at the code for determining the 
second maximum, we will notice a subtle bug. When we implemented a condition on $i$ (such that it is not the same as 
the previous maximum) instead of comparing $i$ and ${index}_1$, we compared $A[i]$ with $A[\myvar{index}_1]$. 
This ensures that the second maximum differs from the first maximum by the value rather than by the index of the 
element that we select for solving the Maximum Pairwise Product Problem. So, our solution fails on any test case 
where the largest number is equal to the second largest number. We now change the condition from
![](../../images/max_pairwise_product_7.png)
to
![](../../images/max_pairwise_product_8.png)
After running the stress test again, we see a barrage of ``OK'' messages on the screen. 
We wait for a minute until we get bored and then decide that $MaxPairwiseProductFast$ is finally correct!

However, you shouldn't stop here, since you have only generated very small tests with $N=5$ and $M=10$. 
We should check whether our program works for larger $n$ and larger elements of the array. 
So, we change $N$ to $1\,000$ (for larger $N$, the naive solution will be pretty slow, because its running 
time is quadratic). We also change $M$ to $200\,000$ and run. We again see the screen filling with words "OK", 
wait for a minute, and then decide that (finally!) $MaxPairwiseProductFast$ is correct. Afterward, we submit the 
resulting solution to the grading system and pass the Maximum Pairwise Product Problem test!

As you see, even for such a simple problem like Maximum Pairwise Product, it is easy to make subtle mistakes when 
designing and implementing an algorithm. The pseudocode below presents a more "reliable" way of implementing the 
algorithm.

![](../../images/max_pairwise_product_9.png)

