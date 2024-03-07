import { IEscrowAccountDetails, NewEscrowAccountDetails } from './escrow-account-details.model';

export const sampleWithRequiredData: IEscrowAccountDetails = {
  id: 17502,
  tenantId: 348,
  customerId: 5051,
  accName: 'whenever gracefully',
  ifscCode: 'frugal',
  virtualAccNumber: 'overload modulo',
  poolingAccNumber: 8335,
};

export const sampleWithPartialData: IEscrowAccountDetails = {
  id: 7333,
  escrowDetailsId: 13777,
  escrowDetailsUlidId: 'that powerless',
  tenantId: 4848,
  customerId: 10467,
  accName: 'bankbook',
  ifscCode: 'distinct underneath brazen',
  virtualAccNumber: 'toy',
  poolingAccNumber: 8620,
};

export const sampleWithFullData: IEscrowAccountDetails = {
  id: 32480,
  escrowDetailsId: 8494,
  escrowDetailsUlidId: 'simple',
  tenantId: 32310,
  customerId: 28723,
  accName: 'tight lament research',
  ifscCode: 'honestly',
  virtualAccNumber: 'cork as finally',
  poolingAccNumber: 29415,
};

export const sampleWithNewData: NewEscrowAccountDetails = {
  tenantId: 29538,
  customerId: 20870,
  accName: 'how net',
  ifscCode: 'consequently',
  virtualAccNumber: 'oof yearly easy-going',
  poolingAccNumber: 10402,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
