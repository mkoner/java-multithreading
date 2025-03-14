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