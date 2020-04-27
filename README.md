# RPN Calculator.

## Reverse Polish Notation
In reverse Polish notation, the operators follow their operands; 
for instance, to add 3 and 4, one would write 3 4 + rather than 3 + 4. 
If there are multiple operations, operators are given immediately after their second operands; 
so the expression written 3 − 4 + 5 in conventional notation would be 
written 3 4 − 5 + in reverse Polish notation: 4 is first subtracted from 3, 
then 5 is added to it. 

## Algorithm
The Application uses Stack to keep current values and result of previous operations, 
the generic algorithm described below:
 
|  Input   |    Operations  |   Stack  |
|----------|:--------------|---------:|
|    1     |  add to stack |    1     |
|    2     |  add to stack |    1,2   |
|    +     |  + operator   |    3     |
|    4     |  add to stack |    3,4   |
|    *     |  * operator   |    12    |
|    3     |  add to stack |    12, 3 |
|    +     |  + operator   |    15    |


## Build
To build the application, install [Apache maven](http://maven.apache.org/) build tool and [java 8](https://docs.aws.amazon.com/corretto/latest/corretto-8-ug/downloads-list.html) or higher
- Clone this repository
````bash
git clone https://github.com/thesolwind/cli-rpn-calculator
````
- Go to the cli-rpn-calculator folder and build jar file
````bash
mvn package
````
## Run
Once the jar file built, run it with next command:
````bash
java -jar target/cli-rpn-calculator-1.0.0.jar
````
