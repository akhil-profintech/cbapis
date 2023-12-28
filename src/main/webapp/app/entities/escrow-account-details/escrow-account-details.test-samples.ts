import { IEscrowAccountDetails, NewEscrowAccountDetails } from './escrow-account-details.model';

export const sampleWithRequiredData: IEscrowAccountDetails = {
  id: 15235,
  escrowDetailsId: 17502,
  tenantId: 348,
  customerId: 5051,
  accName: 'whenever gracefully',
  ifscCode: 'frugal',
  virtualAccNumber: 'overload modulo',
  poolingAccNumber: 8335,
};

export const sampleWithPartialData: IEscrowAccountDetails = {
  id: 959,
  escrowDetailsId: 11314,
  tenantId: 7333,
  customerId: 13777,
  accName: 'that powerless',
  ifscCode: 'pace',
  virtualAccNumber: 'unaccountably bah',
  poolingAccNumber: 21630,
};

export const sampleWithFullData: IEscrowAccountDetails = {
  id: 29126,
  escrowDetailsId: 4484,
  tenantId: 29096,
  customerId: 23654,
  accName: 'swiftly how skive',
  ifscCode: 'whose elegant forget',
  virtualAccNumber: 'scarcely although',
  poolingAccNumber: 13784,
};

export const sampleWithNewData: NewEscrowAccountDetails = {
  escrowDetailsId: 12567,
  tenantId: 21968,
  customerId: 6790,
  accName: 'whose while',
  ifscCode: 'aha now sans',
  virtualAccNumber: 'closely',
  poolingAccNumber: 15062,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
