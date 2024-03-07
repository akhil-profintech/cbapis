import dayjs from 'dayjs/esm';

export interface IProspectRequest {
  id: number;
  prospectRequestId?: number | null;
  prospectRequestUlidId?: string | null;
  anchorTraderId?: number | null;
  requestAmount?: string | null;
  prospectRequestDate?: dayjs.Dayjs | null;
  currency?: string | null;
}

export type NewProspectRequest = Omit<IProspectRequest, 'id'> & { id: null };
