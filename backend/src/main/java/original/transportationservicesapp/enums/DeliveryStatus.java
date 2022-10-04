package original.transportationservicesapp.enums;

import java.util.List;

public enum DeliveryStatus {
    CREATED, UNCONFIRMED_OFFERS, CONFIRMED_OFFER, ARRIVED, DONE;

    public static List<DeliveryStatus> afterAccepted() {
        return List.of(CONFIRMED_OFFER, ARRIVED, DONE);
    }
}
