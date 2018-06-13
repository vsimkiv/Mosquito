
# Mosquito
Mosquito is educational java web project which is developed by students of group IF-086-Java at SoftServe IT Academy.

  
## Running project locally:  
  
1. clone the project;  
2. open project in IDE;  
3. in the file *Mosquito\mosquito-controller\src\main\resources\db.properties* change properties (*MYSQL_DB_URL*,  *MYSQL_DB_USERNAME* and *MYSQL_DB_PASSWORD*) to your own;  
4. run maven goal `mvn clean package` for execute database-scripts and create war-package;  
5. run the project in the local server (for example *Tomcat*)  

# Style Guide  
This guide highlights the most important and most common conventions for writing code.  

##   
  
### Formatting  
* Four spaces should be used as the unit of indentation (no tabs)  
* Don't write lines longer than 120 characters.  
* Avoid writing methods longer than 30 lines.  
* Don't write classes that are longer than 150 lines (not including comments).  
* Use empty line between method definitions.  
* Use empty line between the local variables in a method and its first statement  
* Don't use extra empty lines at the end and beginning of class/method definitions.  
* Don't include spaces before () or [] when writing or calling methods.  
* Don't include a newline before {. Don't use C# style.  
* Use spaces around operators, except unary operators, such as !.  
  
### Naming  
* Use lowerCamelCase for variable/method names and UpperCamelCase for class/interface names.  
* Constants should be all uppercase and with underscores, for wxample: MY_CONSTANT.  
  
### Organization  
  
The order of things inside classes should be:  
  
1. Static variables  
2. Instance variables  
3. Constructors  
4. Static methods  
5. Instance methods  
6. Private static methods  
7. Private instance methods  
  
### Comments  
* Write high level comments above classes or methods explaining what the code does, not how it does it.  
* Use Javadoc comments with tags: @return, @param, @see. (http://www.oracle.com/technetwork/articles/java/index-137868.html)  
* Avoid writing single line comments.  
    
## SQL  
  
### General  
* Use consistent and descriptive identifiers and names.  
* Ensure the name is unique and does not exist as a reserved keyword.  
  
### Tables  
* Use singular names for tables.  
* Never give a table the same name as one of its columns and vice versa.  
* Tables must have at least one key to be complete and useful.  
* For FOREIGN KEY constraints use such convention: `[referencing table name]_[referenced table name]_fk`  
  
### Columns  
* Use the singular name.  
* Use CamelCase for names if it's necessary (it will be easy to support Java code).  
  
### Scripts  
* Always use uppercase for the reserved keywords like SELECT and WHERE.  
* Include in scripts newlines/vertical space for better understanding of code. Newlines should be used for any query that is at all complex or longer than 72 characters.  
* Never use `*` operator for queries.  
  
## Git (Github)  
#### How to commit:  
  
1. Before making commits switch to `dev` branch and do `git pull origin dev` to load the latest commits from central repository.  
2. Create your own branch for feature by command `git checkout -b myfeature`.  
3. Make commits.  
4. After finishing merge your branch `myfeature` to `dev` branch. For this switch to `dev` branch and then do `git merge --no-ff myfeature`.  
5. Push your local branch `dev` to central repository by command `git push origin dev`.  
  
#### General rules:  
* Don`t do pull requests (except you want to discuss your commits with everybody in the team or you want to get the feedback to the code).  
* Commit only completed functional (don`t commit minor changes).  
* Review code of other team members on Github, comment it and give suggestions how to solve problems.  
* If someone added comment on code review you need to answer to this comment (are you agree or not).  
* If you agree with comment fix it in next commit.  
  
#### Commit messages:  
* Write descriptive and understandable messages for commits.  
* Use imperative, present tense in messages: "change", "add", "fix", not "changed", “added”, "fixed".  
* Don't capitalize first letter in messages.  
* No dot (.) at the end of message.  
* Use such style for writing messages: `what to do + for what entity + details(optional)`.  
For example: `create class UserRepoImpl, override and implement all methods of interface UserRepo`
