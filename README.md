# Timelines Application
An Application that retrieves the specified number of timelines for a user.

## How to Run
Firstly, ensure you have java and maven installed.

1. Clone the exisiting repository on your local machine with Git Bash or Command Prompt

2. Navigate to the root directory of the project and install project dependencies:
```bash
mvn install
```

3. Run the following command to run the tests:
```bash
mvn test
```

4. Run the following commands to compile and run the project:
```bash
mvn compile
mvn exec:java
```

5. After the application has started, enter a user id and the length of timeline to retrieve. E.g 989489610 5