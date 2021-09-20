package JDBCWorkspace.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc2Query02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr"); 
		
		Statement st = con.createStatement();
		
		
		// ORNEK 1 - Bölümler tablosundaki tüm kayitlari listeleyen bir sorgu yaziniz.
		
		ResultSet Tablo1 = st.executeQuery("SELECT * FROM Bolumler");
		
		while (Tablo1.next()) {
			System.out.println(Tablo1.getString(1) + " " + Tablo1.getString(2) + " " + Tablo1.getString(3));
		}
		
			System.out.println("===========================================================================================================================================================================================");
			
		// ORNEK 2 - Satis ve muhasebe bölümlerinde calisan personelin isimlerini ve maaslarini, maas ters sirali olarak listeleyiniz.
			
		ResultSet Tablo2 = st.executeQuery("SELECT personel_isim, maas" + " FROM Personel" + " WHERE bolum_id IN (10,30)" + " ORDER BY maas DESC");
		
		while (Tablo2.next()) {
			System.out.println(Tablo2.getString(1) + " " + Tablo2.getInt(2));
		}
		
		System.out.println("===============================================================================================================================================================================================");
		
		// ORNEK 3 - Tüm bölumlerde calisan personelin isimlerini, bölüm isimlerini ve maaslarini, bölüm ve maas sirali listeleyiniz. NOT: Calisani olmasa bile bölüm ismi gosterilmelidir.
		
		ResultSet Tablo3 = st.executeQuery("SELECT bolum_isim, personel_isim, maas" + " FROM Bolumler B" + " LEFT JOIN Personel P" + " ON B.bolum_id = P.bolum_id" + " ORDER BY bolum_isim, maas");
		
		while (Tablo3.next()) {
			System.out.println(Tablo3.getString(1) + " " + Tablo3.getString(2) + " " + Tablo3.getInt(3));
		}
		
		System.out.println("===============================================================================================================================================================================================");
		
		// ORNEK 4 - Maasi en yüksek 10 kisinin bölümünü, adini ve maasini listeyiniz.
		
		ResultSet Tablo4 = st.executeQuery("SELECT bolum_isim, personel_isim, maas" + " FROM Personel P" + " FULL JOIN Bolumler B" + " ON B.bolum_id = P.bolum_id" + " ORDER BY maas DESC" + " FETCH NEXT 10 ROWS ONLY");
																																																
		while (Tablo4.next()) {
			System.out.println(Tablo4.getString(1) + " " + Tablo4.getString(2) + " " + Tablo4.getInt(3));
		}
		
		con.close();
		st.close();
		Tablo1.close();
		Tablo2.close();
		Tablo3.close();
		Tablo4.close();
	}

}