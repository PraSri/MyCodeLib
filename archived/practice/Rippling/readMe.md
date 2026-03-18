Design Excel Sum Formula - y
Median of Two Sorted Arrays - y
Design Spreadsheet - y
Evaluate Division - y
Optimal Account Balancing - y
Maximum Subarray - y
Employee Importance - y
Synonymous Sentences - y
Longest Duplicate Substring
Last Stone Weight - y
Number of Visible People in a Queue
Accounts Merge - y
Insert Delete GetRandom O(1) - y
LFU Cache
Merge Two Sorted Lists - y
Maximize Amount After Two Days of Conversions
Alien Dictionary

We have a list of expense and rules. Aim is to validate rules for each expense.

Each expense is similar to

{
expenseid: "1"
itemId: "Item1"
expensetype: "Food"
amountInUsd : "250"
sellerType : "restaurant"
SellerName "ABC restaurant"

}
List of rules similar to

- Total expense should not be > 175
- Seller type restaurant should not have expense more that 45
- Entertainment expense type should not be charged
  Run the rules on expense and flag the rule which do not satisfy. Implement following in Java Oops:
  evaluateRule(List<rule> , List<expense>)

The follow up would be to add some aggregation rules:

The total expense on trip should not go beyond XX amount.
The total expense on food on a trip should not go beyond XX amount.
This question moreover assess your ability to disambiguate the problem and adaptability of the code for follow up
problems


**************************

Technical Screening Round
The interview asked me the below question
Part 1
Given a list of some drivers and the deliveries they are making, need to write a code to compute the total cost of all
deliveries. It needed to have 3 methods

add driver(driverId)
add deliveries(startTime, endTime)
getTotalCost()
getTotalCost was needed in optimised time, which I provided by computing cost at the time of adding the delivery -
interviewer had agreed to this approach.
I explained the approach and solved by defining 3 classes Driver, Delivery and the main service. The panelist tested it
against his test cases and got the expected results.

I had asked for the evaluation criteria to which the interviewer had said let's solve the question as much as possible

Part 2
Add two more functionalities

payUpToTime(upToTime) -> settle the delivery cost up to this time
getCostToBePaid() -> get the remaining delivery costs left after settling the payment
Discussed the approach for this in O(n), he wanted more optimal solution. Discussed approach for production env based on
events to trigger completion and keeping a track of completed deliveries. Then to be paid is as simple as paid minus
total cost (from part 1)
Agreed on the approach, wrote the code, tested it against interviewer's test cases - worked as expected

Part 3
Implement a method to provide simultaneous deliveries across drivers. He was only expecting the approach. Discussed by
sorting and then checking the overlaps. Seemed satisfied and we closed the discussion.

Question about designing music library where we had to print the most played songs(by unique users). This was then
extended to also add a function to return 3 unique most recently played songs.

Part 2: return 3 unique most recently played songs.

Problem Statement: Design a Song Play Analytics System

Design a system to track song plays and generate analytics based on the number of unique listeners per song. Implement
the SongAnalytics class with the following methods:

SongAnalytics()
Initializes the system.

int add_song(string name)
Adds a song to the system, assigns it a unique auto-incrementing ID starting from 1, and returns the assigned ID.

void play_song(int song_id, int user_id)
Records a play event for a song by a user.

If song_id does not exist, output: Error: Song ID <song_id> does not exist. (replace <song_id> with the invalid ID).
Each user is counted once per song, even if they play it multiple times.

void print_analytics()
Prints a summary of all songs sorted by the number of unique listeners in descending order.

If two songs have the same number of unique listeners, sort them lexicographically by name in ascending order.
Each line in the output should follow the format: <song_name> (<count> unique listeners).
Example:
Input:
add_song("Song A") ? returns 1  
add_song("Song B") ? returns 2  
add_song("Song C") ? returns 3  
play_song(1, 1)  
play_song(1, 2)  
play_song(2, 1)  
play_song(3, 3)  
play_song(3, 3)  
print_analytics()

Output:
Song A (2 unique listeners)  
Song B (1 unique listeners)  
Song C (1 unique listeners)



***********************




Implement an encryption algorithm to encrypt the string originalString per the following:

Initialize two empty strings: temporaryString and encryptedString.

At each step, perform one of the following two operations. Choose the order of operations to produce the
lexicographically minimum possible encryptedString.

Take the first letter from originalString and append it to the end of temporaryString.

Take the last letter from temporaryString and append it to the end of the encryptedString.

Example:
originalString = "dby"
output = "bdy"

originalString = "vgxgpu"
output = "ggpuxv"

