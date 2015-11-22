$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("UserProfile.feature");
formatter.feature({
  "id": "validate-user-registation-on-the-registration-page",
  "description": "As a CNBC User \r\nI should be able to register\r\nSo that I can manage my acccount profile",
  "name": "Validate user registation on the registration page",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 5378591822,
  "status": "passed"
});
formatter.scenario({
  "id": "validate-user-registation-on-the-registration-page;userprofileupdate;;2",
  "tags": [
    {
      "name": "@WIP",
      "line": 14
    }
  ],
  "description": "",
  "name": "UserProfileUpdate",
  "keyword": "Scenario Outline",
  "line": 26,
  "type": "scenario"
});
formatter.step({
  "name": "user is on CNBC home page",
  "keyword": "Given ",
  "line": 16
});
formatter.step({
  "name": "user selects login",
  "keyword": "When ",
  "line": 17
});
formatter.step({
  "name": "provides the login details \"old\"",
  "keyword": "And ",
  "line": 18
});
formatter.step({
  "name": "user updates profile \"cnbc\" \"user\" \"1980\" \"India\" \"0\" \"Technology/IT\" \"Other\" \"\u003c $50K\" \"\u003c20\" \"1\"",
  "keyword": "And ",
  "line": 19,
  "matchedColumns": [
    0,
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9
  ]
});
formatter.step({
  "name": "saves the \"profile\"",
  "keyword": "And ",
  "line": 20
});
formatter.step({
  "name": "\"profile\" changes are saved",
  "keyword": "Then ",
  "line": 21
});
formatter.step({
  "name": "user signs out",
  "keyword": "And ",
  "line": 22
});
formatter.match({
  "location": "CNBCUserProfileSteps.user_is_on_CNBC_home_page()"
});
formatter.result({
  "duration": 65084403355,
  "status": "passed"
});
formatter.match({
  "location": "CNBCUserProfileSteps.user_selects_login()"
});
formatter.result({
  "duration": 12582043445,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "old",
      "offset": 28
    }
  ],
  "location": "CNBCUserProfileSteps.provides_the_login_details(String)"
});
formatter.result({
  "duration": 29471809957,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "cnbc",
      "offset": 22
    },
    {
      "val": "user",
      "offset": 29
    },
    {
      "val": "1980",
      "offset": 36
    },
    {
      "val": "India",
      "offset": 43
    },
    {
      "val": "0",
      "offset": 51
    },
    {
      "val": "Technology/IT",
      "offset": 55
    },
    {
      "val": "Other",
      "offset": 71
    },
    {
      "val": "\u003c $50K",
      "offset": 79
    },
    {
      "val": "\u003c20",
      "offset": 88
    },
    {
      "val": "1",
      "offset": 94
    }
  ],
  "location": "CNBCUserProfileSteps.user_updates_profile(String,String,String,String,String,String,String,String,String,String)"
});
formatter.result({
  "duration": 68103965526,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "profile",
      "offset": 11
    }
  ],
  "location": "CNBCUserProfileSteps.saves_the(String)"
});
formatter.result({
  "duration": 8130144997,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "profile",
      "offset": 1
    }
  ],
  "location": "CNBCUserProfileSteps.changes_are_saved(String)"
});
formatter.result({
  "duration": 90710002,
  "status": "passed"
});
formatter.match({
  "location": "CNBCUserProfileSteps.user_signs_out()"
});
formatter.result({
  "duration": 8172935617,
  "status": "passed"
});
});