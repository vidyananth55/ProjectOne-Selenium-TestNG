Feature: Flight booking page handling

@smoketest
Scenario: enter details, then search and handle pop up

Given user is on website home page
When I search with INDIA and enter details
Then Handle alert




Scenario Outline: enter details, assert and switch window

Given user is on website home page
When I search with <country> and enter details
Then handle pop up and enter <no of passengers> members
And switch between handles

Examples:
|country| no of passengers |
|India  | 3                |
|China  | 4                |
