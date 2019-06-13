
public class DesEx {
	
	public static void main(String[] args) {
		DES des = new DES(0x0123456789abcdefL);
		
		String chiper=des.encode("Helloworld!!!!!!");
		String plain=des.decode(chiper);
		System.out.println(plain);
		chiper=des.encode("Helloworld");
		plain=des.decode(chiper);
		System.out.println(plain);
		chiper=des.encode("6298817");
		plain=des.decode(chiper);
		System.out.println(plain);
		chiper=des.encode("F-box, Feistel Function");
		plain=des.decode(chiper);
		System.out.println(plain);
	}
}
