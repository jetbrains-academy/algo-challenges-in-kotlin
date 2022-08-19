<%css "/util/common.css" %>

# Minimum Unchangeable Amount
<div class="logo">
    <img src="../../images/unchangeable_amount_logo.png">
</div>

Given $n$ coins of denominations $c_1 \le c_2 \le \dotsb \le c_n$,
find the minimum amount that cannot be changed using these coins.

<div class="samples">

| Input           | Return value |
|-----------------|--------------|
| `[1, 2, 4, 10]` | 8            |
| `[1, 1]`        | 3            |
| `[1, 2, 10]`    | 4            |

</div>

<div class="hint">
What can you say about $c_1$ if the smallest unchangeable amount is $1$?
</div>

<div class="hint">
What can you say about $c_1, c_2$ if the smallest unchangeable amount is $2$?
</div>

<div class="hint">
<%include "solution.md" %>
</div>