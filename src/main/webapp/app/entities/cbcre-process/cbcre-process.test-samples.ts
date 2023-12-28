import { ICBCREProcess, NewCBCREProcess } from './cbcre-process.model';

export const sampleWithRequiredData: ICBCREProcess = {
  id: 13419,
};

export const sampleWithPartialData: ICBCREProcess = {
  id: 9493,
  financeRequestId: 'however often fortunately',
  anchortTraderGst: 'immediately noxious',
  financeAmount: 'vice yowza',
  creProcessStatus: 'under',
  responseDateTime: 'muse into',
  creRequestId: 'indeed competent',
  timestamp: 'unfolded',
  totalAmountRequested: 18300,
};

export const sampleWithFullData: ICBCREProcess = {
  id: 16896,
  cbProcessId: 'battleship till',
  financeRequestId: 'lamb how while',
  anchorTraderId: 'sausage',
  anchortTraderGst: 'why overcome aside',
  financeAmount: 'however digress cursor',
  processDateTime: 'pfft',
  creProcessStatus: 'unfolded so',
  responseDateTime: 'giving chassis gadzooks',
  creRequestId: 'dehumanize',
  cumilativetradescore: 24208.46,
  timestamp: 'on cruelly',
  totalAmountRequested: 15630,
  totalInvoiceAmount: 14809,
};

export const sampleWithNewData: NewCBCREProcess = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
