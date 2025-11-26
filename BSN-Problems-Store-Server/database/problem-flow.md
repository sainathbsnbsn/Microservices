                 ┌──────────── PROBLEMS ────────────┐
                 |          PROBLEM_ID = 101        |
                 └───────────────────────────────────┘
                               |
                               | 1-to-Many
                               v
          ┌──── PROBLEM_SOLUTIONS (Approaches) ─────┐
          | SOLUTION_ID=1,2,3                        |
          | APPROACH_NAME="Brute Force" → tag="good" |
          | APPROACH_NAME="Two Pointer" → tag="better"|
          | APPROACH_NAME="HashMap Optimal"→tag="best"|
          └──────────────────────────────────────────┘
                        | 1-to-Many
                        v
            ┌──── SOLUTION_CODE (Implementations) ──┐
            | CODE_ID 11 → SOLUTION_ID 1 → JAVA     |
            | CODE_ID 12 → SOLUTION_ID 1 → CPP      |
            | CODE_ID 13 → SOLUTION_ID 1 → PYTHON   |
            | CODE_ID 21 → SOLUTION_ID 2 → JAVA     |
            | CODE_ID 22 → SOLUTION_ID 2 → CPP      |
            | CODE_ID 23 → SOLUTION_ID 2 → PYTHON   |
            | CODE_ID 31 → SOLUTION_ID 3 → JAVA     |
            | CODE_ID 32 → SOLUTION_ID 3 → CPP      |
            | CODE_ID 33 → SOLUTION_ID 3 → PYTHON   |
            └────────────────────────────────────────┘
                        |
                        | 1-to-1
                        v
           ┌── SOLUTION_EXPLANATION (Only loads when needed) ──┐
           | EXPLANATION_ID 7001 → SOLUTION_ID 1 (Brute Force) |
           | Contains logic, dry run, edge cases, notes        |
           └───────────────────────────────────────────────────┘

                   ┌──────── TAGS (master list) ───────┐
                   | TAG_ID = 3001 → "Arrays"          |
                   | TAG_ID = 3002 → "Two Pointers"    |
                   | TAG_ID = 3003 → "Hashing"         |
                   └────────────────────────────────────┘
                               ↑
                               |
                               | Many-to-Many
                               |
                 ┌──── PROBLEM_TAG_MAPPING ─────┐
                 | PK {PROBLEM_ID, TAG_ID}      |
                 | {101,3001}, {101,3002}, ...  |
                 └───────────────────────────────┘
