<%css "/util/common.css" %>

# Find two adjacent elements with opposite values 

You are given a sequence of bits ('0' and '1'). The sequence starts with '0' and ends with '1'.
Find any occurrence of '1' coming right after '0'. Return an index of '0' that has '1' right after it.

In [Task.kt](course://Divide and Conquer/Opposite Values/src/Task.kt) you already have slow solution implemented using standard library function.
Make it work faster to pass the tests.

<div class="samples">

| Input    | Return value |
|----------|--------------|
| `000101` | 2            |
| `000101` | 4            |
| `000111` | 2            |

</div>


<div class="hint">
Use binary search
</div>


<div class="hint">
<%include "solution.md" %>
</div>