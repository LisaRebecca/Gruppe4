package data;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;

import Tools.Converter;

public class MockDBTest extends BasicJDBCTestCaseAdapter {

		public static void main(String[] args) {
			MockDatabase mdbDatabase = new MockDatabase();
			System.out.println(Converter.resultSetToTable(mdbDatabase.getRS()));
			
		}
	}