# Shortest path in a Binary Maze

## Given a MxN matrix where each element can either be 0 or 1. We need to find the shortest path between a given source cell to a destination cell. The path can only be created out of a cell if its value is 1.
## Expected time complexity is O(MN)

## The idea is inspired from Lee algorithm and uses BFS.

### This question can be twisted a bit if we introduce some more elements not only 0 or 1 but suppose 2 also..and you have to reach 2 with only traversing 1s......Approach is going to be same for all these question......just keep in mind this BFS approach.......and how we traverse all the 8 directions in matrix

#### Checklist of things we need in this question : 

- Point class having x , y co-ordinates
- Node class having point and distance from source
- Visited array/map to keep track of visited nodes
- Queue to implement BFS
- Direction arrays to traverse all 8 directions ...can be twisted if allowed directions to traverse changes :
	- rows[] = {0,0,1,-1}
	- cols[] = {1,-1,0,0}
 
