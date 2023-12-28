import { IInstaAlert, NewInstaAlert } from './insta-alert.model';

export const sampleWithRequiredData: IInstaAlert = {
  id: 25905,
  instaAlertId: 28376,
  lastupdatedDateTime: 'academic',
  lastUpdatedBy: 'whether gussy',
};

export const sampleWithPartialData: IInstaAlert = {
  id: 23459,
  instaAlertId: 21269,
  customerCode: 'mechanically',
  customerName: 'fairly',
  creditDate: 'ack gah fossilize',
  utrNo: 'um selfishly studious',
  remitterAccountNumber: 'who treasury ew',
  ifscCode: 'shabby dolphin greedily',
  lastupdatedDateTime: 'shatter',
  lastUpdatedBy: 'darling extort',
};

export const sampleWithFullData: IInstaAlert = {
  id: 15492,
  instaAlertId: 13708,
  finReqId: 'inasmuch',
  subGrpId: 'eager',
  customerCode: 'simplistic',
  customerName: 'reign bassoon',
  productCode: 'oh irresponsible',
  poolingAccountNumber: 'absent behind',
  transactionType: 'pfft oh',
  batchAmt: 6632,
  batchAmtCcd: 'upright however',
  creditDate: 'a',
  vaNumber: 'posh',
  utrNo: 'caring',
  creditGenerationTime: 'furthermore scavenge',
  remitterName: 'searchingly',
  remitterAccountNumber: 'quirkily',
  ifscCode: 'anti townhouse within',
  lastupdatedDateTime: 'pish recklessly',
  lastUpdatedBy: 'aw snooper wherever',
};

export const sampleWithNewData: NewInstaAlert = {
  instaAlertId: 10859,
  lastupdatedDateTime: 'before',
  lastUpdatedBy: 'boss',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
