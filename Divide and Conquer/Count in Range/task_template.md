<%css "/util/common.css" %>

# Count number of elements in given range 

Given a *sorted* array $a$, and numbers $L$ and $R$, 
count number of elements that satisfy $L \le a_i \le R$.

In [Task.kt](course://Divide and Conquer/Count in Range/src/Task.kt), 
you already have a slow solution.
Make it work faster to pass the tests.

<div class="samples">

| Input                     | Return value |
|---------------------------|--------------|
| $(2, 3, 5, 8, 10)$, 3, 9  | 3            |
| $(2, 3, 5, 8, 10)$, 1, 20 | 5            |
| $(2, 3, 5, 8, 10)$, 6, 7  | 0            |

</div>

<div class="hint">
The elements we are looking for form a segment. If we find the
borders of this segment using binary search, we can find its length.
</div>

<div class="hint">
<%include "solution.md" %>
</div>
