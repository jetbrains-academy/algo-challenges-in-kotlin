# algo-challenges-in-kotlin

## Task description templates
By default, we added `task.md` to `.gitignore`.
You can add `task_template.md` file and write your description there.

It supports several substitution patterns that we use, see them in the table below.
To generate `task.md` trigger Gradle reload event. If it ran successfully but the file wasn't created, try `File -> Reload All from Disk`.

| Command                       | Action                                | Substitutes to                                                              |
|-------------------------------|---------------------------------------|-----------------------------------------------------------------------------|
| `<%css "/util/common.css" %>` | Inlines provided script file contents | `<script>`<br/> _%project_root%/util/common.css contents_ <br/> `</script>` |
| `<%include "solution.md" %>`  | Includes provided file contents       | _%current_dir%/solution.md contents_                                        |

The paths can be relative to the current file location, or absolute starting from the project root directory.
