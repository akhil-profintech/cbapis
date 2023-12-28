export interface IClientCodes {
  id: number;
  clientCode?: number | null;
  clientCharsCode?: string | null;
  clientName?: string | null;
}

export type NewClientCodes = Omit<IClientCodes, 'id'> & { id: null };
