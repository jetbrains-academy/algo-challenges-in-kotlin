[![official project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# algo-challenges-in-kotlin

Prepare for your next technical interview by practicing solving algorithmic
challenges in IDE using Kotlin.

## Task description templates
By default, we added `task.md` to `.gitignore`.
You can add the `task_template.md` file and write your description there.

It supports several substitution patterns that we use, see them in the table below.
To generate `task.md`, trigger the Gradle reload event. If it ran successfully but the file wasn't created, try `File -> Reload All from Disk`.

| Command                       | Action                                | Substitutes to                                                              |
|-------------------------------|---------------------------------------|-----------------------------------------------------------------------------|
| `<%css "/util/common.css" %>` | Inlines provided script file contents | `<script>`<br/> _%project_root%/util/common.css contents_ <br/> `</script>` |
| `<%include "solution.md" %>`  | Includes provided file contents       | _%current_dir%/solution.md contents_                                        |

The paths can be relative to the current file location or absolute, starting from the project root directory.
