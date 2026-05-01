**Problem Statement:**
Design and implement a thread-safe, in-memory Key-Value store (a mini-Redis). The system will be heavily read/write bound and accessed concurrently by dozens of client threads.

**Required API / Operations:**
1.  `put(String key, String value, long ttlInSeconds)`: Inserts or updates a key-value pair. The `ttlInSeconds` (Time-To-Live) dictates how long the key is valid. If `ttlInSeconds` is `<= 0`, the key never expires.
2.  `get(String key)`: Retrieves the value associated with the key. If the key does not exist, or if its TTL has expired, it must throw a custom `KeyNotFoundException`.
3.  `delete(String key)`: Removes the key from the store.

**System Constraints & Expectations:**
* **Thread Safety:** The system must handle concurrent reads and writes without data corruption or `ConcurrentModificationException`.
* **Performance:** The map must be highly performant. A lock on the entire data structure for every read/write is an automatic fail.
* **Memory Management (Active Expiration):** You cannot rely solely on "lazy expiration" (only checking if a key is expired when someone calls `get()`). You must implement a mechanism that actively removes expired keys in the background to prevent memory leaks from keys that are written but never read again.
* **Production-Ready:** Code must demonstrate clean OOP principles, proper encapsulation, and efficient resource handling.

**Deliverables:**
Provide the complete, compilable Java code for:
1.  The custom Exception class.
2.  Any internal data models/wrappers you need.
3.  The main Key-Value Store class containing the required API and background cleanup logic.
