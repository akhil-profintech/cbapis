export interface IContext {
  id: number;
  transactionId?: string | null;
  transactionDate?: string | null;
  action?: string | null;
  userId?: string | null;
  tenantId?: string | null;
  cpCode?: string | null;
  msgpayload?: string | null;
}

export type NewContext = Omit<IContext, 'id'> & { id: null };
