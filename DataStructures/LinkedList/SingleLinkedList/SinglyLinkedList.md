# 🔗 Singly Linked List

A **Singly Linked List** is a fundamental, linear data structure where elements (nodes) are stored dynamically in memory. Each node contains data and a reference (or pointer) to the next node in the sequence, allowing for efficient insertions and deletions without reallocation.

---

## 🧐 Why do we need this? (Problem Statement)

Arrays store elements in contiguous memory locations, which makes resizing expensive and insertions/deletions slow because of shifting elements. **Linked Lists** solve this by storing elements randomly in memory, linking them together via pointers.

| Feature | 📦 Array | 🔗 Linked List |
| :--- | :--- | :--- |
| **Memory Allocation** | Static / Contiguous | Dynamic / Non-contiguous |
| **Size** | Fixed (unless dynamic array) | Dynamic (grows/shrinks easily) |
| **Insertion/Deletion** | Slow O(N) (requires shifting) | Fast O(1) at Head/Tail |
| **Random Access** | Fast O(1) | Slow O(N) (must traverse) |

> [!TIP]
> Use Arrays when you need fast, index-based access. Use Linked Lists when you have frequent insertions/deletions and unpredictable size requirements.

---

## 🏗️ Structure of a Node
A Singly Linked List is made up of individual `Node` objects. Every node has two parts:
1. **Data:** The value you want to store.
2. **Next Pointer:** The reference to the next node.

```java
class Node {
    int data;
    Node next; // Points to the next node in the list

    Node(int data) {
        this.data = data;
        this.next = null; // Next is null by default for a new node
    }
}
```

---

## 🛠️ Core Operations

### 1. ➕ Insertion
Adding a new node to the list. We track both `head` and `tail` pointers to optimize operations.

- **At the Beginning:** O(1)
- **At the End:** O(1) (because we maintain a `tail` pointer)
- **At a Specific Position:** O(N)

```java
// Insert at the End (Optimized O(1) using tail)
public void insertAtEnd(int data) {
    Node newNode = new Node(data);
    if (head == null) {
        head = newNode;
        tail = newNode;
    } else {
        tail.next = newNode;
        tail = newNode;
    }
}

// Insert at the Beginning (O(1))
public void insertAtBeg(int data) {
    Node newNode = new Node(data);
    newNode.next = head; // Point new node to current head
    head = newNode;      // Update head to be the new node
}

// Insert at a specific Position (O(N))
public void insertAtPos(int pos, int data) {
    Node newNode = new Node(data);
    Node temp = head;
    // Traverse to the node just before the insertion point
    for (int i = 0; i < pos - 1; i++) {
        temp = temp.next;
    }
    newNode.next = temp.next; // Link new node to the next part of the list
    temp.next = newNode;      // Link previous node to the new node
}
```

### 2. ➖ Deletion
Removing a node and adjusting the pointers to keep the chain unbroken.

- **From the Beginning:** O(1)
- **From the End:** O(N) (must traverse to find the second-to-last node)
- **At a Specific Position:** O(N)

```java
// Delete at the Beginning (O(1))
public void deleteBeg() {
    Node temp = head;
    head = head.next; // Shift head to the next node
    temp.next = null; // Disconnect the old head
}

// Delete at a Position (O(N))
public void deleteAt(int pos) {
    Node temp = head;
    for (int i = 0; i < pos - 1; i++) {
        temp = temp.next;
    }
    temp.next = temp.next.next; // Bypass the node to delete it
}

// Delete at the End (O(N))
public void deleteEnd() {
    Node temp = head;
    while (temp.next != tail) {
        temp = temp.next; // Traverse to 2nd to last node
    }
    temp.next = null;
    tail = temp; // Update tail
}
```

> [!CAUTION]
> Always check for `NullPointerException`. For instance, trying to delete from an empty list or accessing `.next` of a `null` node will crash your program. Make sure to handle edge cases like `head == null` or deleting the *last* remaining element!

### 3. 🔄 Reversing the List
Reversing the next pointers of all nodes so the list is inverted in-place.
- **Time Complexity:** O(N)
- **Space Complexity:** O(1)

```java
public void reverse() {
    Node prev = null;
    Node current = head;
    Node next = head.next;
    
    while (current != null) {
        next = current.next; // Store next node
        current.next = prev; // Reverse the link
        prev = current;      // Move prev one step forward
        current = next;      // Move current one step forward
    }
    head = prev; // Update head to the last node
}
```

---

## 🏃‍♂️ Traversal and Display
To read or display elements, you iterate through the list until the pointer reaches `null`.

```java
public void display() {
    Node temp = head;
    while (temp != null) {
        System.out.print(temp.data + " ");
        temp = temp.next;
    }
    System.out.println();
}
```

---

## 📝 Workflow Summary Recap
Use this quick cheat sheet to remember Singly Linked List Time Complexities:
- **Search:** O(N)
- **Insert at Head:** O(1)
- **Insert at Tail:** O(1) (with tail pointer), else O(N)
- **Insert at Nth Position:** O(N)
- **Delete Head:** O(1)
- **Delete Tail:** O(N) (even with tail pointer, you need the 2nd to last node)
- **Delete Nth Position:** O(N)

---

## 🤖 Next Steps / Practice Ideas
- Implement cycle detection algorithms (e.g., Floyd's Tortoise and Hare).
- Try finding the middle element of the linked list in a single pass.
- Try merging two sorted linked lists.
