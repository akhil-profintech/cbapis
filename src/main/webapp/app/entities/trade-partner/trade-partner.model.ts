import { ITrade } from 'app/entities/trade/trade.model';

export interface ITradePartner {
  id: number;
  tenantId?: string | null;
  tpId?: string | null;
  orgId?: string | null;
  customerName?: string | null;
  orgName?: string | null;
  gstId?: string | null;
  phoneNumber?: number | null;
  tradePartnerName?: string | null;
  location?: string | null;
  tradepartnerGST?: string | null;
  tradePartnerSector?: string | null;
  acceptanceFromTradePartner?: string | null;
  trades?: Pick<ITrade, 'id'>[] | null;
}

export type NewTradePartner = Omit<ITradePartner, 'id'> & { id: null };
