package in.pft.apis.creditbazaar.gateway.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacedOfferValidationResponse
{
    private ValidatedResponse data;
    private String success;
    private String message;
}
