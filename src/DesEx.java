
public class DesEx {
	
	public static void main(String[] args) {
		DES des = new DES(0x0123456789abcdefL);
		
		String chiper=des.encode("seunghyuseunghyu");
		String plain=des.decode(chiper);
		
		System.out.println(chiper);
		System.out.println(plain);
		
	}
}
