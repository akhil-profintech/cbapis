import { ITrade } from 'app/entities/trade/trade.model';

export interface ITradePartner {
  id: number;
  tpId?: number | null;
  tpUlidId?: string | null;
  orgId?: string | null;
  tenantId?: string | null;
  customerName?: string | null;
  orgName?: string | null;
  gstId?: string | null;
  phoneNumber?: number | null;
  tradePartnerName?: string | null;
  location?: string | null;
  tradePartnerGST?: string | null;
  tradePartnerSector?: string | null;
  acceptanceFromTradePartner?: string | null;
  tosDocument?: string | null;
  trades?: Pick<ITrade, 'id'>[] | null;
}

export type NewTradePartner = Omit<ITradePartner, 'id'> & { id: null };
