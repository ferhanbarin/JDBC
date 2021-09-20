package JDBCWorkspace.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc4DMLInsert {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr"); 
		
		Statement st = con.createStatement();
		
		
		ResultSet rs = st.executeQuery("SELECT * FROM Bolumler");
		
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
		}
		
		
		// ORNEK 1: Bolumler tablosuna yeni bir kayit (80, 'ARGE', 'ISTANBUL') ekleyelim ve eklenen kaydi teyit icin sorgulayalim.
		
		st.execute("INSERT INTO Bolumler VALUES(80, 'ARGE', 'ISTANBUL')");
		
		
		// ORNEK2: Bolumler tablosuna birden fazla yeni kayıt ekleyelim.
		
		// 1. YONTEM --> Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin yavas yapilmasina yol acar. 10000 tane veri kaydi yapildigi düsünüldügünde bu kötü bir yaklasimdir. 
		
		String[] sorgular = {"INSERT INTO bolumler VALUES(95, 'YEMEKHANE', 'ISTANBUL')",
                			 "INSERT INTO bolumler VALUES(85, 'OFIS','ANKARA')",
                			 "INSERT INTO bolumler VALUES(75, 'OFIS2', 'VAN')"};
		
		for (String each : sorgular) {
			st.executeUpdate(each);
		}
		
		
		// 2.YONTEM (addBath ve excuteBatch() metotlari ile)
		// addBatch method'u ile SQL ifadeleri gruplandirilabilir ve exucuteBatch() method'u ile veritabanina bir kere gonderilebilir. excuteBatch() method'u bir int [] dizi dondurur.  
		// Bu dizi her bir ifade sonucunda etkilenen satir sayisini gosterir. 
		
		String [] sorgular1 = {"INSERT INTO bolumler VALUES(81, 'YEMEKHANE2', 'MUS')",
                			   "INSERT INTO bolumler VALUES(82, 'OFIS3','ORDU')",
                			   "INSERT INTO bolumler VALUES(83, 'OFIS4', 'MUGLA')"};
		
		for (String each : sorgular1) {
			st.addBatch(each);
		}
		 	st.executeBatch();
		 
		 // 3.YONTEM --> batch method'uyla birlikte PreparedStatement kullanmak en efektif yöntemdir. Bir sonraki örnekte bunu gerceklestirecegiz.
		 	
		con.close();
		st.close();
		rs.close();
	
	}

}