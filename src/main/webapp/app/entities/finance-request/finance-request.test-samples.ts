import dayjs from 'dayjs/esm';

import { IFinanceRequest, NewFinanceRequest } from './finance-request.model';

export const sampleWithRequiredData: IFinanceRequest = {
  id: 17801,
  requestAmount: 'uh-huh',
  requestDate: dayjs('2023-12-28'),
  currency: 'via ack',
  requestStatus: 'grave',
  dueDate: dayjs('2023-12-28'),
};

export const sampleWithPartialData: IFinanceRequest = {
  id: 13720,
  requestId: 'necessary',
  financeRequestRefNo: 'ack',
  requestAmount: 'certainly',
  requestDate: dayjs('2023-12-27'),
  currency: 'buy ugh',
  requestStatus: 'boastfully',
  dueDate: dayjs('2023-12-28'),
};

export const sampleWithFullData: IFinanceRequest = {
  id: 15019,
  requestId: 'as',
  financeRequestRefNo: 'finally',
  requestAmount: 'phew',
  requestDate: dayjs('2023-12-27'),
  currency: 'afraid halt transit',
  requestStatus: 'gee',
  dueDate: dayjs('2023-12-27'),
};

export const sampleWithNewData: NewFinanceRequest = {
  requestAmount: 'broadly coarse cry',
  requestDate: dayjs('2023-12-28'),
  currency: 'lustrous',
  requestStatus: 'gosh fiercely',
  dueDate: dayjs('2023-12-27'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
