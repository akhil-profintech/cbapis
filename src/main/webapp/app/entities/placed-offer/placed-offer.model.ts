import dayjs from 'dayjs/esm';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';

export interface IPlacedOffer {
  id: number;
  placedOfferUlidId?: string | null;
  placedOfferRefNo?: string | null;
  reqOffUlidId?: string | null;
  requestOfferRefNo?: string | null;
  value?: number | null;
  reqAmount?: number | null;
  marginPtg?: number | null;
  marginValue?: number | null;
  amountAftMargin?: number | null;
  interestPtg?: number | null;
  term?: number | null;
  interestValue?: number | null;
  netAmount?: number | null;
  status?: string | null;
  offerDate?: dayjs.Dayjs | null;
  placedOfferDate?: dayjs.Dayjs | null;
  anchorTrader?: string | null;
  tradePartner?: string | null;
  disbursalAmount?: string | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
  financepartner?: Pick<IFinancePartner, 'id' | 'fpId'> | null;
}

export type NewPlacedOffer = Omit<IPlacedOffer, 'id'> & { id: null };
