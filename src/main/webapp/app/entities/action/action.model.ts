import { IContext } from 'app/entities/context/context.model';

export interface IAction {
  id: number;
  actionId?: number | null;
  actionCode?: string | null;
  actionDescription?: string | null;
  actionVal?: string | null;
  cpCode?: string | null;
  contexts?: Pick<IContext, 'id'>[] | null;
}

export type NewAction = Omit<IAction, 'id'> & { id: null };
