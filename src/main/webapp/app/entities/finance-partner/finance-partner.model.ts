import { IRequestOffer } from 'app/entities/request-offer/request-offer.model';
import { IPlacedOffer } from 'app/entities/placed-offer/placed-offer.model';
import { IAcceptedOffer } from 'app/entities/accepted-offer/accepted-offer.model';
import { IDisbursement } from 'app/entities/disbursement/disbursement.model';

export interface IFinancePartner {
  id: number;
  fpId?: number | null;
  fpUlidId?: string | null;
  tenantId?: string | null;
  orgId?: string | null;
  customerName?: string | null;
  orgName?: string | null;
  gstId?: string | null;
  phoneNumber?: number | null;
  tosDocument?: string | null;
  requestOffers?: Pick<IRequestOffer, 'id'>[] | null;
  placedOffers?: Pick<IPlacedOffer, 'id'>[] | null;
  acceptedOffers?: Pick<IAcceptedOffer, 'id'>[] | null;
  disbursements?: Pick<IDisbursement, 'id'>[] | null;
}

export type NewFinancePartner = Omit<IFinancePartner, 'id'> & { id: null };
