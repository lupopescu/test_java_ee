package mag;

import java.sql.*;

public class magaz {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection database_link;
		// deschidem conectiunea;
		try {
			Class.forName("com.mysql.jdbc.Driver");// /creaza clasa jdbs in org
													// sqlite
			database_link = DriverManager
					.getConnection("jdbc:mysql://localhost/magazin?user=root");
			// cream tabel sql in university( create table) sa consta din
			// coloane
			// nume prenume
			// statment
			String query = "CREATE TABLE IF NOT EXISTS product ( name VARCHAR(20) , descriptie VARCHAR(20), pret INT, cantitate INT, expiredate YEAR  );"; // transmitem
																																							// interogarea
			Statement command = database_link.createStatement();

			command.executeUpdate(query);
			query = "INSERT INTO product VALUES ( 'pere' , 'alimentare', '6', '100', '2016' )";
			command.executeUpdate(query);
			// citim inregistrarile
			query = "SELECT * FROM product;";
			// pregatim un container pentru rez obtinutestudenti
			ResultSet product_list;
			product_list = command.executeQuery(query);
			// parcurgem lista returnata
			int n = 0;
			while (product_list.next()) {

				System.out.println("nume: " + product_list.getString("name"));
				System.out.println("descriprie: "
						+ product_list.getString("descriptie"));
				System.out.println("pret: " + product_list.getString("pret"));
				System.out.println("cantitate: "
						+ product_list.getString("cantitate"));
				System.out.println("anul expirarii: "
						+ product_list.getInt("expiredate"));
				n++;
			}
			System.out.println("total produse " + n);
			// inchidem conecsiunea
			command.close();
			database_link.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}