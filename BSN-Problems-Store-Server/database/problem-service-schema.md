---

# 📘 **Problem Service – Database Schema Documentation**

This document describes the complete database schema for the **Problem Service** used in the DSA Mobile App.
The service manages DSA problems, approaches, multiple solution versions, tags, patterns, and related metadata.

---

# 📂 **Table Overview**

| Table Name               | Purpose                                              |
| ------------------------ | ---------------------------------------------------- |
| **PROBLEMS**             | Stores main DSA problem details                      |
| **PROBLEM_CATEGORY**     | Defines categories such as Arrays, Strings, DP, etc. |
| **PROBLEM_TAGS**         | Tags like “easy”, “sliding window”, “graph”          |
| **PROBLEM_TAG_MAPPING**  | Mapping table between problems and tags              |
| **PROBLEM_APPROACH**     | Stores Good / Better / Best approach metadata        |
| **SOLUTION_CODE**        | Stores Java/Python/CPP code for each approach        |
| **SOLUTION_EXPLANATION** | Explanations such as logic, steps, complexity        |

---

# 🧩 **1. PROBLEMS Table**

Stores core problem details accessible to users.

### **Table: PROBLEMS**

| Column          | Type          | Description               |
| --------------- | ------------- | ------------------------- |
| **PROBLEM_ID**  | NUMBER (PK)   | Unique ID                 |
| **TITLE**       | VARCHAR2(255) | Problem name              |
| **DESCRIPTION** | CLOB          | Full description          |
| **DIFFICULTY**  | VARCHAR2(20)  | EASY / MEDIUM / HARD      |
| **CATEGORY_ID** | NUMBER (FK)   | Links to PROBLEM_CATEGORY |
| **CONSTRAINTS** | CLOB          | Input constraints         |
| **EXAMPLES**    | CLOB          | Test cases                |
| **IS_ACTIVE**   | NUMBER(1)     | 1 = active, 0 = hidden    |
| **CREATED_AT**  | TIMESTAMP     | Timestamp                 |
| **UPDATED_AT**  | TIMESTAMP     | Last updated              |

---

# 🗂 **2. PROBLEM_CATEGORY Table**

Defines high-level patterns such as Arrays, Two Pointers, DP, Trees, Graphs, etc.

### **Table: PROBLEM_CATEGORY**

| Column            | Type          | Description                   |
| ----------------- | ------------- | ----------------------------- |
| **CATEGORY_ID**   | NUMBER (PK)   | Unique ID                     |
| **CATEGORY_NAME** | VARCHAR2(100) | Arrays / Strings / DP / Trees |
| **DESCRIPTION**   | VARCHAR2(255) | Optional info                 |

---

# 🏷 **3. PROBLEM_TAGS Table**

Used to store additional classifications.

### **Table: PROBLEM_TAGS**

| Column          | Type          | Description            |
| --------------- | ------------- | ---------------------- |
| **TAG_ID**      | NUMBER (PK)   | Unique ID              |
| **TAG_NAME**    | VARCHAR2(100) | e.g., "sliding window" |
| **DESCRIPTION** | VARCHAR2(255) | Optional               |

---

# 🔗 **4. PROBLEM_TAG_MAPPING Table**

Many-to-many mapping between problems and tags.

### **Table: PROBLEM_TAG_MAPPING**

| Column          | Type                 | Description |
| --------------- | -------------------- | ----------- |
| **PROBLEM_ID**  | NUMBER (FK)          | Problem     |
| **TAG_ID**      | NUMBER (FK)          | Tag         |
| **PRIMARY KEY** | (PROBLEM_ID, TAG_ID) | Composite   |

---

# 🧠 **5. PROBLEM_APPROACH Table**

A single problem has 3 standard approaches:

* **GOOD**
* **BETTER**
* **BEST**

This table stores metadata per approach.

### **Table: PROBLEM_APPROACH**

| Column               | Type          | Description          |
| -------------------- | ------------- | -------------------- |
| **APPROACH_ID**      | NUMBER (PK)   | Unique ID            |
| **PROBLEM_ID**       | NUMBER (FK)   | Problem              |
| **APPROACH_LEVEL**   | VARCHAR2(20)  | GOOD / BETTER / BEST |
| **TIME_COMPLEXITY**  | VARCHAR2(50)  | e.g., O(n log n)     |
| **SPACE_COMPLEXITY** | VARCHAR2(50)  | e.g., O(1)           |
| **SUMMARY**          | VARCHAR2(500) | Short explanation    |

---

# 💻 **6. SOLUTION_CODE Table**

Each approach can have 3 code versions:

* Java
* Python
* CPP

### **Table: SOLUTION_CODE**

| Column          | Type         | Description               |
| --------------- | ------------ | ------------------------- |
| **CODE_ID**     | NUMBER (PK)  | Unique ID                 |
| **APPROACH_ID** | NUMBER (FK)  | Links to PROBLEM_APPROACH |
| **LANGUAGE**    | VARCHAR2(20) | JAVA / PYTHON / CPP       |
| **SOURCE_CODE** | CLOB         | Actual code content       |
| **RUNTIME**     | VARCHAR2(50) | Measured runtime          |
| **MEMORY**      | VARCHAR2(50) | Memory usage              |
| **IS_OPTIMAL**  | NUMBER(1)    | 1 = best implementation   |

---

# 📄 **7. SOLUTION_EXPLANATION Table**

Long written explanations for each approach.

### **Table: SOLUTION_EXPLANATION**

| Column             | Type          | Description              |
| ------------------ | ------------- | ------------------------ |
| **EXPLANATION_ID** | NUMBER (PK)   | Unique ID                |
| **APPROACH_ID**    | NUMBER (FK)   | Approach                 |
| **LOGIC**          | CLOB          | Step-by-step explanation |
| **EDGE_CASES**     | CLOB          | All edge cases handled   |
| **DRY_RUN**        | CLOB          | Dry run example          |
| **NOTES**          | VARCHAR2(255) | Extra notes              |

---

# 🔗 **ER Diagram (Text Representation)**

```
PROBLEMS (1) ----- (M) PROBLEM_APPROACH ----- (M) SOLUTION_CODE
         \
          \— (M) PROBLEM_TAG_MAPPING — (M) PROBLEM_TAGS

PROBLEMS (M) — (1) PROBLEM_CATEGORY

PROBLEM_APPROACH (1) — (1) SOLUTION_EXPLANATION
```

---

# 🏛 **Design Principles Followed**

* Normalized schema
* Scalable for thousands of DSA problems
* Multi-language code support
* Multiple solution versions per approach
* Tag-based filtering for mobile optimization
* Clean separation of problem → approach → code → explanation

---
