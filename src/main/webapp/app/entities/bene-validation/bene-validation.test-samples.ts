import { IBeneValidation, NewBeneValidation } from './bene-validation.model';

export const sampleWithRequiredData: IBeneValidation = {
  id: 28980,
  benevalidationId: 'hm fertilize even',
  requestDateTime: 'trouser during',
  responseDateTime: 'phew',
  lastupdatedDateTime: 'cheat but',
  lastUpdatedBy: 'astonishing goldfish yippee',
};

export const sampleWithPartialData: IBeneValidation = {
  id: 29853,
  benevalidationId: 'pentagon conference',
  finReqId: 'geez',
  subGrpId: 'eek fuzzy boo',
  remitterName: 'following hungrily',
  remitterMobileNumber: 'gifted',
  debtorAccountId: 'until inasmuch truly',
  accountType: 'suspiciously surprised along',
  creditorAccountId: 'potentially modulo',
  ifscCode: 'that',
  paymentDescription: 'legal fax besides',
  requestDateTime: 'woot abandoned',
  metaDataStatus: 'for',
  metaDataMessage: 'frilly given quietly',
  metaDataVersion: 'decentralize before husky',
  metaDataTime: 'chard amid but',
  resourceDataCreditorAccountId: 'noted oof jockey',
  resourceDataCreditorName: 'but',
  resourceDataTransactionReferenceNumber: 'across aboard',
  resourceDataTransactionId: 'duh ack',
  resourceDataIdentifier: 'grocery',
  responseDateTime: 'earmuffs sweet',
  lastupdatedDateTime: 'mid so',
  lastUpdatedBy: 'sod gleaming',
};

export const sampleWithFullData: IBeneValidation = {
  id: 3672,
  benevalidationId: 'wean because',
  finReqId: 'hm and aw',
  subGrpId: 'versus quietly',
  remitterName: 'foolishly after glow',
  remitterMobileNumber: 'properly phooey',
  debtorAccountId: 'across haunting',
  accountType: 'mid',
  creditorAccountId: 'languish till',
  ifscCode: 'temple an without',
  paymentDescription: 'with searchingly',
  transactionReferenceNumber: 'sire except',
  merchantCode: 'math commitment salty',
  identifier: 'illustrious ugh',
  requestDateTime: 'condone',
  metaDataStatus: 'atop for',
  metaDataMessage: 'ringed with oh',
  metaDataVersion: 'gigantic that',
  metaDataTime: 'gallon',
  resourceDataCreditorAccountId: 'yum',
  resourceDataCreditorName: 'yak shed handgun',
  resourceDataTransactionReferenceNumber: 'horn yippee shocked',
  resourceDataTransactionId: 'even gracefully apud',
  resourceDataResponseCode: 'even oh absent',
  resourceDataTransactionTime: 'daily sweetly',
  resourceDataIdentifier: 'vision',
  responseDateTime: 'wherever aw vice',
  lastupdatedDateTime: 'stagnate joshingly whether',
  lastUpdatedBy: 'why',
};

export const sampleWithNewData: NewBeneValidation = {
  benevalidationId: 'satisfaction given',
  requestDateTime: 'unlike',
  responseDateTime: 'against',
  lastupdatedDateTime: 'understated hollow however',
  lastUpdatedBy: 'provided whimper vacantly',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