***********************


Base problem: Given a list of delivery drivers, the per hour payment of each driver and a list of deliveries they do,
your job is to find the total to be paid to all the driver combined. They were expecting a production quality code
approach. I wrote a class and created respective fucntions for:

Add driver and per hour rate.
Add delivery (This was epoch seconds)
Get total to amount to be paid to all drivers.
Scale up: Given a time when we clear payments, output the total that has to be paid. Also, there should be another
function that returns the total unpaid.

Scale up 3: I don't remember now. I just had to give the approach.

System design
Design a system where Rippling's customers can upload documents for review by internal audit team. Each document has a
time to review complete.

I got stuck in a lot of nice-to-haves that the interviewer kept pushing me away from. Overall, I think I was
concentrating too much on unnecessary functional requirement.


********************************


Base question: Given a list of songs, and users playing songs, return the list of songs in descending order of most
unique users playing the songs.

Scale up: Return the per user most recently played top 3 songs. I had a discussion about the time complexity of what
happens when the number 3 is arbritary and so on.

All in all, I messed up the code clarity by a huge bit.


*****************************************





Design and implement an Excel Spreadsheet

You are building a library that a frontend team may use to surface a spreadsheet to some users. We want this to be a MVP
library that can be expanded in the future if necessary. All data can be stored in memory.

We have the following requirements:

We need to support integer values and formula values for the plus operator. For example, cell values may be: -9, 2, 3,
100, etc. (maximum of 3 digits per integer) ; =2+8, =100+2, etc. (with exactly two integers)
We need to be able to reset cells to their default value, by giving the empty string.
We need to be able to view the spreadsheet, with both the raw and computed values shown. For example, for "=2+8”, the
raw value is “=2+8” and the computed value is “10”.
This does not have to look like a real spreadsheet, as long as the values are clear when printed.

Create a print method to print the whole spreadsheet, or return the spreadsheet data as a string.
The team will later focus on reference values, such as "=A1+B2”.

Round 1 - DSA
Design a Music Player like Spotify with below methods

int addSong(string songTitle); // add a song to your music player with incremental song ids starting from 1
void playSong(int songId, int userId); // user plays a song that is present in the music player
void printMostPlayedSongs(); // print song titles in decreasing order of number of unique users' plays
Follow up

vector<int> getLastThreeSongs(int userId); // get last 3 unique songs played by a given user
There was another follow up as well which the recruiter mentioned post the interview. Couldn't get time for that.

Round 2 - Hiring Manager
General Behavioural Questions

The next 3 rounds happend in the office

Round 3 - DSA
Design a Key Value Store

string get(string key);
void set(string key, string value);
void deleteKey(string key);
Follow up 1
Support transactions - begin, commit and rollback. I immediately asked the interviewer if we needed to support nested
transactions. He said yes but for the next followup. He still asked me to first code it for 1 level of transaction for
which I took a lot of time.

Follow up 2
Support nested transactions. No time was left.

Round 4 - DSA
Design an excel sheet

void set(string cell, string value); // cell can be A1, B2. value can be like "10", "1" or even excel formulae like "
=9+10" and "=-1+-10+2"
void reset(string cell); // reset the cell
void print(); // print all the cells along with their raw and computed values
Follow up
Extend solution to support values like "=A1+10" where A1 is a cell name

Round 5 - System Design
Design Google News

****************************************************8

Implement 3 APIs for a delivery system app. The delivery driver has an hourly rate for performing delivery. Implement
following three APIs:
addDriver(driverId(integer), hourlyRate(float))
addOrder(driverId, startTime, endTime): mention the choice of datatype you choose for time and why
getTotalCost for all the deliveries.
Question was straight forward.
Follow up question:
what is the issue with using float and double as data type for currency and what data type should you use?
what changes would you make in the code if we want to update the hourly rate of a driver.
I was optimistic about this round as I was able to write production level code, handling concurrency and other best
practices and answer the followup questions

Screening round:
Question about designing music library where we had to print the most played songs(by unique users). This was then
extended to also add a function to return 3 most recently played songs. Full statement see round 1 of this interview
experience.
I was able to complete the first part and almost complete the second with a couple of bugs. Wasn't able to run
completely the second part.

HM round:
Went pretty smooth. One project deep dive and situational questions.

Coding Round:
Design key value store. Extended to transactions. See the previously attached interview experience or this.

Coding Round:
Design excel sheet. Two main functions:
set(cell, value/expression)
print() -> should print both raw and computed values.

Example:
set(A1, "=5+9+-8")
set(A2, "5")

