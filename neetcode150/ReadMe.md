? NeetCode 150 Directory Structure Using Copilot
Using Copilot, I created a structured set of directories in Windows to organize NeetCode 150 problems by category and difficulty.
? Step 1: Create Category Folders
$dirs = @(
"Arrays & Hashing",
"Two Pointers",
"Sliding Window",
"Stack",
"Binary Search",
"Linked List",
"Trees",
"Heap / Priority Queue",
"Backtracking",
"Tries",
"Graphs",
"Advanced Graphs",
"1-D Dynamic Programming",
"2-D Dynamic Programming",
"Greedy",
"Intervals",
"Math & Geometry",
"Bit Manipulation"
)

foreach ($d in $dirs) {
New-Item -ItemType Directory -Path "D:\Admin\May2023\git-projects\MyCodeLib\neetcode150\$d"
}

????
? You can replace "D:\Admin\May2023\git-projects\MyCodeLib\neetcode150\$d" with any desired path like "C:\NeetCode150\$d".


? Step 2: Create Difficulty Subfolders (Easy, Medium, Hard) Inside Each Category
$categories = @(
"Arrays & Hashing",
"Two Pointers",
"Sliding Window",
"Stack",
"Binary Search",
"Linked List",
"Trees",
"Heap / Priority Queue",
"Backtracking",
"Tries",
"Graphs",
"Advanced Graphs",
"1-D Dynamic Programming",
"2-D Dynamic Programming",
"Greedy",
"Intervals",
"Math & Geometry",
"Bit Manipulation"
)

$difficulties = @("Easy", "Medium", "Hard")

foreach ($category in $categories) {
$categoryPath = ".\$category"
New-Item -ItemType Directory -Path $categoryPath -Force
foreach ($difficulty in $difficulties) {
$difficultyPath = Join-Path $categoryPath $difficulty
New-Item -ItemType Directory -Path $difficultyPath -Force
}
}

??

? Final Directory Root
D:\Admin\May2023\git-projects\MyCodeLib\neetcode150\


Each category will contain three subfolders: Easy, Medium, and Hard, ready for organizing problems by difficulty.


