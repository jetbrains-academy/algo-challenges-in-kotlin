<%css "/util/common.css" %>

# Edit Distance

The Edit Distance Problem has many applications in computational
biology, natural language processing, spell checking, and many other areas.
For example, biologists often compute edit distances when they search for
disease-causing mutations.

The edit distance between two strings is defined as the minimum number
of single-symbol insertions, deletions, and substitutions to transform one
string into the other one.

**_TODO: this is just taken from the book_**

### Examples

<div class="samples">

| Input                  | Returns |
|------------------------|---------|
| `"short" "ports"`      | 3       |
| `"editing" "distance"` | 5       |
| `"ab" "ab" `           | 0       |

</div>
