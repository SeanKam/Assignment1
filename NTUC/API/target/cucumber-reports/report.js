$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/features/NTUCAssignment.feature");
formatter.feature({
  "name": "NTUC Income QA Tasks",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@NTUC_Assignment"
    }
  ]
});
formatter.scenario({
  "name": "Get and Print User information from GitHub",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@NTUC_Assignment"
    },
    {
      "name": "@Task1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "initialize API environment",
  "keyword": "Given "
});
formatter.match({
  "location": "Automation.setupAPIEnvironment()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I specify the username of a GitHub User in CLI",
  "keyword": "Given "
});
formatter.match({
  "location": "Automation.getUserFromGitHub()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Send GET request to get this user information",
  "keyword": "When "
});
formatter.match({
  "location": "Automation.getGitHubUser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Above information of the user is printout",
  "rows": [
    {
      "cells": [
        "gitHub_Username",
        "login"
      ]
    },
    {
      "cells": [
        "gitHub_name",
        "name"
      ]
    },
    {
      "cells": [
        "gitHub_AccountCreatedOn",
        "created_at"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "Automation.printUserInfo(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Send GET request to get this user Repositories",
  "keyword": "When "
});
formatter.match({
  "location": "Automation.getGitHubUserAllRepo()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Above information of each repository belonging to user is printout",
  "keyword": "Then "
});
formatter.match({
  "location": "Automation.getGitHubUserRepoRequest()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify stars value of a specific repository belonging to user in Task1",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@NTUC_Assignment"
    },
    {
      "name": "@Task2"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I navigate to GitHub main page",
  "rows": [
    {
      "cells": [
        "webURL",
        "http://github.com/"
      ]
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "Automation.navigateToGitHubPage(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for the same user and navigate to his Repositories page",
  "keyword": "When "
});
formatter.match({
  "location": "Automation.navigateToUserRepositoriesPage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I navigate to the Repository that is returned FIRST in the list in TASK 1",
  "keyword": "And "
});
formatter.match({
  "location": "Automation.clickRepository()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I verify the star count shown in webpage is equal to value returned by the API",
  "keyword": "Then "
});
formatter.match({
  "location": "Automation.verifyStarCountValue()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});