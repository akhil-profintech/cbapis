import { ICBCREProcess, NewCBCREProcess } from './cbcre-process.model';

export const sampleWithRequiredData: ICBCREProcess = {
  id: 83,
};

export const sampleWithPartialData: ICBCREProcess = {
  id: 29297,
  cbProcessId: 9493,
  cbProcessUlidId: 'however often fortunately',
  anchorTraderId: 'immediately noxious',
  financeAmount: 'vice yowza',
  processDateTime: 'under',
  responseDateTime: 'muse into',
  creRequestId: 'indeed competent',
  cumilativeTradeScore: 10222.85,
  totalAmountRequested: 24529,
  totalInvoiceAmount: 12141,
};

export const sampleWithFullData: ICBCREProcess = {
  id: 25544,
  cbProcessId: 25211,
  cbProcessUlidId: 'untrue aside',
  cbProcessRefNo: 'builder amongst',
  anchorTraderId: 'criminal',
  anchorTraderGst: 'greatly upside-down incomparable',
  financeAmount: 'owlishly uh-huh',
  processDateTime: 'during blissfully the',
  creProcessStatus: 'eek excellent',
  responseDateTime: 'quickly spleen hmph',
  creRequestId: 'above than',
  cumilativeTradeScore: 17485.23,
  timestamp: 'micturate inhere',
  totalAmountRequested: 12937,
  totalInvoiceAmount: 13248,
};

export const sampleWithNewData: NewCBCREProcess = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
