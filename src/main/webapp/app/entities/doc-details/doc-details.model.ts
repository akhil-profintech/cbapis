import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';

export interface IDocDetails {
  id: number;
  docDetailsId?: number | null;
  docDetailsUlidId?: string | null;
  docRecordId?: number | null;
  link?: string | null;
  description?: string | null;
  docType?: string | null;
  status?: string | null;
  financeRequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
}

export type NewDocDetails = Omit<IDocDetails, 'id'> & { id: null };
