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
