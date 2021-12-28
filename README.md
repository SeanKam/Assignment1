---READ ME---

The assignment is implemented by Java Maven project with BDD framework. The SW tools used includes Rest Assured API (for task 1) and Selenium WebDriver (for task 2). 
It has a feature file consisting of 2 scenarios that execute the tasks as stated in 'Income QA Tasks.docx'.

To Run the assignment, please follow the steps below:
1. Import the Maven project to e.g. Eclipse IDE (If you face issue during importing, please refer to document How to setup project in Eclipse.docx)

2. Open a Command Prompt and navigate to the Project directory

3. From the Command Prompt, run the command : mvn clean test -Duser=<github_username>

e.g. mvn clean test -Duser=torvalds or mvn clean test -Duser=defunkt etc. If no user parameter is specified i.e. command run as 'mvn clean test', the assignment will default to run as mvn clean test -Duser=torvalds

Step 3 will run task 1 followed by task 2. 

NOTE : Task 2 will navigate to Repository webpage of the FIRST repository record that is returned from Get Repositories API in Task 1. 
It will then assert the star value displayed in the webpage is the SAME as the star value returned by Task 1.



