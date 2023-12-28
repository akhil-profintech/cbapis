import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IPlacedOffer } from 'app/entities/placed-offer/placed-offer.model';
import { IAcceptedOffer } from 'app/entities/accepted-offer/accepted-offer.model';

export interface IFinancePartner {
  id: number;
  tenantId?: string | null;
  fpId?: string | null;
  orgId?: string | null;
  customerName?: string | null;
  orgName?: string | null;
  gstId?: string | null;
  phoneNumber?: number | null;
  disbursements?: Pick<IDisbursement, 'id'>[] | null;
  placedOffers?: Pick<IPlacedOffer, 'id'>[] | null;
  acceptedOffers?: Pick<IAcceptedOffer, 'id'>[] | null;
}

export type NewFinancePartner = Omit<IFinancePartner, 'id'> & { id: null };
