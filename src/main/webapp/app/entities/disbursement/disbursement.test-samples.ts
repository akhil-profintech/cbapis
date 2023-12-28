import dayjs from 'dayjs/esm';

import { IDisbursement, NewDisbursement } from './disbursement.model';

export const sampleWithRequiredData: IDisbursement = {
  id: 7821,
  acceptedOfferId: 834,
  offerId: 28101,
  offerAcceptedDate: dayjs('2023-12-28'),
  dbmtRequestId: 'correctly relocate finalise',
  dbmtReqAmount: 6981,
  currency: 'quaintly nor gnash',
  dbmtRequestDate: dayjs('2023-12-28'),
  dbmtstatus: 'virtual rash blah',
};

export const sampleWithPartialData: IDisbursement = {
  id: 7558,
  dbmtId: 'vigilant sustain ingest',
  acceptedOfferId: 15770,
  offerId: 28308,
  offerAcceptedDate: dayjs('2023-12-28'),
  dbmtRequestId: 'whereas gosh',
  dbmtReqAmount: 26758,
  currency: 'uh-huh',
  dbmtRequestDate: dayjs('2023-12-28'),
  dbmtstatus: 'though for',
  offerStatus: 'opposite psst grandmom',
  ftTrnxDetailsId: 'on mundane',
  docId: 17716,
  financeRequestId: 'desensitize slowly',
  destinationAccountName: 'prepone',
  destinationAccountNumber: 'that below bleakly',
  status: 'authenticate supposing',
  actionByDate: 'tarragon',
};

export const sampleWithFullData: IDisbursement = {
  id: 10526,
  dbmtId: 'indeed cake plunder',
  disbursementRefNo: 'pish oh',
  acceptedOfferId: 12816,
  offerId: 7811,
  offerAcceptedDate: dayjs('2023-12-27'),
  dbmtRequestId: 'boohoo',
  dbmtReqAmount: 16596,
  currency: 'coolly',
  dbmtRequestDate: dayjs('2023-12-27'),
  dbmtstatus: 'provided provided oh',
  offerStatus: 'decimal',
  ftTrnxDetailsId: 'until junior',
  collectionTrnxDetailsId: 'into',
  docId: 8600,
  dbmtDateTime: 'uselessly considering',
  dbmtAmount: 20706,
  financeRequestId: 'instead courageously unbearably',
  amountToBeDisbursed: 'noisily though',
  destinationAccountName: 'chowder',
  destinationAccountNumber: 'except sterilise',
  ifscCode: 'instead fearful until',
  status: 'asset elaborate',
  actionByDate: 'intently artistic yowza',
};

export const sampleWithNewData: NewDisbursement = {
  acceptedOfferId: 11937,
  offerId: 1137,
  offerAcceptedDate: dayjs('2023-12-27'),
  dbmtRequestId: 'veneer until whoever',
  dbmtReqAmount: 23305,
  currency: 'aside shabby',
  dbmtRequestDate: dayjs('2023-12-28'),
  dbmtstatus: 'circumstance',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
