import dayjs from 'dayjs/esm';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';

export interface IRequestOffer {
  id: number;
  reqOffId?: string | null;
  requestOfferRefNo?: string | null;
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
  sellerName?: string | null;
  buyerName?: string | null;
  anchorTraderGstComplianceScore?: string | null;
  anchorTraderErpPeerScore?: string | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'requestId'> | null;
  cbcreprocess?: Pick<ICBCREProcess, 'id' | 'cbProcessId'> | null;
}

export type NewRequestOffer = Omit<IRequestOffer, 'id'> & { id: null };
