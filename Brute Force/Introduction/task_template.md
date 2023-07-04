In this module, we'll practice implementing brute-force solutions. To find an object satisfying a particular property, a brute-force algorithm simply enumerates all possible objects. In many cases, such an algorithm is too slow. Still, there are situations when a brute-force solution is needed.

* Say, you need to find a permutation of five objects with some property. Since the input size in this case is small, it is perfectly fine to enumerate all permutations.

* When implementing an efficient algorithm, it is desirable to have a baseline algorithm that is correct even if slow. Such an algorithm can be used to test your efficient solution: to do that, you generate tests of small size and compare the results of two solutions. A brute-force algorithm is a perfect candidate for such a baseline solution.

* There are computational problems for which a brute-force algorithm is the best solution we have.

* Finally, dynamic programming, an extremely popular algorithmic technique, which we will cover later in this course, can be viewed as an optimized brute-force method.

