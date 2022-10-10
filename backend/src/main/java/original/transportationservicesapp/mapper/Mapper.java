package original.transportationservicesapp.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import original.transportationservicesapp.dto.*;
import original.transportationservicesapp.entity.*;


@org.mapstruct.Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface Mapper {
    CustomerDto toCustomerDto(Customer entity);
    @Mapping(target = "deliveries", ignore = true)
    Customer toCustomer(CustomerDto dto);

    @Mapping(target = "deliveries", ignore = true)
    Customer toCustomer(RegistrationCustomerDto dto);
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    void mergeCustomer(CustomerDto dto, @MappingTarget Customer entity);

    @Mapping(target = "status", defaultExpression = "java(original.transportationservicesapp.enums.DeliveryStatus.CREATED)")
    DeliveryDto toDeliveryDto(Delivery entity);
    @Mapping(target = "offers", ignore = true)
    @Mapping(target = "status", defaultExpression = "java(original.transportationservicesapp.enums.DeliveryStatus.CREATED)")
    Delivery toDelivery(DeliveryDto dto);
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "offers", ignore = true)
    void mergeDelivery(DeliveryDto dto, @MappingTarget Delivery entity);

    TransporterDto toTransporterDto(Transporter entity);
    @Mapping(target = "offers", ignore = true)
    Transporter toTransporter(TransporterDto dto);
    @Mapping(target = "offers", ignore = true)
    Transporter toTransporter(RegistrationTransporterDto dto);
    @Mapping(target = "offers", ignore = true)
    void mergeTransporter(TransporterDto dto, @MappingTarget Transporter entity);

    OfferDto toOfferDto(Offer entity);
    @Mapping(target = "delivery", ignore = true)
    @Mapping(target = "transporter", ignore = true)
    Offer toOffer(OfferDto dto);
    @Mapping(target = "transporter", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    void mergeOffer(OfferDto dto, @MappingTarget Offer entity);

    UserDto toUserDto(User entity);
    @Mapping(target = "role", defaultExpression = "java(original.transportationservicesapp.enums.Role.CUSTOMER)")
    User toUser(UserDto dto);
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "id", ignore = true)
    void mergeUser(UserDto dto, @MappingTarget User entity);

    @Mapping(target = "role", defaultExpression = "java(original.transportationservicesapp.enums.Role.CUSTOMER)")
    @Mapping(target = "created", expression = "java(java.time.ZonedDateTime.now())")
    User toUser(RegistrationTransporterDto dto);
    @Mapping(target = "created", defaultExpression = "java(java.time.ZonedDateTime.now())")
    ProfileDto toProfileDto(User entity);

    CustomerDtoShort toCustomerDtoShort(Customer entity);

    SecurityUserDto toSecurityUserDto(User entity);
}
