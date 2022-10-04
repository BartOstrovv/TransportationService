package original.transportationservicesapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeliveryNotContainsOfferException extends RuntimeException{
    public DeliveryNotContainsOfferException(String message) {
        super(message);
    }

    public DeliveryNotContainsOfferException(String entityName, Long id, Long offerID) {
        super(entityName + "with id " + id + " is not contains offer with id " + offerID);
    }

    public DeliveryNotContainsOfferException(String entityName, Long id) {
        super(entityName + "with id " + id + " is not contains confirmed offers");
    }
}
