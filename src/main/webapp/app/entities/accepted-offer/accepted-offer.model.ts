import dayjs from 'dayjs/esm';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';

export interface IAcceptedOffer {
  id: number;
  acceptedOfferUlidId?: string | null;
  acceptedOfferRefNo?: string | null;
  reqOffUlidId?: string | null;
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
  offerAcceptedDate?: dayjs.Dayjs | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
  anchortrader?: Pick<IAnchorTrader, 'id' | 'atId'> | null;
  financepartner?: Pick<IFinancePartner, 'id' | 'fpId'> | null;
}

export type NewAcceptedOffer = Omit<IAcceptedOffer, 'id'> & { id: null };
