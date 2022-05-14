package validator;

/**
 * The class contains method to validate customer data
 *
 * @author Le Viet Hoang
 */
public class CustomerValidator {
    /**
     * This method check if inputted data Customer name is not empty or null
     *
     * @param (name) customer name to validate
     * @return true if not valid false if is valid
     */
    public boolean isInvalidName(String name){
        return (name.equals(null) || name.isEmpty());
    }

    /**
     * This method check if inputted data Customer phone is valid of not
     *
     * @param (phone) customer phone number to validate
     * @return true if not valid false if is valid
     */
    public boolean isValidPhone(String phone){
        try {
            Integer.parseInt(phone);  /* not a string of number */
        }catch (Exception e){
            return false;
        }
        if(phone.length()!=10){  /* not has 10 characters */
            return false;
        }
        if(phone.equals(null) || phone.isEmpty()){  /* not empty */
            return false;
        }
        String reg = "((09|03|07|08|05)([0-9]{8}))";
        return phone.matches(reg);  /* is valid phone number or not */
    }
}
