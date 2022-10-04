package original.transportationservicesapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class DeliveryAlreadyHaveFinalOfferException extends RuntimeException{
    public DeliveryAlreadyHaveFinalOfferException(String message) {
        super(message);
    }

    public DeliveryAlreadyHaveFinalOfferException(String entityName, Long id) {
        super(entityName + "with id " + id + " is already having a confirmed offer or is already transported");
    }
}
