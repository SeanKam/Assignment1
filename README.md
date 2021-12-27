---READ ME---

The assignment is a Java Maven project with BDD framework. It has a feature file consisting of 2 scenarios that implement the tasks as stated in 'Income QA Tasks.docx'.

To Run the assignment
1. Import the Maven project to e.g. Eclipse IDE

2. Open a Command Prompt and navigate to the Project directory

3. From the Command Prompt, run the command : mvn clean test -Duser=<github_username>

e.g. mvn clean test -Duser=torvalds or mvn clean test -Duser=defunkt etc. If no user parameter is specified i.e. command run as 'mvn clean test', the assignment will default to run as mvn clean test -Duser=torvalds

The above will run task 1 followed by task 2. 
NOTE : Task 2 will navigate to Repository webpage of the FIRST repository record that is returned from Get Repositories API in Task 1. It will then assert the star value displayed in the webpage is the SAME as the star value returned by Task 1.



