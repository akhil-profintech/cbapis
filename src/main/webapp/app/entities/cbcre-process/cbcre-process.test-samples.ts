import { ICBCREProcess, NewCBCREProcess } from './cbcre-process.model';

export const sampleWithRequiredData: ICBCREProcess = {
  id: 16180,
};

export const sampleWithPartialData: ICBCREProcess = {
  id: 24939,
  cbProcessId: 15333,
  cbProcessRefNo: 'termite but likely',
  anchorTraderGst: 'dip easy',
  financeAmount: 'data furiously',
  creProcessStatus: 'ally acrobatic',
  responseDateTime: 'swill',
  creRequestId: 'paginate reversal near',
  timestamp: 'recede furthermore',
  totalAmountRequested: 21968,
  status: 'lamb how while',
};

export const sampleWithFullData: ICBCREProcess = {
  id: 6286,
  cbProcessId: 7605,
  cbProcessUlidId: 'ah',
  cbProcessRefNo: 'incomparable flat provided',
  anchorTraderId: 'ha except truly',
  anchorTraderGst: 'questioningly mindless duh',
  financeAmount: 'till',
  processDateTime: 'hmph behind silently',
  creProcessStatus: 'on cruelly',
  responseDateTime: 'deglaze psst',
  creRequestId: 'brr',
  cumilativeTradeScore: 15789.16,
  timestamp: 'gah furthermore',
  totalAmountRequested: 21873,
  totalInvoiceAmount: 14564,
  status: 'where fiberglass ugh',
};

export const sampleWithNewData: NewCBCREProcess = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
