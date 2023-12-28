import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IAcceptedOffer } from 'app/entities/accepted-offer/accepted-offer.model';
import { ITrade } from 'app/entities/trade/trade.model';

export interface IAnchorTrader {
  id: number;
  tenantId?: string | null;
  atId?: string | null;
  orgId?: string | null;
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
  financeRequests?: Pick<IFinanceRequest, 'id'>[] | null;
  acceptedOffers?: Pick<IAcceptedOffer, 'id'>[] | null;
  trades?: Pick<ITrade, 'id'>[] | null;
}

export type NewAnchorTrader = Omit<IAnchorTrader, 'id'> & { id: null };
