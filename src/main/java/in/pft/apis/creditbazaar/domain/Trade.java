package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Trade.
 */
@Table("trade")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Trade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("trade_id")
    private Long tradeId;

    @Column("trade_ref_number")
    private String tradeRefNumber;

    @Column("seller_gst_id")
    private String sellerGstId;

    @Column("buyer_gst_id")
    private String buyerGstId;

    @Column("trade_amount")
    private String tradeAmount;

    @Column("invoice_date")
    private LocalDate invoiceDate;

    @Column("invoice_number")
    private String invoiceNumber;

    @Column("trade_doc_type")
    private String tradeDocType;

    @Column("trade_doc_source")
    private String tradeDocSource;

    @Column("trade_doc_credibility")
    private String tradeDocCredibility;

    @Column("trade_milestone_status")
    private String tradeMilestoneStatus;

    @Column("trade_advance_payment")
    private String tradeAdvancePayment;

    @Column("anchor_trader_name")
    private String anchorTraderName;

    @Column("trade_partner_name")
    private String tradePartnerName;

    @Column("invoice_term")
    private Long invoiceTerm;

    @Column("acceptance_from_trade_partner")
    private String acceptanceFromTradePartner;

    @Column("reason_for_finance")
    private String reasonForFinance;

    @Column("trade_partner_sector")
    private String tradePartnerSector;

    @Column("trade_partner_location")
    private String tradePartnerLocation;

    @Column("trade_partner_gst_compliance_score")
    private String tradePartnerGstComplianceScore;

    @Transient
    @JsonIgnoreProperties(
        value = {
            "repayments",
            "requestOffers",
            "disbursements",
            "prospectRequests",
            "trades",
            "placedOffers",
            "acceptedOffers",
            "settlements",
            "anchortrader",
        },
        allowSetters = true
    )
    private FinanceRequest financerequest;

    @Transient
    @JsonIgnoreProperties(value = { "trades" }, allowSetters = true)
    private TradePartner tradepartner;

    @Transient
    @JsonIgnoreProperties(value = { "financeRequests", "acceptedOffers", "trades" }, allowSetters = true)
    private AnchorTrader anchortrader;

    @Column("financerequest_id")
    private Long financerequestId;

    @Column("tradepartner_id")
    private Long tradepartnerId;

    @Column("anchortrader_id")
    private Long anchortraderId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Trade id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTradeId() {
        return this.tradeId;
    }

    public Trade tradeId(Long tradeId) {
        this.setTradeId(tradeId);
        return this;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeRefNumber() {
        return this.tradeRefNumber;
    }

    public Trade tradeRefNumber(String tradeRefNumber) {
        this.setTradeRefNumber(tradeRefNumber);
        return this;
    }

    public void setTradeRefNumber(String tradeRefNumber) {
        this.tradeRefNumber = tradeRefNumber;
    }

    public String getSellerGstId() {
        return this.sellerGstId;
    }

    public Trade sellerGstId(String sellerGstId) {
        this.setSellerGstId(sellerGstId);
        return this;
    }

    public void setSellerGstId(String sellerGstId) {
        this.sellerGstId = sellerGstId;
    }

    public String getBuyerGstId() {
        return this.buyerGstId;
    }

    public Trade buyerGstId(String buyerGstId) {
        this.setBuyerGstId(buyerGstId);
        return this;
    }

    public void setBuyerGstId(String buyerGstId) {
        this.buyerGstId = buyerGstId;
    }

    public String getTradeAmount() {
        return this.tradeAmount;
    }

    public Trade tradeAmount(String tradeAmount) {
        this.setTradeAmount(tradeAmount);
        return this;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public LocalDate getInvoiceDate() {
        return this.invoiceDate;
    }

    public Trade invoiceDate(LocalDate invoiceDate) {
        this.setInvoiceDate(invoiceDate);
        return this;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public Trade invoiceNumber(String invoiceNumber) {
        this.setInvoiceNumber(invoiceNumber);
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getTradeDocType() {
        return this.tradeDocType;
    }

    public Trade tradeDocType(String tradeDocType) {
        this.setTradeDocType(tradeDocType);
        return this;
    }

    public void setTradeDocType(String tradeDocType) {
        this.tradeDocType = tradeDocType;
    }

    public String getTradeDocSource() {
        return this.tradeDocSource;
    }

    public Trade tradeDocSource(String tradeDocSource) {
        this.setTradeDocSource(tradeDocSource);
        return this;
    }

    public void setTradeDocSource(String tradeDocSource) {
        this.tradeDocSource = tradeDocSource;
    }

    public String getTradeDocCredibility() {
        return this.tradeDocCredibility;
    }

    public Trade tradeDocCredibility(String tradeDocCredibility) {
        this.setTradeDocCredibility(tradeDocCredibility);
        return this;
    }

    public void setTradeDocCredibility(String tradeDocCredibility) {
        this.tradeDocCredibility = tradeDocCredibility;
    }

    public String getTradeMilestoneStatus() {
        return this.tradeMilestoneStatus;
    }

    public Trade tradeMilestoneStatus(String tradeMilestoneStatus) {
        this.setTradeMilestoneStatus(tradeMilestoneStatus);
        return this;
    }

    public void setTradeMilestoneStatus(String tradeMilestoneStatus) {
        this.tradeMilestoneStatus = tradeMilestoneStatus;
    }

    public String getTradeAdvancePayment() {
        return this.tradeAdvancePayment;
    }

    public Trade tradeAdvancePayment(String tradeAdvancePayment) {
        this.setTradeAdvancePayment(tradeAdvancePayment);
        return this;
    }

    public void setTradeAdvancePayment(String tradeAdvancePayment) {
        this.tradeAdvancePayment = tradeAdvancePayment;
    }

    public String getAnchorTraderName() {
        return this.anchorTraderName;
    }

    public Trade anchorTraderName(String anchorTraderName) {
        this.setAnchorTraderName(anchorTraderName);
        return this;
    }

    public void setAnchorTraderName(String anchorTraderName) {
        this.anchorTraderName = anchorTraderName;
    }

    public String getTradePartnerName() {
        return this.tradePartnerName;
    }

    public Trade tradePartnerName(String tradePartnerName) {
        this.setTradePartnerName(tradePartnerName);
        return this;
    }

    public void setTradePartnerName(String tradePartnerName) {
        this.tradePartnerName = tradePartnerName;
    }

    public Long getInvoiceTerm() {
        return this.invoiceTerm;
    }

    public Trade invoiceTerm(Long invoiceTerm) {
        this.setInvoiceTerm(invoiceTerm);
        return this;
    }

    public void setInvoiceTerm(Long invoiceTerm) {
        this.invoiceTerm = invoiceTerm;
    }

    public String getAcceptanceFromTradePartner() {
        return this.acceptanceFromTradePartner;
    }

    public Trade acceptanceFromTradePartner(String acceptanceFromTradePartner) {
        this.setAcceptanceFromTradePartner(acceptanceFromTradePartner);
        return this;
    }

    public void setAcceptanceFromTradePartner(String acceptanceFromTradePartner) {
        this.acceptanceFromTradePartner = acceptanceFromTradePartner;
    }

    public String getReasonForFinance() {
        return this.reasonForFinance;
    }

    public Trade reasonForFinance(String reasonForFinance) {
        this.setReasonForFinance(reasonForFinance);
        return this;
    }

    public void setReasonForFinance(String reasonForFinance) {
        this.reasonForFinance = reasonForFinance;
    }

    public String getTradePartnerSector() {
        return this.tradePartnerSector;
    }

    public Trade tradePartnerSector(String tradePartnerSector) {
        this.setTradePartnerSector(tradePartnerSector);
        return this;
    }

    public void setTradePartnerSector(String tradePartnerSector) {
        this.tradePartnerSector = tradePartnerSector;
    }

    public String getTradePartnerLocation() {
        return this.tradePartnerLocation;
    }

    public Trade tradePartnerLocation(String tradePartnerLocation) {
        this.setTradePartnerLocation(tradePartnerLocation);
        return this;
    }

    public void setTradePartnerLocation(String tradePartnerLocation) {
        this.tradePartnerLocation = tradePartnerLocation;
    }

    public String getTradePartnerGstComplianceScore() {
        return this.tradePartnerGstComplianceScore;
    }

    public Trade tradePartnerGstComplianceScore(String tradePartnerGstComplianceScore) {
        this.setTradePartnerGstComplianceScore(tradePartnerGstComplianceScore);
        return this;
    }

    public void setTradePartnerGstComplianceScore(String tradePartnerGstComplianceScore) {
        this.tradePartnerGstComplianceScore = tradePartnerGstComplianceScore;
    }

    public FinanceRequest getFinancerequest() {
        return this.financerequest;
    }

    public void setFinancerequest(FinanceRequest financeRequest) {
        this.financerequest = financeRequest;
        this.financerequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public Trade financerequest(FinanceRequest financeRequest) {
        this.setFinancerequest(financeRequest);
        return this;
    }

    public TradePartner getTradepartner() {
        return this.tradepartner;
    }

    public void setTradepartner(TradePartner tradePartner) {
        this.tradepartner = tradePartner;
        this.tradepartnerId = tradePartner != null ? tradePartner.getId() : null;
    }

    public Trade tradepartner(TradePartner tradePartner) {
        this.setTradepartner(tradePartner);
        return this;
    }

    public AnchorTrader getAnchortrader() {
        return this.anchortrader;
    }

    public void setAnchortrader(AnchorTrader anchorTrader) {
        this.anchortrader = anchorTrader;
        this.anchortraderId = anchorTrader != null ? anchorTrader.getId() : null;
    }

    public Trade anchortrader(AnchorTrader anchorTrader) {
        this.setAnchortrader(anchorTrader);
        return this;
    }

    public Long getFinancerequestId() {
        return this.financerequestId;
    }

    public void setFinancerequestId(Long financeRequest) {
        this.financerequestId = financeRequest;
    }

    public Long getTradepartnerId() {
        return this.tradepartnerId;
    }

    public void setTradepartnerId(Long tradePartner) {
        this.tradepartnerId = tradePartner;
    }

    public Long getAnchortraderId() {
        return this.anchortraderId;
    }

    public void setAnchortraderId(Long anchorTrader) {
        this.anchortraderId = anchorTrader;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trade)) {
            return false;
        }
        return getId() != null && getId().equals(((Trade) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Trade{" +
            "id=" + getId() +
            ", tradeId=" + getTradeId() +
            ", tradeRefNumber='" + getTradeRefNumber() + "'" +
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
            "}";
    }
}
