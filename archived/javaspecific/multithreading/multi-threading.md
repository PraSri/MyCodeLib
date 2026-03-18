* There are two types of threads in an application –
    *   user thread 
    *   daemon thread.

* When we start an application, main is the first user thread created and we can create multiple user threads as well as daemon threads.
* When all the user threads are executed, JVM terminates the program.

* We can set different priorities to different Threads but it doesn’t guarantee that higher priority thread will execute first than lower priority thread. 
* Thread scheduler is the part of Operating System implementation and when a Thread is started, it’s execution is controlled by Thread Scheduler and JVM doesn’t have any control on it’s execution.

* We can create Threads by either implementing **Runnable** interface or by extending **Thread Class**.

[Journaldev tutorial](https://www.journaldev.com/1079/multithreading-in-java)


* If a thread want to pause its execution for some time, then It may either call
	* sleep(2000);
    * join(2000);

* In sleep(), Thread scheduler pauses thread’s execution. If there is no other thread to run then, for 2000 ms , processor will be idle.

* In sleep, locks possessed by thread will not be released.

* Lets see Join :

* Join has following overloaded versions (join is synchronised):

* t.join()-> waits until thread t is dead.

* t.join(2000) -> waits max 2000 ms for thread t to complete, is t does not finished in 2000 ms then current thread will not wait, and will start its execution

* t.join(2000, 100)

* After calling Join() in java, a thread pauses its execution and wait until some other thread completely finishes the task or, time-expires,

* In Join, CPU does not become Idle.

## Thread Safety in Java

* Synchronization is the easiest and most widely used tool for thread safety in java.
* Use of Atomic Wrapper classes from java.util.concurrent.atomic package. For example AtomicInteger
* Use of locks from java.util.concurrent.locks package.
* Using thread safe collection classes, check this post for usage of ConcurrentHashMap for thread safety.
* Using volatile keyword with variables to make every thread read the data from memory, not read from thread cache.

## Java synchronized
Synchronization is the tool using which we can achieve thread-safety, JVM guarantees that synchronized code will be executed by only one thread at a time. java keyword synchronized is used to create synchronized code and internally it uses locks on Object or Class to make sure only one thread is executing the synchronized code.

* When a method is synchronized, it locks the **Object**, if **method is static it locks the Class**, so it’s always best practice to use synchronized block to lock the only sections of method that needs synchronization.

* You should use the lowest level of locking, for example, if there are multiple synchronized block in a class and one of them is locking the Object, then other synchronized blocks will also be not available for execution by other threads. When we lock an Object, it acquires a lock on all the fields of the Object

**Java Synchronization could result in deadlocks**

**Java synchronized keyword cannot be used for constructors and variables.**

* It is preferable to create a dummy private Object to use for the synchronized block so that it’s reference can’t be changed by any other code. For example, if you have a setter method for Object on which you are synchronizing, it’s reference can be changed by some other code leads to the parallel execution of the synchronized block.

* We should not use any object that is maintained in a constant pool, for example String should not be used for synchronization because if any other code is also locking on same String, it will try to acquire lock on the same reference object from String pool and even though both the codes are unrelated, they will lock each other.


