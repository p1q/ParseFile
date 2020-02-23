<h1 align="center">ParseFile v1.0 Beta</h1>

<p align="center"><img src="https://github.com/p1q/ParseFile/blob/master/src/main/resources/logo.jpg" title="InternetShopLogo" /></p>

## :page_facing_up: What's This?
<p>This is a Java console application, which can read text files, calculate some statistics and save all data into the database. The application uses JDBC as DAO layer implementation.</p>
<p>In current implementation it's possible to calculate longest word, shortest word, line length, quantity of words, quantity of non-space symbols, average word length, words' duplications for each line of the file, and for the whole file.</p>

## :nut_and_bolt: Tech Stack
- Platform: Java EE 8
- Project management tool: Maven
- Database: MySQL 5
- Logging: Log4j

## :rocket: Project Deployment
How to run the application with maven:
1. Install JDK or JRE latest version and set correct JAVA_HOME variable.
2. Install Git (distributed version control system).
3. Install and configure MySQL database.
   <br />Or you are free to use provided public server 178.136.201.61 with phpMyAdmin for testing purpose
   <br />(for your convenience all connection data are hardcoded). 
4. Run bundled init_db.sql file in your database to initialize it. This step is already done for provided MySQL server.
5. Go to a folder in your file system and clone project from GitHub into this folder with command
   <br /><code>git clone https://github.com/p1q/ParseFile.git</code>
8. That's all! You can run it.

## :warning: Please Note
While coding some assumptions were made because of possible twofold understanding of some task details:
- Opened file doesn't include very special symbols. The file will be cleaned of this symbols:  .,?!:;()@#$%&^<>'
  but if it has something else, in that case the statistics can be inaccurate. 
- Shortest and longest words can be "".
- Two words with different letter case are considered as different words (e.g. "Sample" and "sample").
- Line length for the whole file is considered as sum of all its lines.
- FileParser class is covered by unit test (JUnit4).
- Google Java Style is followed while coding.

Of cause, it's possible to amend the application according to customer's requirements. 

## :man: Author

👤 **Eugeny Prokop**

- LinkedIn: [@EugenyProkop](https://www.linkedin.com/in/eugeny-prokop)
- GitHub: [@EugenyProkop](https://github.com/p1q)

## :scroll: License

Copyright © 2020 [Eugeny Prokop](https://github.com/p1q).<br />
This project is MIT licensed. See the [License](https://github.com/p1q/ParseFile/blob/master/LICENSE) file.
