package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.Trade} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TradeDTO implements Serializable {

    private Long id;

    private String tradeUlidId;

    private String tradeRefNo;

    private String sellerGstId;

    private String buyerGstId;

    private String tradeAmount;

    private LocalDate invoiceDate;

    private String invoiceNumber;

    private String tradeDocType;

    private String tradeDocSource;

    private String tradeDocCredibility;

    private String tradeMilestoneStatus;

    private String tradeAdvancePayment;

    private String anchorTraderName;

    private String tradePartnerName;

    private Long invoiceTerm;

    private String acceptanceFromTradePartner;

    private String reasonForFinance;

    private String tradePartnerSector;

    private String tradePartnerLocation;

    private String tradePartnerGstComplianceScore;

    private FinanceRequestDTO financerequest;

    private AnchorTraderDTO anchortrader;

    private TradePartnerDTO tradepartner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeUlidId() {
        return tradeUlidId;
    }

    public void setTradeUlidId(String tradeUlidId) {
        this.tradeUlidId = tradeUlidId;
    }

    public String getTradeRefNo() {
        return tradeRefNo;
    }

    public void setTradeRefNo(String tradeRefNo) {
        this.tradeRefNo = tradeRefNo;
    }

    public String getSellerGstId() {
        return sellerGstId;
    }

    public void setSellerGstId(String sellerGstId) {
        this.sellerGstId = sellerGstId;
    }

    public String getBuyerGstId() {
        return buyerGstId;
    }

    public void setBuyerGstId(String buyerGstId) {
        this.buyerGstId = buyerGstId;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getTradeDocType() {
        return tradeDocType;
    }

    public void setTradeDocType(String tradeDocType) {
        this.tradeDocType = tradeDocType;
    }

    public String getTradeDocSource() {
        return tradeDocSource;
    }

    public void setTradeDocSource(String tradeDocSource) {
        this.tradeDocSource = tradeDocSource;
    }

    public String getTradeDocCredibility() {
        return tradeDocCredibility;
    }

    public void setTradeDocCredibility(String tradeDocCredibility) {
        this.tradeDocCredibility = tradeDocCredibility;
    }

    public String getTradeMilestoneStatus() {
        return tradeMilestoneStatus;
    }

    public void setTradeMilestoneStatus(String tradeMilestoneStatus) {
        this.tradeMilestoneStatus = tradeMilestoneStatus;
    }

    public String getTradeAdvancePayment() {
        return tradeAdvancePayment;
    }

    public void setTradeAdvancePayment(String tradeAdvancePayment) {
        this.tradeAdvancePayment = tradeAdvancePayment;
    }

    public String getAnchorTraderName() {
        return anchorTraderName;
    }

    public void setAnchorTraderName(String anchorTraderName) {
        this.anchorTraderName = anchorTraderName;
    }

    public String getTradePartnerName() {
        return tradePartnerName;
    }

    public void setTradePartnerName(String tradePartnerName) {
        this.tradePartnerName = tradePartnerName;
    }

    public Long getInvoiceTerm() {
        return invoiceTerm;
    }

    public void setInvoiceTerm(Long invoiceTerm) {
        this.invoiceTerm = invoiceTerm;
    }

    public String getAcceptanceFromTradePartner() {
        return acceptanceFromTradePartner;
    }

    public void setAcceptanceFromTradePartner(String acceptanceFromTradePartner) {
        this.acceptanceFromTradePartner = acceptanceFromTradePartner;
    }

    public String getReasonForFinance() {
        return reasonForFinance;
    }

    public void setReasonForFinance(String reasonForFinance) {
        this.reasonForFinance = reasonForFinance;
    }

    public String getTradePartnerSector() {
        return tradePartnerSector;
    }

    public void setTradePartnerSector(String tradePartnerSector) {
        this.tradePartnerSector = tradePartnerSector;
    }

    public String getTradePartnerLocation() {
        return tradePartnerLocation;
    }

    public void setTradePartnerLocation(String tradePartnerLocation) {
        this.tradePartnerLocation = tradePartnerLocation;
    }

    public String getTradePartnerGstComplianceScore() {
        return tradePartnerGstComplianceScore;
    }

    public void setTradePartnerGstComplianceScore(String tradePartnerGstComplianceScore) {
        this.tradePartnerGstComplianceScore = tradePartnerGstComplianceScore;
    }

    public FinanceRequestDTO getFinancerequest() {
        return financerequest;
    }

    public void setFinancerequest(FinanceRequestDTO financerequest) {
        this.financerequest = financerequest;
    }

    public AnchorTraderDTO getAnchortrader() {
        return anchortrader;
    }

    public void setAnchortrader(AnchorTraderDTO anchortrader) {
        this.anchortrader = anchortrader;
    }

    public TradePartnerDTO getTradepartner() {
        return tradepartner;
    }

    public void setTradepartner(TradePartnerDTO tradepartner) {
        this.tradepartner = tradepartner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradeDTO)) {
            return false;
        }

        TradeDTO tradeDTO = (TradeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tradeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradeDTO{" +
            "id=" + getId() +
            ", tradeUlidId='" + getTradeUlidId() + "'" +
            ", tradeRefNo='" + getTradeRefNo() + "'" +
            ", sellerGstId='" + getSellerGstId() + "'" +
            ", buyerGstId='" + getBuyerGstId() + "'" +
            ", tradeAmount='" + getTradeAmount() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", tradeDocType='" + getTradeDocType() + "'" +
            ", tradeDocSource='" + getTradeDocSource() + "'" +
            ", tradeDocCredibility='" + getTradeDocCredibility() + "'" +
            ", tradeMilestoneStatus='" + getTradeMilestoneStatus() + "'" +
            ", tradeAdvancePayment='" + getTradeAdvancePayment() + "'" +
            ", anchorTraderName='" + getAnchorTraderName() + "'" +
            ", tradePartnerName='" + getTradePartnerName() + "'" +
            ", invoiceTerm=" + getInvoiceTerm() +
            ", acceptanceFromTradePartner='" + getAcceptanceFromTradePartner() + "'" +
            ", reasonForFinance='" + getReasonForFinance() + "'" +
            ", tradePartnerSector='" + getTradePartnerSector() + "'" +
            ", tradePartnerLocation='" + getTradePartnerLocation() + "'" +
            ", tradePartnerGstComplianceScore='" + getTradePartnerGstComplianceScore() + "'" +
            ", financerequest=" + getFinancerequest() +
            ", anchortrader=" + getAnchortrader() +
            ", tradepartner=" + getTradepartner() +
            "}";
    }
}
