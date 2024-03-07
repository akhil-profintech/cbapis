package in.pft.apis.creditbazaar.gateway.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatedResponse
{
    private String id;
    private String validatedTimeStamp;
    private boolean valid;
    private PlacedOfferDTO placedOfferDTO;
}
