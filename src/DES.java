
public class DES {
	private long[] subkey;
	private Key key;
	private Encode en;
	private Decode de;
		
	DES(long mykey){
		
		key = new Key();
		subkey=key.MakeSubKey(mykey);
		en = new Encode();
		de = new Decode();
	
	}
	
	public String encode(String input)
	{

		String inputstr=toBinaryString(input);
		//System.out.println(inputstr);
		String result="";

		for(int i=0; i<inputstr.length();i=i+64)
		{
			result=result + en.Crypto(inputstr.substring(i,i+64), subkey);
		}
		//System.out.println("encode output : " + result);
		return toString(result);
	}
	
	public String decode(String input)
	{

		String inputstr=toBinaryString(input);
		//System.out.println("decode inpout : " + inputstr);
		String result="";

		for(int i=0; i<inputstr.length();i=i+64)
		{	
			result=result + de.Decrypt(inputstr.substring(i,i+64), subkey);
		}
		
		//System.out.println(result);
		return toString(result);
	}
	
	private String toBinaryString(String input)
	{
		String result="";
		
		for(int i=0; i<input.length();i++)
		{
			int temp=(int)input.charAt(i);
			result=result + String.format("%8s",Integer.toBinaryString(temp)).replace(' ', '0');
		}

		while(result.length()%64!=0)
			result= result+"00000000";

		return result;
	}
	
	private String toString(String input)
	{
		String result="";
		for(int i=0; i<input.length();i=i+8)
		{
			char temp=(char)Integer.parseInt(input.substring(i,i+8),2);
			result=result + temp;
		}

		return result;
	}
}
