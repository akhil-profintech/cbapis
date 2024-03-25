package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A AnchorTrader.
 */
@Table("anchor_trader")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnchorTrader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("at_id")
    private Long atId;

    @Column("at_ulid_id")
    private String atUlidId;

    @NotNull(message = "must not be null")
    @Column("org_id")
    private String orgId;

    @NotNull(message = "must not be null")
    @Column("tenant_id")
    private String tenantId;

    @NotNull(message = "must not be null")
    @Column("customer_name")
    private String customerName;

    @NotNull(message = "must not be null")
    @Column("org_name")
    private String orgName;

    @NotNull(message = "must not be null")
    @Column("gst_id")
    private String gstId;

    @NotNull(message = "must not be null")
    @Column("phone_number")
    private Long phoneNumber;

    @Column("anchor_trader_name")
    private String anchorTraderName;

    @Column("location")
    private String location;

    @Column("anchor_trader_gst")
    private String anchorTraderGST;

    @Column("anchor_trader_sector")
    private String anchorTraderSector;

    @Column("anchor_trader_pan")
    private String anchorTraderPAN;

    @Column("kyc_completed")
    private String kycCompleted;

    @Column("bank_details")
    private String bankDetails;

    @Column("email_id")
    private String emailId;

    @Column("personal_email_id")
    private String personalEmailId;

    @Column("account_number")
    private String accountNumber;

    @Column("ifsc_code")
    private String ifscCode;

    @Column("bank_name")
    private String bankName;

    @Column("branch_name")
    private String branchName;

    @Column("erp_access")
    private Boolean erpAccess;

    @Column("type_of_firm")
    private String typeOfFirm;

    @Column("pan_status")
    private String panStatus;

    @Column("tos_document")
    private String tosDocument;

    @Column("accept_terms")
    private Boolean acceptTerms;

    @Column("accept_declaration")
    private Boolean acceptDeclaration;

    @Column("gst_registration_certificate_upload_status")
    private Boolean gstRegistrationCertificateUploadStatus;

    @Column("gst_registration_certificate_verification_status")
    private Boolean gstRegistrationCertificateVerificationStatus;

    @Column("udhyam_registrationcertificate_upload_status")
    private Boolean udhyamRegistrationcertificateUploadStatus;

    @Column("udhyam_registrationcertificate_verification_status")
    private Boolean udhyamRegistrationcertificateVerificationStatus;

    @Column("kyc_declaration")
    private Boolean kycDeclaration;

    @Transient
    @JsonIgnoreProperties(
        value = {
            "requestOffers",
            "trades",
            "placedOffers",
            "acceptedOffers",
            "disbursements",
            "docDetails",
            "repayments",
            "settlements",
            "cBCREProcesses",
            "anchortrader",
        },
        allowSetters = true
    )
    private Set<FinanceRequest> financeRequests = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "anchortrader" }, allowSetters = true)
    private Set<AnchorTraderPartner> anchorTraderPartners = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "anchortrader", "financepartner" }, allowSetters = true)
    private Set<AcceptedOffer> acceptedOffers = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "anchortrader", "tradepartner" }, allowSetters = true)
    private Set<Trade> trades = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AnchorTrader id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtId() {
        return this.atId;
    }

    public AnchorTrader atId(Long atId) {
        this.setAtId(atId);
        return this;
    }

    public void setAtId(Long atId) {
        this.atId = atId;
    }

    public String getAtUlidId() {
        return this.atUlidId;
    }

    public AnchorTrader atUlidId(String atUlidId) {
        this.setAtUlidId(atUlidId);
        return this;
    }

    public void setAtUlidId(String atUlidId) {
        this.atUlidId = atUlidId;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public AnchorTrader orgId(String orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public AnchorTrader tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public AnchorTrader customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public AnchorTrader orgName(String orgName) {
        this.setOrgName(orgName);
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGstId() {
        return this.gstId;
    }

    public AnchorTrader gstId(String gstId) {
        this.setGstId(gstId);
        return this;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public Long getPhoneNumber() {
        return this.phoneNumber;
    }

    public AnchorTrader phoneNumber(Long phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAnchorTraderName() {
        return this.anchorTraderName;
    }

    public AnchorTrader anchorTraderName(String anchorTraderName) {
        this.setAnchorTraderName(anchorTraderName);
        return this;
    }

    public void setAnchorTraderName(String anchorTraderName) {
        this.anchorTraderName = anchorTraderName;
    }

    public String getLocation() {
        return this.location;
    }

    public AnchorTrader location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnchorTraderGST() {
        return this.anchorTraderGST;
    }

    public AnchorTrader anchorTraderGST(String anchorTraderGST) {
        this.setAnchorTraderGST(anchorTraderGST);
        return this;
    }

    public void setAnchorTraderGST(String anchorTraderGST) {
        this.anchorTraderGST = anchorTraderGST;
    }

    public String getAnchorTraderSector() {
        return this.anchorTraderSector;
    }

    public AnchorTrader anchorTraderSector(String anchorTraderSector) {
        this.setAnchorTraderSector(anchorTraderSector);
        return this;
    }

    public void setAnchorTraderSector(String anchorTraderSector) {
        this.anchorTraderSector = anchorTraderSector;
    }

    public String getAnchorTraderPAN() {
        return this.anchorTraderPAN;
    }

    public AnchorTrader anchorTraderPAN(String anchorTraderPAN) {
        this.setAnchorTraderPAN(anchorTraderPAN);
        return this;
    }

    public void setAnchorTraderPAN(String anchorTraderPAN) {
        this.anchorTraderPAN = anchorTraderPAN;
    }

    public String getKycCompleted() {
        return this.kycCompleted;
    }

    public AnchorTrader kycCompleted(String kycCompleted) {
        this.setKycCompleted(kycCompleted);
        return this;
    }

    public void setKycCompleted(String kycCompleted) {
        this.kycCompleted = kycCompleted;
    }

    public String getBankDetails() {
        return this.bankDetails;
    }

    public AnchorTrader bankDetails(String bankDetails) {
        this.setBankDetails(bankDetails);
        return this;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public AnchorTrader emailId(String emailId) {
        this.setEmailId(emailId);
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPersonalEmailId() {
        return this.personalEmailId;
    }

    public AnchorTrader personalEmailId(String personalEmailId) {
        this.setPersonalEmailId(personalEmailId);
        return this;
    }

    public void setPersonalEmailId(String personalEmailId) {
        this.personalEmailId = personalEmailId;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public AnchorTrader accountNumber(String accountNumber) {
        this.setAccountNumber(accountNumber);
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public AnchorTrader ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return this.bankName;
    }

    public AnchorTrader bankName(String bankName) {
        this.setBankName(bankName);
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public AnchorTrader branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Boolean getErpAccess() {
        return this.erpAccess;
    }

    public AnchorTrader erpAccess(Boolean erpAccess) {
        this.setErpAccess(erpAccess);
        return this;
    }

    public void setErpAccess(Boolean erpAccess) {
        this.erpAccess = erpAccess;
    }

    public String getTypeOfFirm() {
        return this.typeOfFirm;
    }

    public AnchorTrader typeOfFirm(String typeOfFirm) {
        this.setTypeOfFirm(typeOfFirm);
        return this;
    }

    public void setTypeOfFirm(String typeOfFirm) {
        this.typeOfFirm = typeOfFirm;
    }

    public String getPanStatus() {
        return this.panStatus;
    }

    public AnchorTrader panStatus(String panStatus) {
        this.setPanStatus(panStatus);
        return this;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getTosDocument() {
        return this.tosDocument;
    }

    public AnchorTrader tosDocument(String tosDocument) {
        this.setTosDocument(tosDocument);
        return this;
    }

    public void setTosDocument(String tosDocument) {
        this.tosDocument = tosDocument;
    }

    public Boolean getAcceptTerms() {
        return this.acceptTerms;
    }

    public AnchorTrader acceptTerms(Boolean acceptTerms) {
        this.setAcceptTerms(acceptTerms);
        return this;
    }

    public void setAcceptTerms(Boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public Boolean getAcceptDeclaration() {
        return this.acceptDeclaration;
    }

    public AnchorTrader acceptDeclaration(Boolean acceptDeclaration) {
        this.setAcceptDeclaration(acceptDeclaration);
        return this;
    }

    public void setAcceptDeclaration(Boolean acceptDeclaration) {
        this.acceptDeclaration = acceptDeclaration;
    }

    public Boolean getGstRegistrationCertificateUploadStatus() {
        return this.gstRegistrationCertificateUploadStatus;
    }

    public AnchorTrader gstRegistrationCertificateUploadStatus(Boolean gstRegistrationCertificateUploadStatus) {
        this.setGstRegistrationCertificateUploadStatus(gstRegistrationCertificateUploadStatus);
        return this;
    }

    public void setGstRegistrationCertificateUploadStatus(Boolean gstRegistrationCertificateUploadStatus) {
        this.gstRegistrationCertificateUploadStatus = gstRegistrationCertificateUploadStatus;
    }

    public Boolean getGstRegistrationCertificateVerificationStatus() {
        return this.gstRegistrationCertificateVerificationStatus;
    }

    public AnchorTrader gstRegistrationCertificateVerificationStatus(Boolean gstRegistrationCertificateVerificationStatus) {
        this.setGstRegistrationCertificateVerificationStatus(gstRegistrationCertificateVerificationStatus);
        return this;
    }

    public void setGstRegistrationCertificateVerificationStatus(Boolean gstRegistrationCertificateVerificationStatus) {
        this.gstRegistrationCertificateVerificationStatus = gstRegistrationCertificateVerificationStatus;
    }

    public Boolean getUdhyamRegistrationcertificateUploadStatus() {
        return this.udhyamRegistrationcertificateUploadStatus;
    }

    public AnchorTrader udhyamRegistrationcertificateUploadStatus(Boolean udhyamRegistrationcertificateUploadStatus) {
        this.setUdhyamRegistrationcertificateUploadStatus(udhyamRegistrationcertificateUploadStatus);
        return this;
    }

    public void setUdhyamRegistrationcertificateUploadStatus(Boolean udhyamRegistrationcertificateUploadStatus) {
        this.udhyamRegistrationcertificateUploadStatus = udhyamRegistrationcertificateUploadStatus;
    }

    public Boolean getUdhyamRegistrationcertificateVerificationStatus() {
        return this.udhyamRegistrationcertificateVerificationStatus;
    }

    public AnchorTrader udhyamRegistrationcertificateVerificationStatus(Boolean udhyamRegistrationcertificateVerificationStatus) {
        this.setUdhyamRegistrationcertificateVerificationStatus(udhyamRegistrationcertificateVerificationStatus);
        return this;
    }

    public void setUdhyamRegistrationcertificateVerificationStatus(Boolean udhyamRegistrationcertificateVerificationStatus) {
        this.udhyamRegistrationcertificateVerificationStatus = udhyamRegistrationcertificateVerificationStatus;
    }

    public Boolean getKycDeclaration() {
        return this.kycDeclaration;
    }

    public AnchorTrader kycDeclaration(Boolean kycDeclaration) {
        this.setKycDeclaration(kycDeclaration);
        return this;
    }

    public void setKycDeclaration(Boolean kycDeclaration) {
        this.kycDeclaration = kycDeclaration;
    }

    public Set<FinanceRequest> getFinanceRequests() {
        return this.financeRequests;
    }

    public void setFinanceRequests(Set<FinanceRequest> financeRequests) {
        if (this.financeRequests != null) {
            this.financeRequests.forEach(i -> i.setAnchortrader(null));
        }
        if (financeRequests != null) {
            financeRequests.forEach(i -> i.setAnchortrader(this));
        }
        this.financeRequests = financeRequests;
    }

    public AnchorTrader financeRequests(Set<FinanceRequest> financeRequests) {
        this.setFinanceRequests(financeRequests);
        return this;
    }

    public AnchorTrader addFinanceRequest(FinanceRequest financeRequest) {
        this.financeRequests.add(financeRequest);
        financeRequest.setAnchortrader(this);
        return this;
    }

    public AnchorTrader removeFinanceRequest(FinanceRequest financeRequest) {
        this.financeRequests.remove(financeRequest);
        financeRequest.setAnchortrader(null);
        return this;
    }

    public Set<AnchorTraderPartner> getAnchorTraderPartners() {
        return this.anchorTraderPartners;
    }

    public void setAnchorTraderPartners(Set<AnchorTraderPartner> anchorTraderPartners) {
        if (this.anchorTraderPartners != null) {
            this.anchorTraderPartners.forEach(i -> i.setAnchortrader(null));
        }
        if (anchorTraderPartners != null) {
            anchorTraderPartners.forEach(i -> i.setAnchortrader(this));
        }
        this.anchorTraderPartners = anchorTraderPartners;
    }

    public AnchorTrader anchorTraderPartners(Set<AnchorTraderPartner> anchorTraderPartners) {
        this.setAnchorTraderPartners(anchorTraderPartners);
        return this;
    }

    public AnchorTrader addAnchorTraderPartner(AnchorTraderPartner anchorTraderPartner) {
        this.anchorTraderPartners.add(anchorTraderPartner);
        anchorTraderPartner.setAnchortrader(this);
        return this;
    }

    public AnchorTrader removeAnchorTraderPartner(AnchorTraderPartner anchorTraderPartner) {
        this.anchorTraderPartners.remove(anchorTraderPartner);
        anchorTraderPartner.setAnchortrader(null);
        return this;
    }

    public Set<AcceptedOffer> getAcceptedOffers() {
        return this.acceptedOffers;
    }

    public void setAcceptedOffers(Set<AcceptedOffer> acceptedOffers) {
        if (this.acceptedOffers != null) {
            this.acceptedOffers.forEach(i -> i.setAnchortrader(null));
        }
        if (acceptedOffers != null) {
            acceptedOffers.forEach(i -> i.setAnchortrader(this));
        }
        this.acceptedOffers = acceptedOffers;
    }

    public AnchorTrader acceptedOffers(Set<AcceptedOffer> acceptedOffers) {
        this.setAcceptedOffers(acceptedOffers);
        return this;
    }

    public AnchorTrader addAcceptedOffer(AcceptedOffer acceptedOffer) {
        this.acceptedOffers.add(acceptedOffer);
        acceptedOffer.setAnchortrader(this);
        return this;
    }

    public AnchorTrader removeAcceptedOffer(AcceptedOffer acceptedOffer) {
        this.acceptedOffers.remove(acceptedOffer);
        acceptedOffer.setAnchortrader(null);
        return this;
    }

    public Set<Trade> getTrades() {
        return this.trades;
    }

    public void setTrades(Set<Trade> trades) {
        if (this.trades != null) {
            this.trades.forEach(i -> i.setAnchortrader(null));
        }
        if (trades != null) {
            trades.forEach(i -> i.setAnchortrader(this));
        }
        this.trades = trades;
    }

    public AnchorTrader trades(Set<Trade> trades) {
        this.setTrades(trades);
        return this;
    }

    public AnchorTrader addTrade(Trade trade) {
        this.trades.add(trade);
        trade.setAnchortrader(this);
        return this;
    }

    public AnchorTrader removeTrade(Trade trade) {
        this.trades.remove(trade);
        trade.setAnchortrader(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnchorTrader)) {
            return false;
        }
        return getId() != null && getId().equals(((AnchorTrader) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnchorTrader{" +
            "id=" + getId() +
            ", atId=" + getAtId() +
            ", atUlidId='" + getAtUlidId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", gstId='" + getGstId() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", anchorTraderName='" + getAnchorTraderName() + "'" +
            ", location='" + getLocation() + "'" +
            ", anchorTraderGST='" + getAnchorTraderGST() + "'" +
            ", anchorTraderSector='" + getAnchorTraderSector() + "'" +
            ", anchorTraderPAN='" + getAnchorTraderPAN() + "'" +
            ", kycCompleted='" + getKycCompleted() + "'" +
            ", bankDetails='" + getBankDetails() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", personalEmailId='" + getPersonalEmailId() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", branchName='" + getBranchName() + "'" +
            ", erpAccess='" + getErpAccess() + "'" +
            ", typeOfFirm='" + getTypeOfFirm() + "'" +
            ", panStatus='" + getPanStatus() + "'" +
            ", tosDocument='" + getTosDocument() + "'" +
            ", acceptTerms='" + getAcceptTerms() + "'" +
            ", acceptDeclaration='" + getAcceptDeclaration() + "'" +
            ", gstRegistrationCertificateUploadStatus='" + getGstRegistrationCertificateUploadStatus() + "'" +
            ", gstRegistrationCertificateVerificationStatus='" + getGstRegistrationCertificateVerificationStatus() + "'" +
            ", udhyamRegistrationcertificateUploadStatus='" + getUdhyamRegistrationcertificateUploadStatus() + "'" +
            ", udhyamRegistrationcertificateVerificationStatus='" + getUdhyamRegistrationcertificateVerificationStatus() + "'" +
            ", kycDeclaration='" + getKycDeclaration() + "'" +
            "}";
    }
}
