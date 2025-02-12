package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// Ensure empty strings are replaced with "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	//==============================================================================================================

	//OR method for the binary operation
	public static Binary or(Binary num1, Binary num2) {
		//the index of the LSB of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;

		//new string to place bits in one by one
		String num3="";

		//loop through all the digits
		while (ind1 >= 0 || ind2 >= 0) {
			//start at index of LSB, check if index=1. If not, then default is set to 0
			int bit1 = (ind1 >= 0 && num1.number.charAt(ind1) == '1') ? 1 : 0;
			int bit2 = (ind2 >= 0 && num2.number.charAt(ind2) == '1') ? 1 : 0;

			//perform the bitwise operation
			int orResult = bit1 | bit2;

			num3 = ((orResult ==0) ? "0" : "1") + num3; //add this bit to new number

			//go to next index
			if (ind1 >=0) ind1--;
			if (ind2 >=0) ind2--;


		}//end of while loop

		return new Binary(num3);

	}//end of OR method

	//AND method for the Binary Operation
	public static Binary and (Binary num1, Binary num2) {

		//index of the LSB
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;

		//new string to place result in
		String num3 = "";

		//loop through all values until you have reached the end of the index
		while (ind1 >= 0 || ind2 >=0) {
			//start at index of LSB, check if index=1, if not then default is 0
			int bit1 = (ind1 >=0 && num1.number.charAt(ind1) == '1') ? 1 : 0;
			int bit2 = (ind2 >=0 && num2.number.charAt(ind2) == '1') ? 1 : 0;

			//compute the operation
			int andResult = bit1 & bit2;

			num3 = ((andResult == 0) ? "0" : "1") +num3;

			//go to next index
			if (ind1 >=0) ind1--;
			if (ind2 >=0) ind2--;

		}//end of while loop

		return new Binary(num3);
	}//end of AND method

	//MULTIPLY method for Binary Operation
	public static Binary multiply (Binary num1, Binary num2) {
		//create new variable to store final answer
		Binary num3 = new Binary("0");

		//get length of binary numbers
		int length1 = num1.number.length();
		int length2 = num2.number.length();

		//loop through each bit of the second number
		for (int i=length2 - 1 ; i >=0 ; i--) {

			//if bit at position i is 1, then add num1 shifted by (length2 - 1 - i)
			if (num2.number.charAt(i) == '1') {
				//shift num1
				String shiftedNum1 = num1.number + "0".repeat (length2 - 1 - i);

				//add shifted num1 to the result
				num3 = Binary.add(num3, new Binary(shiftedNum1));
			}
		}

		return num3;
	}//end of multiply METHOD
}	
