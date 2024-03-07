export interface IAction {
  id: number;
  actionId?: string | null;
  actionCode?: string | null;
  actionDescription?: string | null;
  actionVal?: string | null;
  cpCode?: string | null;
}

export type NewAction = Omit<IAction, 'id'> & { id: null };
