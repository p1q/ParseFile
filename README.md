<h1 align="center">Parse File v1.0 Beta</h1>

<p align="center"><img src="https://github.com/p1q/ParseFile/blob/master/src/main/resources/logo.jpg" title="InternetShopLogo" /></p>

## 🤔 What's this?
<p>This is a very basic functional e-commerce application, written in Java using Servlets and JSPs. It complies fully with the MVC design pattern, as Servlets are used as Controllers and JSPs take care of the Presentation (View). The Model is described by Entities, that are mapped on tables in a MySQL relational database. The application has 2 versions of DAO layer implementation: plain JDBC and Hibernate.</p>
<p>You are free to use it as a template for your own online shop software. In current implementation you can add, remove and modify goods, users, roles and orders. The application provides registration and login procedures, passwords are saved into the database as hashes to increase security. You can make orders by adding goods into user's bucket.</p>

## :nut_and_bolt: Tech Stack
- Platform: Java EE 8
- Project management tool: Maven 4
- Database: MySQL 5
- ORM: Hibernate 5
- View level: JSP/JSTL
- Servlet container: Tomcat
- Logging: Log4j

## :rocket: Project Deployment
1. Install JDK latest version and set correct JAVA_HOME variable
2. Install Git (distributed version control system)
3. Install an IDE
4. Install and configure Apache Tomcat (I'm using version 8)
5. Install and configure MySQL database
6. Run init_db.sql file in your database
7. Clone project from GitHub into your IDE as a maven project. Default admin account is "admin", password is "1".
8. That's all! You can run it.

## 🕺 Contribute

1.  [Fork](https://help.github.com/articles/fork-a-repo/) this repository to your own GitHub account and then [clone](https://help.github.com/articles/cloning-a-repository/) it to your local device
2.  Make the necessary changes
3.  Create your feature branch: `git checkout -b my-new-feature`
4.  Commit your changes: `git commit -am 'Add some feature'`
5.  Send a pull request to check them out

## :man: Author

👤 **Eugeny Prokop**

- LinkedIn: [@EugenyProkop](https://www.linkedin.com/in/eugeny-prokop)
- GitHub: [@EugenyProkop](https://github.com/p1q)

## :scroll: License

Copyright © 2019 [Eugeny Prokop](https://github.com/p1q).<br />
This project is MIT licensed. See the [License](https://github.com/p1q/InternetShop/blob/master/LICENSE) file.
