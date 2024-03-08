import dayjs from 'dayjs/esm';

import { IProspectRequest, NewProspectRequest } from './prospect-request.model';

export const sampleWithRequiredData: IProspectRequest = {
  id: 14074,
  anchorTraderId: 24081,
  requestAmount: 'within',
  prospectRequestDate: dayjs('2024-03-07'),
  currency: 'testy odyssey diagnosis',
};

export const sampleWithPartialData: IProspectRequest = {
  id: 12590,
  prospectRequestId: 30239,
  prospectRequestUlidId: 'pattern typify petty',
  anchorTraderId: 20275,
  requestAmount: 'pharmacist tadpole',
  prospectRequestDate: dayjs('2024-03-07'),
  currency: 'valid wisely while',
};

export const sampleWithFullData: IProspectRequest = {
  id: 26936,
  prospectRequestId: 28803,
  prospectRequestUlidId: 'zowie zowie',
  anchorTraderId: 28496,
  requestAmount: 'now',
  prospectRequestDate: dayjs('2024-03-07'),
  currency: 'unfortunately',
};

export const sampleWithNewData: NewProspectRequest = {
  anchorTraderId: 24733,
  requestAmount: 'excepting',
  prospectRequestDate: dayjs('2024-03-07'),
  currency: 'than',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
