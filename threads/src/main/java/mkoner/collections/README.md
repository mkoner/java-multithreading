### Synchronized Collections

In Java, synchronized collections are thread-safe versions of standard collections provided by
utility methods in the Collections class, such as:
 - Collections.synchronizedList(List<T> list)
 - Collections.synchronizedMap(Map<K, V> map)
 - Collections.synchronizedSet(Set<T> set)

<br>These methods wrap the given collection and synchronize access to it, ensuring that only one
thread can modify it at a time.

Cons:
<br>Low performance due to single lock(coarse grained) used for the entire resource even for non related operations

### CountDownLatch

CountDownLatch is a synchronization aid in Java that allows one or more threads to wait until a
set of operations being performed by other threads completes. It is part of the
java.util.concurrent package.
- It starts with a count (an integer) that represents the number of tasks that must complete before
  the waiting thread(s) can proceed.
- Threads call countDown() to decrease the count.
- Other threads call await(), which blocks them until the count reaches zero.

#### Key Methods of CountDownLatch

|Method	| Description|
|-------|------------|
|CountDownLatch(int count)|	Creates a latch with the given count.|
|await()|	Blocks until the count reaches zero.|
|await(long timeout, TimeUnit unit)|	Waits for the count to reach zero or the timeout expires.|
|countDown()|	Decreases the count by one.|
|getCount()|	Returns the current count.|

#### Cons of CountDownLatch
- Cannot Be Reused: Once the count reaches zero, it cannot be reset; a new CountDownLatch must be created if needed.
- Fixed Number of Participants: You must know in advance how many threads need to call countDown()


### BlockingQueue

It's a thread-safe queue where the operation to insert an element can wait
for space to become available and the retrieve operation can wait for an element to be added when the queue is empty.
<br>
Impl classes: ArrayBlockingQueue, DelayQueue (elements can only be consumed after a certain delay), 
LinkedBlockingDeque, LinkedBlockingQueue, LinkedTransferQueue, PriorityBlockingQueue, 
SynchronousQueue(0 capacity, after inserting an element  we can only do another insertion when that element is consumed)
<br>
#### Operations

add(e), remove(), element() -> throw exception<br>
offer(e), poll(), peek() -> return special values (boolean for offer(), null is empty for poll() and peek())<br>
put(e), take() -> blocking ops<br>
offer(e, time, unit), poll(time, unit) -> times out<br>

#### Sub interfaces of BlockingQueue:
- BlockingDeque<E>:  double-ended queue that supports blocking operations, Blocks if full but doesn’t wait for consumption,
insertion at both ends (putFirst(), putLast()), consume from both ends (takeFirst(), takeLast())
<br> Concrete impl LinkedBlockingDeque
- TransferQueue<E>:  allows producers to wait until consumers retrieve elements.
  Methods: 
  * transfer(E e) → Blocks until a consumer receives the element.
  * tryTransfer(E e) → Returns true if an element is immediately transferred; otherwise, false.
  * tryTransfer(E e, long timeout, TimeUnit unit) → Waits for a consumer for the given time.
  * hasWaitingConsumer() → Checks if any consumer is waiting.
<br> Concrete impl LinkedTransferQueue<E>
  
### ConcurrentMap<K, V>
is a thread-safe variant of Map<K, V> designed for concurrent access without requiring explicit 
synchronization. It belongs to the java.util.concurrent package and provides atomic operations that 
allow multiple threads to modify the map concurrently without corrupting the data.
<br>
Most used Implementing Classes: ConcurrentHashMap 
#### Key Features:
- Thread-safe: Multiple threads can access it concurrently.
- Lock-free Reads: Read operations (get(), containsKey()) do not require locks.
- Segmented Locking: Uses a finer-grained locking mechanism (on buckets instead of the whole map).
- Atomic Operations: Supports methods like putIfAbsent(), compute(), and replace().

