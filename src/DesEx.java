
public class DesEx {
	
	public static void main(String[] args) {
		DesEx ex=DesEx();
		Key key= new Key();
		Encode en = new Encode();
		String chiper="";
		String plain="";
		
		long mykey = 0x0123456789abcdefL;
		long[] subkey=Key.MakeSubKey(mykey);
		
		chiper=Encode.Crypto("1111111111111111111111111111111111111111111111111111111111111111",subkey);
		System.out.println(chiper);
		
		plain=Decode.Decrypt(chiper,subkey);
		System.out.println(plain);
	}
	private static DesEx DesEx() {
		// TODO Auto-generated method stub
		return null;
	}
}
