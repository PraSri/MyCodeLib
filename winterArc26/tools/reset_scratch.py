#!/usr/bin/env python3
"""
Regenerate blank Scratch.java skeletons from Solution.java.

Scratch.java is git-ignored. It keeps only the package, the imports and the
*public* signatures of Solution.java (including public nested design classes
such as LRUCache), with every body replaced by a TODO. Private helpers,
fields and comments are dropped so nothing leaks the answer.

Usage (run from anywhere):
  python3 winterArc26/tools/reset_scratch.py                 # create missing Scratch.java only
  python3 winterArc26/tools/reset_scratch.py sliding_window/minimum_window_substring
                                                              # wipe & regenerate one problem
  python3 winterArc26/tools/reset_scratch.py sliding_window   # wipe & regenerate a whole pattern
  python3 winterArc26/tools/reset_scratch.py --all            # wipe & regenerate everything
"""
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
INDENT = "    "
TODO_BODY = [
    "// Write it cold. No peeking at Solution.java.",
    'throw new UnsupportedOperationException("TODO");',
]


def strip_comments(src: str) -> str:
    out, i, n = [], 0, len(src)
    while i < n:
        c = src[i]
        if c in "\"'":
            j = i + 1
            while j < n and src[j] != c:
                j += 2 if src[j] == "\\" else 1
            out.append(src[i:j + 1])
            i = j + 1
        elif src.startswith("//", i):
            while i < n and src[i] != "\n":
                i += 1
        elif src.startswith("/*", i):
            end = src.find("*/", i + 2)
            i = n if end == -1 else end + 2
        else:
            out.append(c)
            i += 1
    return "".join(out)


def skip_literal(s: str, i: int) -> int:
    q, j = s[i], i + 1
    while j < len(s) and s[j] != q:
        j += 2 if s[j] == "\\" else 1
    return j + 1


def match_brace(s: str, i: int) -> int:
    """s[i] == '{'; return index of the matching '}'."""
    depth = 0
    while i < len(s):
        c = s[i]
        if c in "\"'":
            i = skip_literal(s, i)
            continue
        if c == "{":
            depth += 1
        elif c == "}":
            depth -= 1
            if depth == 0:
                return i
        i += 1
    raise ValueError("unbalanced braces")


def has_top_level_assign(header: str) -> bool:
    depth = 0
    for k, c in enumerate(header):
        if c == "(":
            depth += 1
        elif c == ")":
            depth -= 1
        elif c == "=" and depth == 0 and header[k + 1:k + 2] != "=" and header[k - 1:k] not in "!<>=":
            return True
    return False


def block_members(body: str):
    """Yield (header, inner_body) for every block-bodied member at depth 0."""
    i, start, n = 0, 0, len(body)
    while i < n:
        c = body[i]
        if c in "\"'":
            i = skip_literal(body, i)
        elif c == ";":
            start = i = i + 1
        elif c == "{":
            header = body[start:i]
            j = match_brace(body, i)
            if has_top_level_assign(header):  # field initializer with braces
                i = j + 1
                continue
            yield header, body[i + 1:j]
            start = i = j + 1
        else:
            i += 1


def clean_header(header: str) -> str:
    header = re.sub(r"@\w+(\([^)]*\))?", "", header)
    return " ".join(header.split())


def render(body: str, depth: int) -> list:
    pad = INDENT * depth
    lines = []
    for header, inner in block_members(body):
        h = clean_header(header)
        if not re.search(r"\bpublic\b", h):
            continue
        if re.search(r"\b(class|interface|enum)\b", h):
            lines += ["", f"{pad}{h} {{"] + render(inner, depth + 1) + [f"{pad}}}"]
        elif "(" in h:
            lines += ["", f"{pad}{h} {{"] + [pad + INDENT + t for t in TODO_BODY] + [f"{pad}}}"]
    return lines


def skeleton(solution_src: str) -> str:
    src = strip_comments(solution_src)
    pkg = re.search(r"^\s*package\s+[\w.]+\s*;", src, re.M)
    imports = re.findall(r"^\s*import\s+[\w.*\s]+;", src, re.M)
    m = re.search(r"public\s+(final\s+)?class\s+Solution\b[^{]*\{", src)
    if not m:
        raise ValueError("no `public class Solution` found")
    open_idx = m.end() - 1
    body = src[open_idx + 1:match_brace(src, open_idx)]
    out = []
    if pkg:
        out += [pkg.group().strip(), ""]
    if imports:
        out += [i.strip() for i in imports] + [""]
    out.append("public class Scratch {")
    out += render(body, 1)
    out.append("}")
    return "\n".join(out) + "\n"


def problem_dirs(targets):
    for t in targets:
        p = (ROOT / t).resolve()
        if (p / "Solution.java").exists():
            yield p
        else:
            yield from sorted(s.parent for s in p.rglob("Solution.java"))


def main(argv):
    force = bool(argv)
    targets = ["."] if (not argv or argv == ["--all"]) else argv
    written = 0
    for d in problem_dirs(targets):
        scratch = d / "Scratch.java"
        if scratch.exists() and not force:
            continue
        scratch.write_text(skeleton((d / "Solution.java").read_text()))
        written += 1
    print(f"wrote {written} Scratch.java file(s)")


if __name__ == "__main__":
    main(sys.argv[1:])
