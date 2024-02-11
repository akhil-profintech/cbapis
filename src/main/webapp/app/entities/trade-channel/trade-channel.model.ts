export interface ITradeChannel {
  id: number;
  tradeChannelId?: string | null;
  anchorTraderId?: string | null;
  tradePartnerId?: string | null;
  financePartnerId?: string | null;
  anchorTraderTenantId?: string | null;
  tradePartnerTenantId?: string | null;
  financePartnerTenantId?: string | null;
}

export type NewTradeChannel = Omit<ITradeChannel, 'id'> & { id: null };
