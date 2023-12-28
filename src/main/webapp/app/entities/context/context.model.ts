import dayjs from 'dayjs/esm';
import { IAction } from 'app/entities/action/action.model';

export interface IContext {
  id: number;
  transactionId?: number | null;
  transactionDate?: dayjs.Dayjs | null;
  clientId?: number | null;
  cpCode?: string | null;
  action?: Pick<IAction, 'id' | 'actionVal'> | null;
}

export type NewContext = Omit<IContext, 'id'> & { id: null };
