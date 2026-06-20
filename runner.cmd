javac TaskTracker.java
echo "Testing adding feature"
java TaskTracker add runner
java TaskTracker add Walker
java TaskTracker add Singer
java TaskTracker add Worker

echo "Testing show all"
java TaskTracker all

echo "testing move"
java TaskTracker move 0 DONE
java TaskTracker all
