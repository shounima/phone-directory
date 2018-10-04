public class phoneEntry {
		
		private String firstName;
		private String lastName;
		private String phone;
		
	/*pre-condition: this is a constructor and needs three String parameters
	 * post-condition: this will make an object called phoneEntry with three String parameters
	 */
		
		phoneEntry(String firstName, String lastName, String phone){
			this.firstName = firstName;
			this.lastName = lastName;
			this.phone = phone;
		}

	/*pre-condition: needs a String parameter
	 * post-condition: sets the parameter as the first name 
	 */		
		public void setFirstName(String first) {
			firstName = first;
		}
		
	/*pre-condition: needs a String parameter
	 * post-condition: sets the parameter as the last name
	 */	
		public void setLastName(String last) {
			lastName = last;
		}
		
	/*pre-condition: needs a String parameter
	 * post-condition: sets the parameter as the phone number
	 */
		public void setPhoneNumber(String num) {
			phone = num;
		}
		
	/*pre-condition: needs the variable firstName to exist
	 * post-condition: returns the variable firstName
	 */		
		public String getFirstName() {
			return firstName;
		}
		
	/*pre-condition: needs the variable lasttName to exist
	 * post-condition: returns the variable lastName
	 */
		public String getLastName() {
			return lastName;
		}
		
	/*pre-condition: needs the variable phone to exist
	 * post-condition: returns the variable phoneNumber
	 */
		public String getPhoneNumber() {
			return phone;
		}
	
	/*pre-condition: needs the variable firstName, lastName and phone to exist
	 * post-condition: formats the phoneEntry constructor and returns it
	 */	
		public String toString() {
			return firstName + "\t\t\t" + lastName + "\t\t\t" + phone;
		}
		
		
}