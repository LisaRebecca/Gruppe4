import java.sql.*;

public class SqlSelect {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bookstore?user=root&password=sequel");

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");

			while (rs.next()) {
				int isbn = rs.getInt("isbn");
				String title = rs.getString("title");
				int author_id = rs.getInt("author_id");
				int publisher_id = rs.getInt("publisher_id");
				String year_pub = rs.getString("year_pub");
				String description = rs.getString("description");

				System.out.format("%s, %s, %s, %s, %s, %s\n", isbn, title, author_id, publisher_id, year_pub, description);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}