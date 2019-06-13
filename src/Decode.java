
public class Decode extends Encode {
	public String Decrypt(String input, long[] subkey)
	{
		
		String plain="";
		Feistel feistel= new Feistel();
		String afterip = "";
		
		for(int i=0;i<64;i++)
		{
			afterip=afterip+input.charAt(ip[i]-1);
		}
		String afterip_1_str = String.format("%64s", afterip.substring(0,32) ).replace(' ', '0');
		
		String afterip_2_str = String.format("%64s", afterip.substring(32,64) ).replace(' ', '0');
		
		int afterip_1=(int)(Long.parseLong(afterip_1_str,2)&0xffffffff);
		int afterip_2=(int)(Long.parseLong(afterip_2_str,2)&0xffffffff);
		
		int tmp, afterfiestal;
		
		for(int i=15; i>-1; i--)
		{
			
			afterfiestal = feistel.dofeistel(afterip_2, subkey[i]);
			tmp = afterip_2;
			afterip_2 = afterfiestal ^ afterip_1;
			afterip_1 = tmp;
		}

		long result=((long)afterip_2<<32) | afterip_1;
		
		String resultstr=String.format("%64s", Long.toBinaryString(result) ).replace(' ','0');
	
		for(int i=0; i<64;i++)
		{
			plain=plain + resultstr.charAt(ipi[i]-1);
		}
		return plain;
	}
}
