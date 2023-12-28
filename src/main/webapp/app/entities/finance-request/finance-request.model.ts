import dayjs from 'dayjs/esm';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { IRequestOffer } from 'app/entities/request-offer/request-offer.model';
import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IProspectRequest } from 'app/entities/prospect-request/prospect-request.model';
import { ITrade } from 'app/entities/trade/trade.model';
import { IPlacedOffer } from 'app/entities/placed-offer/placed-offer.model';
import { IAcceptedOffer } from 'app/entities/accepted-offer/accepted-offer.model';
import { ISettlement } from 'app/entities/settlement/settlement.model';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';

export interface IFinanceRequest {
  id: number;
  requestId?: string | null;
  financeRequestRefNo?: string | null;
  requestAmount?: string | null;
  requestDate?: dayjs.Dayjs | null;
  currency?: string | null;
  requestStatus?: string | null;
  dueDate?: dayjs.Dayjs | null;
  repayments?: Pick<IRepayment, 'id'>[] | null;
  requestOffers?: Pick<IRequestOffer, 'id'>[] | null;
  disbursements?: Pick<IDisbursement, 'id'>[] | null;
  prospectRequests?: Pick<IProspectRequest, 'id'>[] | null;
  trades?: Pick<ITrade, 'id'>[] | null;
  placedOffers?: Pick<IPlacedOffer, 'id'>[] | null;
  acceptedOffers?: Pick<IAcceptedOffer, 'id'>[] | null;
  settlements?: Pick<ISettlement, 'id'>[] | null;
  anchortrader?: Pick<IAnchorTrader, 'id' | 'atId'> | null;
}

export type NewFinanceRequest = Omit<IFinanceRequest, 'id'> & { id: null };
