import { IVANumber, NewVANumber } from './va-number.model';

export const sampleWithRequiredData: IVANumber = {
  id: 4757,
  vaId: 29192,
  poolingAccNumber: 'duh age bass',
  virtualAccNumber: 'rediscover afore whereas',
  vaStatus: 'like limp',
};

export const sampleWithPartialData: IVANumber = {
  id: 1031,
  vaId: 1408,
  poolingAccNumber: 'whereas',
  virtualAccNumber: 'oh meh aha',
  vaStatus: 'tiny contort instead',
  financeRequestId: 29291,
  subGroupId: 'impound rightfully',
};

export const sampleWithFullData: IVANumber = {
  id: 3663,
  vaId: 23579,
  poolingAccNumber: 'frenetically entail',
  virtualAccNumber: 'under correctly',
  vaStatus: 'grandmom',
  financeRequestId: 15061,
  subGroupId: 'likewise whoa',
};

export const sampleWithNewData: NewVANumber = {
  vaId: 12441,
  poolingAccNumber: 'elimination',
  virtualAccNumber: 'before',
  vaStatus: 'however successfully',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
