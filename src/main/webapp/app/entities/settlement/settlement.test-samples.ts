import { ISettlement, NewSettlement } from './settlement.model';

export const sampleWithRequiredData: ISettlement = {
  id: 15712,
  placedOfferUlidId: 'severe pony who',
  reqOffUlidId: 'relinquish hence',
  dbmtRequestId: 22412,
  dbmtId: 31660,
  dbmtDate: 'tic wherever dimple',
  dbmtStatus: 'dollarise',
  dbmtAmount: 22791,
  currency: 'how esteem',
};

export const sampleWithPartialData: ISettlement = {
  id: 7483,
  settlementId: 26765,
  placedOfferUlidId: 'excepting vacantly',
  reqOffUlidId: 'huzzah',
  dbmtRequestId: 25689,
  dbmtId: 12980,
  dbmtDate: 'ick meanwhile amusing',
  dbmtStatus: 'very',
  dbmtAmount: 16711,
  currency: 'dispossess',
};

export const sampleWithFullData: ISettlement = {
  id: 790,
  settlementId: 26915,
  settlementUlidId: 'epithelium',
  settlementRefNo: 'boohoo uh-huh oh',
  acceptedOfferUlidId: 'mattock',
  placedOfferUlidId: 'outgoing',
  reqOffUlidId: 'energetically supposing claw',
  dbmtRequestId: 32588,
  dbmtId: 13449,
  dbmtDate: 'outside yum',
  dbmtStatus: 'viciously secondary',
  dbmtAmount: 4605,
  currency: 'where phew',
  recordStatus: 'furthermore',
};

export const sampleWithNewData: NewSettlement = {
  placedOfferUlidId: 'even',
  reqOffUlidId: 'below',
  dbmtRequestId: 6801,
  dbmtId: 31778,
  dbmtDate: 'gleeful yowza',
  dbmtStatus: 'obediently',
  dbmtAmount: 5216,
  currency: 'upstage kit gently',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
