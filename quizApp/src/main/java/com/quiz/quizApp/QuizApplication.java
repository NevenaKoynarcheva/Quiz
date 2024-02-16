package com.quiz.quizApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class QuizApplication {
	public static void main(String[] args) {
	Connection connection = null;
	Statement statement = null;
		try {
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "1234");
		statement = connection.createStatement();
		statement.executeQuery("SELECT count(*) FROM pg_database WHERE datname = 'questionDb'");
		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		int count = resultSet.getInt(1);

		if (count <= 0) {
			statement.executeUpdate("CREATE DATABASE questionDb");

		}
	} catch (SQLException e){

	} finally {
			try {
				if (statement != null) {
					statement.close();

				}
				if (connection != null) {

					connection.close();
				}
			} catch (SQLException e) {

			}
		}

		SpringApplication.run(QuizApplication.class, args);
	}

}
