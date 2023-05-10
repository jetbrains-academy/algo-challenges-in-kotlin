
### Solution

Given a string, count the number of ones in it and store it
in a variable, say, `counter`. Then, each time `flip` is called,
adjust `counter` accordingly. In turn, when `countOnes` is called,
instead of counting the number of ones from scratch, just return the
value of `counter`.
