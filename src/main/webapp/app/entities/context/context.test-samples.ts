import dayjs from 'dayjs/esm';

import { IContext, NewContext } from './context.model';

export const sampleWithRequiredData: IContext = {
  id: 21246,
  transactionId: 1398,
  transactionDate: dayjs('2023-12-27T13:28'),
  clientId: 1633,
  cpCode: 'miscount',
};

export const sampleWithPartialData: IContext = {
  id: 23753,
  transactionId: 15535,
  transactionDate: dayjs('2023-12-27T22:24'),
  clientId: 13013,
  cpCode: 'that',
};

export const sampleWithFullData: IContext = {
  id: 19517,
  transactionId: 21,
  transactionDate: dayjs('2023-12-27T21:03'),
  clientId: 16622,
  cpCode: 'manipulate unto',
};

export const sampleWithNewData: NewContext = {
  transactionId: 25950,
  transactionDate: dayjs('2023-12-28T01:11'),
  clientId: 18851,
  cpCode: 'orchard pish frantically',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
