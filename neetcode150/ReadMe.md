Prompt template: Add static inner classes + Javadoc links

Purpose

This prompt template helps you ask an assistant to create empty static inner classes inside a Java outer class file for
every LeetCode problem referenced in that file's header comments, and to paste each problem title + link as a Javadoc
anchor above the matching inner class.

How to use

- Replace the angled-bracket placeholders with your values.
- Paste the whole prompt to the assistant (or your automated agent).

Template

"""
I have a Java file at <ABSOLUTE_FILE_PATH> that contains a top-level class named <OUTER_CLASS_NAME> and a header comment
block listing several LeetCode problems (title + URL). For each LeetCode problem referenced in the header, please:

1. Create a public static inner class inside <OUTER_CLASS_NAME> whose name is the problem title converted to
   PascalCase (remove non-alphanumeric characters). If a name would conflict with the outer class name, append `Q` to
   the inner-class name.
2. Leave the class body empty (just a placeholder comment).
3. Copy the problem title and the URL and insert them as a Javadoc comment immediately above the inner class, using an
   HTML anchor tag: <a href="URL">LeetCode - Title</a>.
4. Don't change any other code or formatting unnecessarily.
5. After editing, run a quick lint/compile check and report any warnings/errors you see.

Example inputs:

- ABSOLUTE_FILE_PATH: /path/to/project/src/com/example/TopKFrequentElements.java
- OUTER_CLASS_NAME: TopKFrequentElements

Return: Edit the file in-place and tell me which classes you added and any notable warnings from the lint check.
"""

Example (filled for this repo)

"""
I have a Java file at
/Users/prakharsrivastava/IdeaProjects/MyCodeLib/neetcode150/arraysandhashing/TopKFrequentElements.java that contains a
top-level class named TopKFrequentElements and a header comment block listing several LeetCode problems (title + URL).
For each LeetCode problem referenced in the header, please:

1. Create a public static inner class inside TopKFrequentElements whose name is the problem title converted to
   PascalCase (remove non-alphanumeric characters). If a name would conflict with the outer class name, append `Q` to
   the inner-class name.
2. Leave the class body empty (just a placeholder comment).
3. Copy the problem title and the URL and insert them as a Javadoc comment immediately above the inner class, using an
   HTML anchor tag: <a href="URL">LeetCode - Title</a>.
4. Don't change any other code or formatting unnecessarily.
5. After editing, run a quick lint/compile check and report any warnings/errors you see.

Return: Edit the file in-place and tell me which classes you added and any notable warnings from the lint check.
"""

Tips & variations

- If you prefer the link as plain text (not HTML), replace the anchor instruction.
- To avoid the unchecked generic-array warning, the assistant can add a cast and @SuppressWarnings("unchecked") where
  arrays of generic types are created.
- To generate method stubs instead of empty classes, ask the assistant to include a public static `solve()` or `run()`
  method in each inner class.

That's it — use this prompt whenever you want to bulk-create placeholder inner classes from a header comment list of
problems.
