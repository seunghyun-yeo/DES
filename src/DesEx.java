
public class DesEx {
	
	public static void main(String[] args) {
		DES des = new DES(0x6298817981796298L);
		
		String chiper=des.encode("Helloworld!!!!!!");
		String plain=des.decode(chiper);
		System.out.println(plain);
		chiper=des.encode("Helloworld");
		System.out.println(chiper);
		plain=des.decode(chiper);
		System.out.println(plain);
		chiper=des.encode("6298817");
		plain=des.decode(chiper);
		System.out.println(plain);
		chiper=des.encode("Java Programming assignment #5: DES (Data Encryption Standard) with Java");
		plain=des.decode(chiper);
		System.out.println(plain);
	}
}
