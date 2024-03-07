import dayjs from 'dayjs/esm';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { ITradePartner } from 'app/entities/trade-partner/trade-partner.model';

export interface ITrade {
  id: number;
  tradeUlidId?: string | null;
  tradeRefNo?: string | null;
  sellerGstId?: string | null;
  buyerGstId?: string | null;
  tradeAmount?: string | null;
  invoiceDate?: dayjs.Dayjs | null;
  invoiceNumber?: string | null;
  tradeDocType?: string | null;
  tradeDocSource?: string | null;
  tradeDocCredibility?: string | null;
  tradeMilestoneStatus?: string | null;
  tradeAdvancePayment?: string | null;
  anchorTraderName?: string | null;
  tradePartnerName?: string | null;
  invoiceTerm?: number | null;
  acceptanceFromTradePartner?: string | null;
  reasonForFinance?: string | null;
  tradePartnerSector?: string | null;
  tradePartnerLocation?: string | null;
  tradePartnerGstComplianceScore?: string | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
  anchortrader?: Pick<IAnchorTrader, 'id' | 'atId'> | null;
  tradepartner?: Pick<ITradePartner, 'id' | 'tpId'> | null;
}

export type NewTrade = Omit<ITrade, 'id'> & { id: null };
