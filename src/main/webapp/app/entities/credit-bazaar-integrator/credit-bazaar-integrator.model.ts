export interface ICreditBazaarIntegrator {
  id: number;
  integratorUlidId?: string | null;
  tenantId?: string | null;
  orgId?: string | null;
  customerName?: string | null;
  orgName?: string | null;
  gstId?: string | null;
  phoneNumber?: number | null;
}

export type NewCreditBazaarIntegrator = Omit<ICreditBazaarIntegrator, 'id'> & { id: null };
