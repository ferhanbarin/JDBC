package JDBCWorkspace.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Jdbc5CRUD {
	
/*
	 Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir. PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir. Bunun icin; 
	   
	     1) Veri girisine uygun bir POJO(Plain Old Java Object) sinifi olusturulur.
	     2) POJO Class nesnelerini saklayacak bir collection olusturulur
	     3) Bir döngü ile kayitlar eklenir. 
	
	
    Tercih Nedenleri;
    1: Hizlidir, çoklu sorgulari bir seferde yollar.
    2: Hazırlanan deyimlerin en önemli avantaji SQL enjeksiyon saldirilarini önlemeye yardımci olmasidır.  
       SQL enjeksiyonu, SQL ifadelerinde istemci tarafindan saglanan verileri kullanan uygulamalardan kötü niyetli olarak yararlanma teknigidir. 
       Saldirganlar, özel hazirlanmıs dize girişi saglayarak SQL motorunu istenmeyen komutlari yürütmesi için kandirir, böylece kisitli verileri görüntülemek veya islemek için bir veritabanina yetkisiz erişim elde eder.
       SQL enjeksiyon tekniklerinin tümü, uygulamadaki tek bir güvenlik açığından yararlanır.
       Yanlıs dogrulanmis veya dogrulanmamiş dize degişmezleri, dinamik olarak oluşturulmuş bir SQL ifadesinde birleştirilir ve SQL motoru tarafindan kod olarak yorumlanir. 
       Hazirlanan ifadeler, müşteri tarafindan saglanan verileri her zaman bir parametrenin icerigi olarak ele alır ve asla bir SQL ifadesinin parcasi olarak kabul etmez.
 
 */

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr"); 
		
		Statement st = con.createStatement();
		
		
		// ORNEK1: Urunler adinda bir tablo olusturalim. id(NUMBER(3), isim VARCHAR2(10) fiyat NUMBER(7,2).
		
		st.execute("CREATE TABLE Urunler (" + " id number(3)," + " isim VARCHAR2(10)" + " fiyat NUMBER (7.2))");
		
		System.out.println("Tablo olusturuldu.");
		
		
		
		// ORNEK2: Urunler tablosuna asagidaki kayitlari toplu bir sekilde ekleyelim.
		
		List <Urun> kayitlar = new ArrayList<>();
		kayitlar.add(new Urun(101, "Laptop", 6500));
		kayitlar.add(new Urun(102, "PC", 4500));
		kayitlar.add(new Urun(103, "Telefon", 4500));
		kayitlar.add(new Urun(104, "Anakart", 1500));
		kayitlar.add(new Urun(105, "Klavye", 200));
		kayitlar.add(new Urun(106, "Fare", 100));
		
		PreparedStatement pst = con.prepareStatement("INSERT INTO Urunler VALUES(?, ?, ?)");
		
		for (Urun each : kayitlar) {
			pst.setInt(1, each.getId());
			pst.setString(2, each.getIsim());
			pst.setDouble(3, each.getFiyat());
			
			pst.addBatch();
		}
		
		pst.executeBatch();
		
		System.out.println("Islem Tamam.");
		
		st.close();
		con.close();
		pst.close();
		
	}

}