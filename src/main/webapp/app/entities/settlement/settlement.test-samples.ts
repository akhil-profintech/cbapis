import { ISettlement, NewSettlement } from './settlement.model';

export const sampleWithRequiredData: ISettlement = {
  id: 2263,
  placedOfferUlidId: 'round loudly',
  reqOffUlidId: 'who load wherever',
  dbmtRequestId: 8342,
  dbmtId: 22412,
  dbmtDate: 'roar gadzooks spiderling',
  dbmtStatus: 'within',
  dbmtAmount: 8866,
  currency: 'beneath throughout astride',
};

export const sampleWithPartialData: ISettlement = {
  id: 15469,
  placedOfferUlidId: 'sanitize',
  reqOffUlidId: 'microblog prospect neon',
  dbmtRequestId: 24272,
  dbmtId: 24137,
  dbmtDate: 'waterlogged',
  dbmtStatus: 'that inquisitively',
  dbmtAmount: 16700,
  currency: 'shear strut',
};

export const sampleWithFullData: ISettlement = {
  id: 26132,
  settlementId: 3036,
  settlementUlidId: 'atomise',
  settlementRefNo: 'madly',
  acceptedOfferUlidId: 'huzzah ladder damaged',
  placedOfferUlidId: 'hm mmm',
  reqOffUlidId: 'because',
  dbmtRequestId: 597,
  dbmtId: 22318,
  dbmtDate: 'alongside altruistic given',
  dbmtStatus: 'sans yahoo urgently',
  dbmtAmount: 18019,
  currency: 'save boohoo',
};

export const sampleWithNewData: NewSettlement = {
  placedOfferUlidId: 'woefully understated',
  reqOffUlidId: 'yowza apud',
  dbmtRequestId: 17524,
  dbmtId: 5216,
  dbmtDate: 'upstage kit gently',
  dbmtStatus: 'altruistic wound',
  dbmtAmount: 14196,
  currency: 'inquisitively',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
