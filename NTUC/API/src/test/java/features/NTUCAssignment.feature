@NTUC_Assignment
Feature: NTUC Income QA Tasks

  @Task1
Scenario: Get and Print User information from GitHub
	Given initialize API environment
	Given I specify the username of a GitHub User in CLI
  When I Send GET request to get this user information
  Then Above information of the user is printout
  |gitHub_Username|login|
  |gitHub_name|name|
  |gitHub_AccountCreatedOn|created_at|
  When I Send GET request to get this user Repositories
  Then Above information of each repository belonging to user is printout
  
   @Task2
Scenario: Verify stars value of a specific repository belonging to user in Task1
	Given I navigate to GitHub main page
	|webURL|http://github.com/|
	When I search for the same user and navigate to his Repositories page
	And I navigate to the Repository that is returned FIRST in the list in TASK 1
	Then I verify the star count shown in webpage is equal to value returned by the API  