export interface IFinanceRequestMapping {
  id: number;
  financeRequestId?: number | null;
  financeRequestMappingUlidId?: string | null;
  anchorTraderId?: string | null;
  financePartnerId?: string | null;
  anchorTraderTenantId?: string | null;
  financePartnerTenantId?: string | null;
}

export type NewFinanceRequestMapping = Omit<IFinanceRequestMapping, 'id'> & { id: null };
