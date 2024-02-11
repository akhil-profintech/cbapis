export interface ITenantDetails {
  id: number;
  tenantId?: string | null;
  tenantSchema?: string | null;
}

export type NewTenantDetails = Omit<ITenantDetails, 'id'> & { id: null };
