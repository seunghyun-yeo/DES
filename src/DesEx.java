
public class DesEx {
	
	public static void main(String[] args) {
		DES des = new DES(0x0123456789abcdefL);
		String chiper="";
		String plain="";
		chiper=des.encode("Helloworld!!!!!!");
		System.out.println(chiper);
		plain=des.decode(chiper);
		System.out.println(plain);
		
	}
}
