export interface IEscrowAccountDetails {
  id: number;
  escrowDetailsId?: number | null;
  escrowDetailsUlidId?: string | null;
  tenantId?: number | null;
  customerId?: number | null;
  accName?: string | null;
  ifscCode?: string | null;
  virtualAccNumber?: string | null;
  poolingAccNumber?: number | null;
}

export type NewEscrowAccountDetails = Omit<IEscrowAccountDetails, 'id'> & { id: null };
