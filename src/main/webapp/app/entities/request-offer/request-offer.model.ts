import dayjs from 'dayjs/esm';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';

export interface IRequestOffer {
  id: number;
  reqOffUlidId?: string | null;
  reqOfferRefNo?: string | null;
  offerValue?: number | null;
  requestAmt?: number | null;
  tradeValue?: number | null;
  marginPtg?: number | null;
  marginValue?: number | null;
  amountAftMargin?: number | null;
  interestPtg?: number | null;
  term?: number | null;
  interestValue?: number | null;
  netAmount?: number | null;
  status?: string | null;
  financeRequestDate?: dayjs.Dayjs | null;
  anchorTraderName?: string | null;
  tradePartnerName?: string | null;
  anchorTraderGst?: string | null;
  tradePartnerGst?: string | null;
  anchorTraderGSTComplianceScore?: string | null;
  anchorTraderGSTERPPeerScore?: string | null;
  sellerTradePerformanceIndex?: string | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
  financepartner?: Pick<IFinancePartner, 'id' | 'fpId'> | null;
}

export type NewRequestOffer = Omit<IRequestOffer, 'id'> & { id: null };
