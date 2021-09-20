package JDBCWorkspace.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.sql. *  --> Herseyi tamamen import et. (Kisayol)

public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// 1) Ilgili driver yüklemeliyiz. (ORN: TV'nin fisini tak)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		// 2) Baglanti olusturmaliyiz. (ORN: Netflix bagla)
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr"); 
		
		
		// 3) SQL komutlari icin bir Statement nesnesi olusturalim. (ORN: Alan olusturduk, kumandada kanal ayarlama)
		Statement st = con.createStatement();
		
		
		// 4) SQL ifaderi yazabilir ve calistirabiliriz. (ORN: Kumandada istedigimiz komuta basmak. Ses acmak, kanal degistirmek gibi)
		// (Personel tablosundaki Personel_id'si 7369 olan personelin adini, maasini sorgula)
		ResultSet isim = st.executeQuery("SELECT Personel_isim, maas FROM Personel WHERE personel_id=7369");
		
		
		// 5) Sonuclari aldik.
			while (isim.next()) {
				System.out.print("Personel isim : " + isim.getString("personel_isim") + "Maas : " + isim.getInt("maas"));
			}
			
		// 6) Olusturulan nesneleri bellekten kaldiralim.
			con.close();
			st.close();
			isim.close();
	}

}