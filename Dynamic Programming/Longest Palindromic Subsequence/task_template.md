<%css "/util/common.css" %>

# Longest Palindromic Subsequence

Given a string, find a longest palindromic subsequence of this string, 
i.e., a subsequence that reads the same backward and forward.

[//]: <> (TODO write what subsequence is)

### Input

A string consisting of lowercase English letters.
The length of the string doesn't exceed $5\ 000$. 

### Output

A string of maximum length that is a subsequence of the given string
and that is a palindrome at the same time.

If multiple answers exist, return any.

### Examples

<div class="samples">

| Input          | Returns     |
|----------------|-------------|
| `"bmczhadaem"` | `"madam"`   |
| `"abacaba"`    | `"abacaba"` |
| `"kotlin"`     | `"k"`       |

</div>

<div class="hint">
<%include "solution.md" %>
</div>
