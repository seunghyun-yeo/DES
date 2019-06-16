
public class Decode extends Encode {
	public String Decrypt(String input, long[] subkey)
	{
		Feistel feistel= new Feistel();
		String afterip = "";
		String chiper="";
		
		for(int i=0;i<ip.length;i++) {
		
			afterip=afterip+input.charAt(ip[i]-1);
		}
		
		String afterip_1_str = String.format("%32s", afterip.substring(0,32) ).replace(' ', '0');
		
		//for(int i=0; i<afterip_1_str.length();i=i+4)
		//System.out.print(afterip_1_str.substring(i,i+4)+" ");
		//System.out.println();
		
		String afterip_2_str = String.format("%32s", afterip.substring(32,64) ).replace(' ', '0');

		//for(int i=0; i<afterip_2_str.length();i=i+4)
		//System.out.print(afterip_2_str.substring(i,i+4)+" ");
		//System.out.println();
		
		long afterip_1=(long)(Long.parseLong(afterip_1_str,2)&0xffffffff);
		long afterip_2=(long)(Long.parseLong(afterip_2_str,2)&0xffffffff);
		
		long tmp=0;
		long afterfiestal=0;
		
		for(int i=15; i>=0; i--) {
		
			
			afterfiestal = feistel.dofeistel(afterip_2, subkey[i]);
			//System.out.println("f : "+String.format("%32s", Integer.toBinaryString(afterfiestal)).replace(' ', '0'));
			tmp = afterip_2;
			afterip_2 = afterfiestal ^ afterip_1;
			//System.out.println("r1: "+String.format("%32s", Integer.toBinaryString(afterip_2)).replace(' ', '0'));
			afterip_1 = tmp;
		}

		long result=((long)afterip_2<<32) | afterip_1;
		String resultstr=String.format("%64s", Long.toBinaryString(result) ).replace(' ','0');
		for(int i=0; i<ipi.length;i++) {
			chiper=chiper + resultstr.charAt(ipi[i]-1);
		}
		//System.out.println("chiper" + chiper);
			return chiper;
	}
}
