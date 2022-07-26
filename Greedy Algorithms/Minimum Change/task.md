# Find Minimum Change Impossible to Make

You have several coins, given their values.

Given $v_1, v_2, \ldots, v_n$ &mdash; coin values.

Implement a function `findMinimumChange` that takes an array $v$, and returns the minimum positive integer $c$ &mdash; the change value
you can't make with the coins you have.

<div class="hint">
What is the answer, when the minimum coin value is greater than $1$ ($\min v_i > 1$)?
</div>


<style>
.samples th, .samples td {
  border: 1px solid black;
  border-collapse: collapse;
  padding: 15px;
  width: 300px;
  /*max-width: 100%;*/
  /*text-align: center;*/
  /*alignment: center;*/
};
</style>

<div class="samples">

| Input             | Return value |
|-------------------|--------------|
| `[1, 2, 3, 4, 5]` | 16           |
| `[1, 1]`          | 3            |
| `[100, 1, 2]`     | 4            |

</div>
