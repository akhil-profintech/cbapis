import dayjs from 'dayjs/esm';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';

export interface IAcceptedOffer {
  id: number;
  offerId?: string | null;
  acceptedOfferRefNo?: string | null;
  reqOffId?: number | null;
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
  financerequest?: Pick<IFinanceRequest, 'id' | 'requestId'> | null;
  financepartner?: Pick<IFinancePartner, 'id' | 'fpId'> | null;
  anchortrader?: Pick<IAnchorTrader, 'id' | 'atId'> | null;
}

export type NewAcceptedOffer = Omit<IAcceptedOffer, 'id'> & { id: null };
