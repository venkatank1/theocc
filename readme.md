# Description
This application does the following things.
1. Read names from a given input file.
2. Sort the names.
3. Calculate the weight of each name using a pluggable algorithm.
4. Return the sum of weights of all names in the input.

# Components
## InputSourceReader
Pluggable input source reader. A input source reader reads the input from a given source.(For example file, web socket, database...). For this application the input is read from file.

## Mapper
A pluggable custom mapper component. 

## Scorable
A pluggable scoring algorithm for a given unit. In our case a unit a name.

## ScoringController
A general purpose orchestrator which could be configured with InputSourceRader, Mapper and Score in order to calclate the sum.

## FileInputScoringController
A specific scoring controller for file input source.

# Notes
. Unit tests are not included. I have manually tested. I just did not use any external library. Just want to keep the app simple. In real time development I generally create a bunch of unit test cases.
. Tested parallel processing. Did not really see a difference when the input size 18 times or less. of the given input size.
. Thought about using the memory efficiently. However did not really see a value add when input size is less than 12 times the given input size. Its just creating a overhead of I/O.