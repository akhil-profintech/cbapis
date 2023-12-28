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
  financeRequestId: 'hm foxglove overestimate',
  subGroupId: 'access elbow self-esteem',
};

export const sampleWithFullData: IVANumber = {
  id: 16690,
  vaId: 27902,
  poolingAccNumber: 'yum',
  virtualAccNumber: 'boohoo',
  vaStatus: 'out',
  financeRequestId: 'outrun however',
  subGroupId: 'after cooked',
};

export const sampleWithNewData: NewVANumber = {
  vaId: 2513,
  poolingAccNumber: 'after',
  virtualAccNumber: 'absent',
  vaStatus: 'gallop meh',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