Only +,- and in case of -, + would be preceding which made it easier to split. This was then extended to:
set(A1, "=A1+5")
set(A3, "=A1")

Created Cell class and Map<String, Cell> for the sheet. Cell had an isExpression variable which I populated by checking
if the input starts with "=". I suggested having different cell types i.e. StaticCell, ExpressionCell(didn't implement)
but interviewer wasn't particularly excited about this.
I calculated the expression values while printing lazily(I am not sure what he expected) but I told him at the end that
we had a choice of calculating proactively using some sort of dependency graph or just do it lazily using recursion.

I wouldn't recommend my solution for the interview even though the feedback for the round was positive.

System Design:
Design hotel reservation aggregator. The only difference was that the hotel can be listed on other aggregators as well
and we need to handle this. I moved this requirement to the end and designed rest of the system before.
Feedback for this round was borderline.

The problem was like: given a list of transaction <from, to, amount>, I have to settle them and return who will pay
whom, how much. (note: it did not ask me to find the most optimal way to settle like in Splitwise simplify balance
feature).
I gave a solution, and coded it. I did it in Java to use Oops etc(as i use Java in my day to day job) but I practice DSA
with c++. And it lead to multiple compilation errors, as I haven't used a lot of datastructures in Java. My suggestion
will be to stick to one language for coding.
I told the intervviewer regading the same and they were ok and said they are fine with the solution.
In last 5min they asked me a follow up, what is the most optimal way to settle the balance. I gave them a greedy
solution and we ended the interview.

It was like design a Music Player analytics sort of service(in memory), that prints most played song.

void playSong(songId, userId);

void addSong(songId, title);

void printAnalytics();
I was able to code. Then they asked a follow up to add a feature to star/unstar song(s) for a user and get last N
favourite songs played(something like that). I wasn't able to fully write the working code for it as time was up.

First problem was to implement a Cache to add/get/delete key/values(both strings).
This was straight forward, I implemented a class in c++ with the relevant methods.

The follow up was, that I need to support transactions as well. for e.g. user can call BEGIN transaction, COMMIT
transaction or ROLLBACK transaction.
During the transaction, user should be able to get/delete/put. In case the transaction is rolled back, all operations
that happened during the transaction should be reverted.

I implemented this solution using a temporary map to store data during transaction and add the dataa to global(to cache)
map. In case of rollback, just clear temporary data map.

I missed 2 corner cases in delete method functionality which the interviewer pointed out(as in they just said think hard
if it covers all cases, I did and improve. 1 more case was missed and they were mocking me like "I can't keep telling
you again & again to think hard ...)), they were kinda rude. Most egoistic and rude interviewer I have ever had. They
were like trying to pull me down rather than being helpful or empathetic.

***************************************************88

HLD
In this round I was asked to design a Google news sort of service where we scrape from multiple news sites. Assume they
provide endpoint to get latest 25 news from each publisher. There can be thousands of publishers.
It was required to write down table schema for all like publisher, user, category_subscription, publisher_subscription,
articles etc and handle deduplication in article scraping service etc.
Along with this, user's should be able to subscribe to certain categories like Education or publishers say ndtv.
On home page.

1st round (Screening - High Level Design round)
News aggregator system like Google News

Fetch news articles from multiple news publishers
Generate custom news feed for Users based on their interests and the publishers they follow

3rd round (High Level Design)
Design an event system like amplitude with analytics and reporting use cases

4th round (DSA)
Design a key value cache system and implement set, get, delete methods to be expected. Input would be as list of
strings.
["SET key1 value1"]
["GET key1"]
["DELETE key1"]

Follow up: How do we handle transactions?
A transaction starts with BEGIN and operations like SET, GET, DELETE can take place during this time. After these
operation, the transaction is ended by either a COMMIT that commits everything permanently in the data store or ROLLBACK
that reverts everything that was performed during the transaction window.
We need to implement commit and rollback methods

5th round (DSA)
https://leetcode.com/discuss/interview-question/483660/Google-or-Phone-or-Currency-Conversion
Even this problem has a follow up question to find the currency conversion in such a way that the conversion rate is
maximum. We need to return the max conversion value rather than minimum value to convert.

Round 1: Phone Screen
Design a K-V Store to support the following: getByKey, updateValueByKey, removeKey
A followup was asked on handling concurrency for updateValueByKey, when two threads are trying to update the value for a
key. (Had to replicate and then provide a solution)

Round 2:
Design a spreadsheet similar to google sheets which supports basic functionalites of a spreadsheet.

