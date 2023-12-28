import dayjs from 'dayjs/esm';

import { IProspectRequest, NewProspectRequest } from './prospect-request.model';

export const sampleWithRequiredData: IProspectRequest = {
  id: 30493,
  prospectRequestId: 14074,
  anchorTraderId: 24081,
  requestAmount: 'within',
  prospectRequestDate: dayjs('2023-12-28'),
  currency: 'testy odyssey diagnosis',
};

export const sampleWithPartialData: IProspectRequest = {
  id: 3442,
  prospectRequestId: 8726,
  anchorTraderId: 12590,
  requestAmount: 'quarrelsomely potentially unwritten',
  prospectRequestDate: dayjs('2023-12-27'),
  currency: 'beside ack',
};

export const sampleWithFullData: IProspectRequest = {
  id: 28987,
  prospectRequestId: 30047,
  anchorTraderId: 24611,
  requestAmount: 'silky meditate uh-huh',
  prospectRequestDate: dayjs('2023-12-27'),
  currency: 'zowie zowie',
};

export const sampleWithNewData: NewProspectRequest = {
  prospectRequestId: 28496,
  anchorTraderId: 5208,
  requestAmount: 'aw',
  prospectRequestDate: dayjs('2023-12-28'),
  currency: 'ouch penny fooey',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
