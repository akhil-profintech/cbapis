package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CollectionTransactionDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollectionTransactionDetailsDTO.class);
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO1 = new CollectionTransactionDetailsDTO();
        collectionTransactionDetailsDTO1.setId(1L);
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO2 = new CollectionTransactionDetailsDTO();
        assertThat(collectionTransactionDetailsDTO1).isNotEqualTo(collectionTransactionDetailsDTO2);
        collectionTransactionDetailsDTO2.setId(collectionTransactionDetailsDTO1.getId());
        assertThat(collectionTransactionDetailsDTO1).isEqualTo(collectionTransactionDetailsDTO2);
        collectionTransactionDetailsDTO2.setId(2L);
        assertThat(collectionTransactionDetailsDTO1).isNotEqualTo(collectionTransactionDetailsDTO2);
        collectionTransactionDetailsDTO1.setId(null);
        assertThat(collectionTransactionDetailsDTO1).isNotEqualTo(collectionTransactionDetailsDTO2);
    }
}
