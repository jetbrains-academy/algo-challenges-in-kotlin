<%css "/util/common.css" %>

# Collecting Signatures

You are responsible for collecting signatures from all tenants in 
a building. For each tenant, you know a period of time when he or she 
is at home. You would like to collect all signatures by visiting the 
building as few times as possible. For simplicity, we assume that when 
you enter the building, you instantly collect the signatures of all 
tenants that are in the building at that time.

Try our an <a href="https://discrete-math-puzzles.github.io/puzzles/touch-all-segments/index.html">interactive puzzle</a> 
before solving this programming challenge!

<div class="logo">
    <img src="../../images/collecting_signatures_logo.png">
</div>

Find the minimum number of points needed to cover all given segments 
on a line.

TODO: adjust input-output format

### Input

The first line of the input contains the number $n$ of segments. 
Each of the following $n$ lines contains two integers $l_i$ and $r_i$ 
(separated by a space) defining the coordinates of endpoints of 
the $i$-th segment.

### Output

The minimum number $k$ of points on the first line and the 
integer coordinates of $k$ points (separated by spaces) on 
the second line. You can output the points in any order. 
If there are multiple such sets of points, you can output any of them.


### Constraints

$1 \le n \le 100$; $0 \le l_i  \le r_i  \le 10^9$ for all $i$.


<div class="samples">

Sample 1:
Input:
3
1 3
2 5
3 6
Output:
1
3

Sample 2:
Input:
4 
4 7 
1 3 
2 5 
5 6
Output:
2 
3 6


</div>

<div class="hint">
<%include "solution.md" %>
</div>



