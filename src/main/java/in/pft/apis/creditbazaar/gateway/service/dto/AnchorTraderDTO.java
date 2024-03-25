package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.AnchorTrader} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnchorTraderDTO implements Serializable {

    private Long id;

    private Long atId;

    private String atUlidId;

    @NotNull(message = "must not be null")
    private String orgId;

    @NotNull(message = "must not be null")
    private String tenantId;

    @NotNull(message = "must not be null")
    private String customerName;

    @NotNull(message = "must not be null")
    private String orgName;

    @NotNull(message = "must not be null")
    private String gstId;

    @NotNull(message = "must not be null")
    private Long phoneNumber;

    private String anchorTraderName;

    private String location;

    private String anchorTraderGST;

    private String anchorTraderSector;

    private String anchorTraderPAN;

    private String kycCompleted;

    private String bankDetails;

    private String emailId;

    private String personalEmailId;

    private String accountNumber;

    private String ifscCode;

    private String bankName;

    private String branchName;

    private Boolean erpAccess;

    private String typeOfFirm;

    private String panStatus;

    private String tosDocument;

    private Boolean acceptTerms;

    private Boolean acceptDeclaration;

    private Boolean gstRegistrationCertificateUploadStatus;

    private Boolean gstRegistrationCertificateVerificationStatus;

    private Boolean udhyamRegistrationcertificateUploadStatus;

    private Boolean udhyamRegistrationcertificateVerificationStatus;

    private Boolean kycDeclaration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtId() {
        return atId;
    }

    public void setAtId(Long atId) {
        this.atId = atId;
    }

    public String getAtUlidId() {
        return atUlidId;
    }

    public void setAtUlidId(String atUlidId) {
        this.atUlidId = atUlidId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGstId() {
        return gstId;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAnchorTraderName() {
        return anchorTraderName;
    }

    public void setAnchorTraderName(String anchorTraderName) {
        this.anchorTraderName = anchorTraderName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnchorTraderGST() {
        return anchorTraderGST;
    }

    public void setAnchorTraderGST(String anchorTraderGST) {
        this.anchorTraderGST = anchorTraderGST;
    }

    public String getAnchorTraderSector() {
        return anchorTraderSector;
    }

    public void setAnchorTraderSector(String anchorTraderSector) {
        this.anchorTraderSector = anchorTraderSector;
    }

    public String getAnchorTraderPAN() {
        return anchorTraderPAN;
    }

    public void setAnchorTraderPAN(String anchorTraderPAN) {
        this.anchorTraderPAN = anchorTraderPAN;
    }

    public String getKycCompleted() {
        return kycCompleted;
    }

    public void setKycCompleted(String kycCompleted) {
        this.kycCompleted = kycCompleted;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPersonalEmailId() {
        return personalEmailId;
    }

    public void setPersonalEmailId(String personalEmailId) {
        this.personalEmailId = personalEmailId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Boolean getErpAccess() {
        return erpAccess;
    }

    public void setErpAccess(Boolean erpAccess) {
        this.erpAccess = erpAccess;
    }

    public String getTypeOfFirm() {
        return typeOfFirm;
    }

    public void setTypeOfFirm(String typeOfFirm) {
        this.typeOfFirm = typeOfFirm;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getTosDocument() {
        return tosDocument;
    }

    public void setTosDocument(String tosDocument) {
        this.tosDocument = tosDocument;
    }

    public Boolean getAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(Boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public Boolean getAcceptDeclaration() {
        return acceptDeclaration;
    }

    public void setAcceptDeclaration(Boolean acceptDeclaration) {
        this.acceptDeclaration = acceptDeclaration;
    }

    public Boolean getGstRegistrationCertificateUploadStatus() {
        return gstRegistrationCertificateUploadStatus;
    }

    public void setGstRegistrationCertificateUploadStatus(Boolean gstRegistrationCertificateUploadStatus) {
        this.gstRegistrationCertificateUploadStatus = gstRegistrationCertificateUploadStatus;
    }

    public Boolean getGstRegistrationCertificateVerificationStatus() {
        return gstRegistrationCertificateVerificationStatus;
    }

    public void setGstRegistrationCertificateVerificationStatus(Boolean gstRegistrationCertificateVerificationStatus) {
        this.gstRegistrationCertificateVerificationStatus = gstRegistrationCertificateVerificationStatus;
    }

    public Boolean getUdhyamRegistrationcertificateUploadStatus() {
        return udhyamRegistrationcertificateUploadStatus;
    }

    public void setUdhyamRegistrationcertificateUploadStatus(Boolean udhyamRegistrationcertificateUploadStatus) {
        this.udhyamRegistrationcertificateUploadStatus = udhyamRegistrationcertificateUploadStatus;
    }

    public Boolean getUdhyamRegistrationcertificateVerificationStatus() {
        return udhyamRegistrationcertificateVerificationStatus;
    }

    public void setUdhyamRegistrationcertificateVerificationStatus(Boolean udhyamRegistrationcertificateVerificationStatus) {
        this.udhyamRegistrationcertificateVerificationStatus = udhyamRegistrationcertificateVerificationStatus;
    }

    public Boolean getKycDeclaration() {
        return kycDeclaration;
    }

    public void setKycDeclaration(Boolean kycDeclaration) {
        this.kycDeclaration = kycDeclaration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnchorTraderDTO)) {
            return false;
        }

        AnchorTraderDTO anchorTraderDTO = (AnchorTraderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, anchorTraderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnchorTraderDTO{" +
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
