export interface ITenantDetails {
  id: number;
  tenantUlidId?: string | null;
  tenantSchema?: string | null;
}

export type NewTenantDetails = Omit<ITenantDetails, 'id'> & { id: null };
