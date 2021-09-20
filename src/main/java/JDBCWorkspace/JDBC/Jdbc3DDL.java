package JDBCWorkspace.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
	A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet) 
    	Döndurmeyen metotlar kullanilmalidir.(executeQuery kullanamayiz) Bunun icin JDBC'de 2 alternatif bulunmaktadir.  
    	1) execute() method'u 
    	2) excuteUpdate() method'u.  
    
	B)  - execute() metodu hertur SQL ifadesiyle kullanilabilen genel bir komuttur.
   		- execute(), Boolean bir deger döndürür. DDL islemlerin false dondururken, DML islemlerinde true deger döndürür.
       	- Ozellikle, hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi durumlarda tercih edilmektedir. 
     	   
     
	C) - executeUpdate() method'u ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
   	   - Bu islemlerde islemden etkilenen satir sayisini döndürür.
   	   - Ayrica, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 döndürür.
*/

public class Jdbc3DDL {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr"); 
		
		Statement st = con.createStatement();
		
		
		// ORNEK 1: Isciler adinda bir tablo olusturunuz id NUMBER(3), birim VARCHAR2(10), maas NUMBER(5).
		
		st.execute("CREATE TABLE Isciler" + " id NUMBER (3)," + " birim VARCHAR(10)," + " maas NUMBER(5),"); // st.executeUpdate("   ")
		
		System.out.println("Isciler tablosu olusturuldu.");
		
				
		
		// ORNEK 2: Isciler tablosunu kalici olarak siliniz.
		
		st.executeUpdate("DROP TABLE Isciler PURGE");
		
		System.out.println("Isciler tablosu silindi");
		
		
				
		// ORNEK 3: Isciler tablosuna yeni bir sütun {isim Varchar2(20)} ekleyiniz.
		
		st.executeUpdate("ALTER TABLE Isciler ADD isim VARCHAR2(20)");
		
		System.out.println("Isim sütunu eklendi.");
		
		
		
		// ORNEK 4: Isciler tablosundaki soyisim sütununu siliniz.
		
		st.execute("ALTER TABLE Isciler DROP COLUMN maas");
		
		System.out.println("Islem tamam.");
		
		
		
		// ORNEK 5: Isciler tablosunun adini calisanlar olarak degistiriniz.
		
		st.execute("ALTER TABLE Isciler RENAME TO Calisanlar");
		
		System.out.println("Islem yine tamam.");
	}

}