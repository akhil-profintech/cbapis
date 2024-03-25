import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IAnchorTraderPartner } from 'app/entities/anchor-trader-partner/anchor-trader-partner.model';
import { IAcceptedOffer } from 'app/entities/accepted-offer/accepted-offer.model';
import { ITrade } from 'app/entities/trade/trade.model';

export interface IAnchorTrader {
  id: number;
  atId?: number | null;
  atUlidId?: string | null;
  orgId?: string | null;
  tenantId?: string | null;
  customerName?: string | null;
  orgName?: string | null;
  gstId?: string | null;
  phoneNumber?: number | null;
  anchorTraderName?: string | null;
  location?: string | null;
  anchorTraderGST?: string | null;
  anchorTraderSector?: string | null;
  anchorTraderPAN?: string | null;
  kycCompleted?: string | null;
  bankDetails?: string | null;
  emailId?: string | null;
  personalEmailId?: string | null;
  accountNumber?: string | null;
  ifscCode?: string | null;
  bankName?: string | null;
  branchName?: string | null;
  erpAccess?: boolean | null;
  typeOfFirm?: string | null;
  panStatus?: string | null;
  tosDocument?: string | null;
  acceptTerms?: boolean | null;
  acceptDeclaration?: boolean | null;
  gstRegistrationCertificateUploadStatus?: boolean | null;
  gstRegistrationCertificateVerificationStatus?: boolean | null;
  udhyamRegistrationcertificateUploadStatus?: boolean | null;
  udhyamRegistrationcertificateVerificationStatus?: boolean | null;
  kycDeclaration?: boolean | null;
  financeRequests?: Pick<IFinanceRequest, 'id'>[] | null;
  anchorTraderPartners?: Pick<IAnchorTraderPartner, 'id'>[] | null;
  acceptedOffers?: Pick<IAcceptedOffer, 'id'>[] | null;
  trades?: Pick<ITrade, 'id'>[] | null;
}

export type NewAnchorTrader = Omit<IAnchorTrader, 'id'> & { id: null };
