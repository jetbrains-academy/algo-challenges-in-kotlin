# Find two adjacent elements with opposite values 

You are given a sequence of bits ('0' and '1'). The sequence starts with '0' and ends with '1'.
Find any occurrence of '1' coming right after '0'. Return an index of '0' that has '1' right after it.

In [Task.kt](course://lesson1/task1/src/Task.kt) you already have slow solution implemented using standard library function.
Make it work faster to pass the tests.

<div class="hint">
Use binary search
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

| Input    | Return value |
|----------|--------------|
| `000101` | 2            |
| `000101` | 4            |
| `000111` | 3            |

</div>