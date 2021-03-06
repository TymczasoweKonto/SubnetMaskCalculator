import java.util.regex.Pattern;

public class Methods 
{
	
	
	public static String getCIDR (String subnetMask)
	{
		int ones = 0;
		
		String[] subnetChoppedMask = subnetMask.split(Pattern.quote("."));
		Integer[] subnetIntMask = new Integer[4];
		String[] binaryChoppedMask = new String[4];
		
		
		for (int i = 0; i < 4; i++)
		{
			
				subnetIntMask[i] = Integer.parseInt(subnetChoppedMask[i]);
				binaryChoppedMask[i] = Integer.toBinaryString(subnetIntMask[i]);
				if (binaryChoppedMask[i].length()<8)
				{
					for (int j = binaryChoppedMask[i].length(); j < 8; j++)
					{
						binaryChoppedMask[i] = "0" + binaryChoppedMask[i];
					}
				}
			
		}
		
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < binaryChoppedMask[i].length(); j++)
			{
				
				if(binaryChoppedMask[i].charAt(j)=='1') 
				{
					ones++;
				}
			}
		}
		
		StringBuilder CIDR = new StringBuilder("");
		
	
		CIDR.append(Integer.toString(ones));
		
		return CIDR.toString();
	}
	
	
	public static String amountOfHosts(String CIDR)
	{
		Double amount = Math.pow(2, 32 - Integer.parseInt(CIDR)) - 2;
		long temp = Math.round(amount);
		String amountString = Long.toString(temp);
		
		return amountString;
	}
	
	
	/**
	 * Function returns CIDR (without "/" character) of a mask which covers desired amount of hosts.
	 * @param hostAmount
	 * @return
	 */
	public static String whichMask (String hostAmount)
	{
		Double[] hostValues = new Double[31];
		
		for (int i = 1; i < 31; i++)
		{
			hostValues[i] = Math.pow(2,  32 - (i)) - 2;
		}
		
		for (int i = 30; i > 0; i--)
		{
			if (Integer.parseInt(hostAmount) <= hostValues[i])
			{
				int CIDR = i;
				return Integer.toString(CIDR);
			}
		}
		
		return "No mask found for provided input!";
	}
	
	/**
	 * Converts given CIDR to a subnet mask.
	 * @param CIDR
	 * @return
	 */
	
	public static String CIDRtoMask(String CIDR)
	{
		
		if (CIDR.charAt(0)=='/')
		{
			CIDR = CIDR.substring(1,CIDR.length());
		}
		
		if (Integer.parseInt(CIDR) <= 0 || Integer.parseInt(CIDR) > 30)
		{
			return "Invalid input!";
		}
		
		int amountOfOnes = Integer.parseInt(CIDR);
		
		StringBuilder maskConstructor = new StringBuilder("");
		
		for (int i = 0; i < amountOfOnes; i++)
		{
			maskConstructor.append('1');
		}
		
		for (int i = maskConstructor.length(); i < 32; i++)
		{
			maskConstructor.append('0');
		}
		
		
		
		String[] choppedMask = new String[4];
		
		
		for (int i = 0; i < 4; i++)
		{
			choppedMask[i] = maskConstructor.substring(i*8, i*8+8);
		}

			
		int[] choppedIntMask = new int[4];
		
		for (int i = 0; i < 4; i++)
		{
			choppedIntMask[i] = Integer.parseInt(choppedMask[i], 2);
		}
		
				
		StringBuilder finishedMask = new StringBuilder("");
		
		for (int i = 0; i < 4; i++)
		{
			finishedMask.append(Integer.toString(choppedIntMask[i]));
			if (i != 3)
			{
				finishedMask.append('.');
			}
		}
		
		return finishedMask.toString();
		
	}
	
	public static String binaryAddition(String s1, String s2) {
	    if (s1 == null || s2 == null) return "";
	    int first = s1.length() - 1;
	    int second = s2.length() - 1;
	    StringBuilder outputSum = new StringBuilder();
	    int carry = 0;
	    while (first >= 0 || second >= 0) {
	        int sum = carry;
	        if (first >= 0) {
	            sum += s1.charAt(first) - '0';
	            first--;
	        }
	        if (second >= 0) {
	            sum += s2.charAt(second) - '0';
	            second--;
	        }
	        carry = sum >> 1;
	        sum = sum & 1;
	        outputSum.append(sum == 0 ? '0' : '1');
	    }
	    if (carry > 0)
	        outputSum.append('1');

	    outputSum.reverse();
	    return String.valueOf(outputSum);
	}

	public static String calculateSubnetAddress(String inputAddress, String subnetMask)
	{
	
		

			String[] splittedAddress = inputAddress.split(Pattern.quote("."));
			Integer[] splittedIntAddress = new Integer[4];
			for (int i = 0; i < splittedAddress.length; i++)
			{
				splittedIntAddress[i] = Integer.parseInt(splittedAddress[i]);
			}
			
			String[] splittedMask = subnetMask.split(Pattern.quote("."));
			Integer[] splittedIntMask = new Integer[4];
			for (int i = 0; i < splittedMask.length; i++)
			{
				splittedIntMask[i] = Integer.parseInt(splittedMask[i]);
			}
			
			
			
			StringBuilder[] splittedBinaryAddress = new StringBuilder[4];
			StringBuilder[] splittedBinaryMask = new StringBuilder[4];
			
			for (int i = 0; i < 4; i++)
			{
				splittedBinaryAddress[i] = new StringBuilder("");
				splittedBinaryMask[i] = new StringBuilder("");
				
			}
			
			
			for (int i = 0; i < 4; i++)
			{
				splittedBinaryAddress[i].append(Integer.toBinaryString(splittedIntAddress[i]));
				splittedBinaryMask[i].append(Integer.toBinaryString(splittedIntMask[i]));
				
				if(splittedBinaryMask[i].length()<8)
				{
					splittedBinaryMask[i].reverse();
					for (int j = splittedBinaryMask[i].length(); j<8;j++)
					{
						
						splittedBinaryMask[i].append('0');
					}
					splittedBinaryMask[i].reverse();
				}
				
				if(splittedBinaryAddress[i].length()<8)
				{
					splittedBinaryAddress[i].reverse();
					for (int j = splittedBinaryAddress[i].length(); j<8;j++)
					{
						
						splittedBinaryAddress[i].append('0');
					}
					splittedBinaryAddress[i].reverse();
				}
			}
			
			
			StringBuilder[] outputBinary = new StringBuilder[4];
			for (int  i = 0; i < 4; i++)
			{
				outputBinary[i] = new StringBuilder("");
			}
			
			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < splittedBinaryAddress[i].length(); j++)
				{
					if (splittedBinaryMask[i].charAt(j)=='1')
					{
						outputBinary[i].append(splittedBinaryAddress[i].charAt(j));
					}
					else
					{
						outputBinary[i].append("0");
					}
				}
			}
			
			Integer[] outputInt = new Integer[4];
			for (int  i = 0; i < 4; i++)
			{
				outputInt[i] = Integer.parseInt(outputBinary[i].toString(), 2);
			}
			
			StringBuilder outputSubnetAddress = new StringBuilder("");
		
			for (int i = 0; i < 4; i++)
			{
				outputSubnetAddress.append(Integer.toString(outputInt[i]));
				if (i!=3)
				{
					outputSubnetAddress.append(".");
				}
			}
			return outputSubnetAddress.toString();
		
		
	
		
		
		
	}
	
	public static String calculateBroadcastAddress(String subnetAddress, String subnetMask)
	{
		
		String[] splittedAddress = subnetAddress.split(Pattern.quote("."));
		Integer[] splittedIntAddress = new Integer[4];
		for (int i = 0; i < splittedAddress.length; i++)
		{
			splittedIntAddress[i] = Integer.parseInt(splittedAddress[i]);
		}
		
		String[] splittedMask = subnetMask.split(Pattern.quote("."));
		Integer[] splittedIntMask = new Integer[4];
		for (int i = 0; i < splittedMask.length; i++)
		{
			splittedIntMask[i] = Integer.parseInt(splittedMask[i]);
		}
		
		
		
		StringBuilder[] choppedAddress = new StringBuilder[4];
		StringBuilder[] choppedMask = new StringBuilder[4];
		
		for (int i = 0; i < 4; i++)
		{
			choppedAddress[i] = new StringBuilder("");
			choppedMask[i] = new StringBuilder("");
	
		}
		
		for (int i = 0; i < 4; i++)
		{
			choppedAddress[i].append(Integer.toBinaryString(splittedIntAddress[i]));
			choppedMask[i].append(Integer.toBinaryString(splittedIntMask[i]));
			
			
			if(choppedMask[i].length()<8)
			{
				choppedMask[i].reverse();
				for (int j = choppedMask[i].length(); j<8;j++)
				{
					
					choppedMask[i].append('0');
				}
				choppedMask[i].reverse();
			}
			
			if(choppedAddress[i].length()<8)
			{
				choppedAddress[i].reverse();
				for (int j = choppedAddress[i].length(); j<8;j++)
				{
					
					choppedAddress[i].append('0');
				}
				choppedAddress[i].reverse();
			}
			
		}
		
		
		StringBuilder address = new StringBuilder("");
		StringBuilder mask = new StringBuilder("");
		StringBuilder newMask = new StringBuilder("");
	
		
		
		for (int i = 0; i < 4; i++)
		{
			address.append(choppedAddress[i]);
			mask.append(choppedMask[i]);
			
		}
		
		for (int i = 0; i < mask.length(); i++)
		{
			if(mask.charAt(i)=='0')
			{
				newMask.append('1');
			}
			else if (mask.charAt(i)=='1')
			{
				newMask.append('0');
			}
		}
		
		
		
		
		String outputBinary = Methods.binaryAddition(address.toString(), newMask.toString());
		StringBuilder[] chopped = new StringBuilder[4];
		for (int j = 0; j < 4; j++)
		{
			chopped[j] = new StringBuilder("");
		}
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				chopped[i].append(outputBinary.charAt(i*8+j));
			}
		}
		
		Integer[] choppedInt = new Integer[4];
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				choppedInt[i] = Integer.parseInt(chopped[i].toString(),2);
			}
		}
		
		StringBuilder output = new StringBuilder("");
		
		for (int i = 0; i < 4; i++)
		{
			
			output.append(Integer.toString(choppedInt[i]));
			if (i!=3)
			{
				output.append(".");
			}
		}
		
		
		return output.toString();
		
	}
	
}
