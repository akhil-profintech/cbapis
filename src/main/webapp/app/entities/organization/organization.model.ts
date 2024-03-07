import { IGstin } from 'app/entities/gstin/gstin.model';

export interface IOrganization {
  id: number;
  orgId?: number | null;
  orgUlidId?: string | null;
  orgName?: string | null;
  orgAddress?: string | null;
  industryType?: string | null;
  tenantId?: string | null;
  gstins?: Pick<IGstin, 'id'>[] | null;
}

export type NewOrganization = Omit<IOrganization, 'id'> & { id: null };
