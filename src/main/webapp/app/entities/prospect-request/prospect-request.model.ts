import dayjs from 'dayjs/esm';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';

export interface IProspectRequest {
  id: number;
  prospectRequestId?: number | null;
  anchorTraderId?: number | null;
  requestAmount?: string | null;
  prospectRequestDate?: dayjs.Dayjs | null;
  currency?: string | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'requestId'> | null;
}

export type NewProspectRequest = Omit<IProspectRequest, 'id'> & { id: null };