for eg:
1.) setting up a cell: A1 = 1
2.) sum operations: A2 = "1+2"
A followup was asked where interviewer asked to extend the design to support multiplication and dependency like A2 =
B1+B2.

Note: There could be many equations in any order.
A1 = 5
B1 = 6
E2 = A2+B2
A2 = D1+E2
B2 = 1 + A1
I explained my topo-sort based approach and coded it successfully. I used observor pattern to solve this for design. You
would find similar approach in the discuss section of a similar
question: https://leetcode.com/problems/design-excel-sum-formula/description/

Round 3:
2 questions were asked:
1.) A large file of append only streaming logs would be given with (user_id, date, timestamp), task was to find out the
number of users whose timestamp[i] < timestamp[j] and user_id[i] > user_id[j] for a given date.

2.) A combinatorics question, I could not get it on the first try but after a hint from the interviewer I was able to
give an approach. Interviewer seemed satisfied and discussed time/space complexities.
similar question: https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/description/

Round 4: Design round
Design a photo upload service like Instagram. A detailed discussion on DB, Tables, APIs, Caching, Sync/Async, Fan Out(
push/pull, Discussion around handling celebrity problem). Scaling individual components, SPOF etc.
Some questions on setting up alerting/monitoring/metrics/analytics for this service.

Verdict: Offer

Problem: Design a News Aggregator

System Design: Design an event tracking system like amplitude

This was interesting problem because the interviewer gave me a lot of requirements before hand - sdk, servers, database,
query, chart render etc.
I gave a few sub-optimal solutions for a few components and could not discuss all the requirements.
When there was 5 minutes left I took the opportunity to talk about all the missed components at a high level and also
touched points on observability - metrics, logging, tracing. I also mentioned on-call alerting, incident, SOPs,
scalability, problems with retries bottlenecks.
I believe this helped me get through the interview

DSA: Write a KV in memory store
The interviewer gave a relatively simple problem initially with only save and delete operations and then later added
commit, rollback (transaction)

https://leetcode.com/discuss/interview-question/483660/google-phone-currency-conversion
Prepare this well. I prpeared both bfs and dfs but somehow in interview i thought to apply dfs which i was not
confident. Should have done bfs as i was pretty sure. Prepare well you can pass tech round.

https://medium.com/@rickymondal/system-design-inmemory-key-value-with-transaction-0a6df97ccce1

Round 1[Hire]: Design Excel Sheet : https://leetcode.com/problems/design-excel-sum-formula/

Round 1[Strong Hire]: Design In-memory key-value
store [https://leetcode.com/discuss/interview-question/279913/key-value-store-with-transaction]

Round 2[Lean - Hire]: Design News Aggregator and feed
system. [Interviewer was not good; he started asking questions before I fully grasped what he was asking ... there are all kind of people who are Engineering Manager nowadays, I guess :)] [Tough luck]

Round 3[Hire]: Design Stack overflow API end-2-end and other questions related to designing etc.

* 1:30 minutes round
* Divided in 2 parts 45 minutes each. You need to code end to end API in 45 minutes with working and prod ready code.

HR came back with SDE-2 Designation [Down-levelled ... he is still trying though .. lets see] because of stupid system
design Interview and that freaking luck.

I said NO .. as I'm looking for only SDE-3 or higher positions.

Design an excel sheet where cells are like 'A1', 'B3' etc ( character-> column, digit->row)
where the content of a cell can be a number (2,-9,0) or an expression like '2+7', '4+9-8', 'A1+B3'

Clarified some things:

columns can go beyond [a-z] i.e: AA10 ,AZ23 etc are valid
there is not fixed size of excel sheet
expressions for cells can be mixed ( like 'A1+B3 +7 -9' but lets not get into that right now)
for now, need not worry about '* and / ' operators
Requirements:

running code [done]. all classes and impelmentations to be done by self
Solved the question with SOLID classes for excel and cells and described the solution for evaluating cell references
too, correctly.

Rejection reason: They were expecting my Expression Eval function to be written in a more consolidated way. I wrote it
as you'd go around this problem : https://leetcode.com/problems/basic-calculator-ii/description/ . Add to that you'd
need a recursive call as well for the cell ref situations

Sounded like a pretty gimmicky reason to reject when the code looked fine. I'd not really restructure the way I wrote
the eval function in a 38min interview where runnig the code is important. I could sense the interviewer was wanting me
to implement the Eval function separately, probably in another class( not sure, don't see the need for that here). I
went around separating the expressions and operators and then evaluating them as well, just to satisfy him, looks like
that was not it .

Similar problems: https://leetcode.com/problems/design-excel-sum-formula/description/



