import dayjs from 'dayjs/esm';

import { IFinanceRequest, NewFinanceRequest } from './finance-request.model';

export const sampleWithRequiredData: IFinanceRequest = {
  id: 30833,
  requestAmount: 'provided above',
  requestDate: dayjs('2024-03-15'),
  currency: 'gift hazelnut',
  requestStatus: 'specialise divide',
  dueDate: dayjs('2024-03-15'),
};

export const sampleWithPartialData: IFinanceRequest = {
  id: 14070,
  financeRequestId: 24413,
  financeRequestRefNo: 'while',
  tradeChannelId: 'excitedly',
  requestAmount: 'medicine consequently blazon',
  requestDate: dayjs('2024-03-14'),
  currency: 'save meteorology soap',
  requestStatus: 'halt',
  dueDate: dayjs('2024-03-14'),
  gstConsent: true,
};

export const sampleWithFullData: IFinanceRequest = {
  id: 31918,
  financeRequestId: 27062,
  financeRequestUlidId: 'phooey victoriously kissingly',
  financeRequestRefNo: 'fairly even',
  tradeChannelId: 'distinct supporter whether',
  requestAmount: 'cauterize',
  requestDate: dayjs('2024-03-14'),
  currency: 'more',
  requestStatus: 'extra-large yearningly',
  dueDate: dayjs('2024-03-15'),
  gstConsent: true,
};

export const sampleWithNewData: NewFinanceRequest = {
  requestAmount: 'stand less',
  requestDate: dayjs('2024-03-15'),
  currency: 'nail sarcastic',
  requestStatus: 'curiously whoever holiday',
  dueDate: dayjs('2024-03-14'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
