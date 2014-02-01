-- PIG Test
A = LOAD 'test-input.txt' USING PigStorage;

-- If we would like to print into screen:
-- DUMP A; 

-- Storing into a file
STORE A into 'test-output';
