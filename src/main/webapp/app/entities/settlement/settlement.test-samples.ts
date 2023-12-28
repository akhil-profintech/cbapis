import { ISettlement, NewSettlement } from './settlement.model';

export const sampleWithRequiredData: ISettlement = {
  id: 19206,
  offerId: 28402,
  dbmtRequestId: 15304,
  dbmtId: 2263,
  dbmtDate: 'round loudly',
  dbmtStatus: 'who load wherever',
  dbmtAmount: 8342,
  currency: 'yuck endive fabulous',
};

export const sampleWithPartialData: ISettlement = {
  id: 3976,
  settlementRefNo: 'recognize beneath',
  offerId: 8608,
  dbmtRequestId: 10406,
  dbmtId: 17139,
  dbmtDate: 'unless',
  dbmtStatus: 'known eek thongs',
  dbmtAmount: 12106,
  currency: 'prospect neon',
};

export const sampleWithFullData: ISettlement = {
  id: 24272,
  settlementId: 'mould correctly animated',
  settlementRefNo: 'almost',
  offerId: 26915,
  dbmtRequestId: 7652,
  dbmtId: 26056,
  dbmtDate: 'where',
  dbmtStatus: 'after',
  dbmtAmount: 1359,
  currency: 'madly',
};

export const sampleWithNewData: NewSettlement = {
  offerId: 28468,
  dbmtRequestId: 1375,
  dbmtId: 782,
  dbmtDate: 'mattock',
  dbmtStatus: 'outgoing',
  dbmtAmount: 27191,
  currency: 'intelligence against',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
