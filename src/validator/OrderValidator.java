package validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class contains method to validate customer data
 *
 * @author Le Viet Hoang
 */
public class OrderValidator {

    /**
     * This method check if inputted data: order date is either valid or not in the future
     *
     * @param (date) customer name to validate, type: string
     * @return true if valid false if not valid
     */
    public boolean isValidDate(String date){
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date currentDate = new Date();
            if(sdf.parse(date).after(currentDate)){
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * This method check if inputted data: order date is either valid or not in the future
     *
     * @param (orderAmount) number of product ordered, type: int
     * @param (oldAmount) actual stock left in shop, type: int
     * @return true if valid false if not valid
     */
    public boolean isValidAmount(int orderAmount,int oldAmount){
        if (orderAmount==0){
            return false;
        }
        return oldAmount-orderAmount>=0;
    }
}
