import { IOrganization } from 'app/entities/organization/organization.model';

export interface IGstin {
  id: number;
  companyName?: string | null;
  gstId?: string | null;
  organization?: Pick<IOrganization, 'id' | 'orgId'> | null;
}

export type NewGstin = Omit<IGstin, 'id'> & { id: null };
