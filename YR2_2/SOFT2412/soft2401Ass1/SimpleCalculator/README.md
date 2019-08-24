# Group12Assignment
this is the readme file for group 12 assignment.

the project was implemented in java, using eclipse IDE. Test cases are based on Junit library.

it was built with gradle, after executing the gradle build, test, jar commands, the corresponding jar file was successfully created, and the test was run successfully.

jenkins was used for the automation of the project.



to build the project yourself: 

1. clone this repository with command 'git clone  <url>' to your local machine.

2. after you have successfully cloned this repository, back up the build.gradle file.

3. run gradle in the command line, type in command 'gradle init', to initiate the workspace for gradle build.

4. replace the newly generated build.gradle file with the backed up file.

5. run gradle command 'gradle build' to build the project, after the success, you can try other commands like 'gradle jar' to create packed jar file for the project, and 'gradle test' to run tests for the project, and check the result log produced.

6. after setting up the gradle correctly, open up jenkins. create a new project, and set up the config correctly. link the jenkins project towards the repository, providing correct github account.

7. change the action before build parameter to open command prompt, and set up the gradle commands in the action after build parameter including building and testing, save the configuration.

8. click build now button, and the project will start building on itself.
