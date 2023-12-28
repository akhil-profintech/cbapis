import dayjs from 'dayjs/esm';

import { IRepayment, NewRepayment } from './repayment.model';

export const sampleWithRequiredData: IRepayment = {
  id: 23209,
  acceptedOfferId: 23400,
  offerId: 9986,
  offerAcceptedDate: dayjs('2023-12-28'),
  dbmtRequestId: 'bashfully',
  dbmtstatus: 'carbohydrate helpfully creepy',
  dbmtDateTime: 'even but',
  dbmtId: 'forenenst',
  dbmtAmount: 31606,
  currency: 'hourly whose',
  repaymentStatus: 'longingly yet unhappy',
};

export const sampleWithPartialData: IRepayment = {
  id: 16624,
  repaymentRefNo: 'mandarin',
  acceptedOfferId: 26313,
  offerId: 29295,
  offerAcceptedDate: dayjs('2023-12-28'),
  dbmtRequestId: 'tepid sick',
  dbmtstatus: 'mid',
  dbmtDateTime: 'pointed',
  dbmtId: 'assay',
  dbmtAmount: 31087,
  currency: 'excited',
  repaymentStatus: 'absent',
  repaymentDate: dayjs('2023-12-27'),
  ftTrnxDetailsId: 'put painter righteously',
  docId: 11374,
  financeRequestId: 'mass woot',
  repaymentDueDate: dayjs('2023-12-28'),
  amountRepayed: 'ha where',
  ifscCode: 'response',
  status: 'where scarcely deeply',
  referenceNumber: 'bop',
};

export const sampleWithFullData: IRepayment = {
  id: 19015,
  repaymentId: 'honorable times',
  repaymentRefNo: 'gleefully',
  acceptedOfferId: 31921,
  offerId: 28398,
  offerAcceptedDate: dayjs('2023-12-28'),
  dbmtRequestId: 'below',
  dbmtstatus: 'oh ha',
  dbmtDateTime: 'likewise orderly',
  dbmtId: 'textual to',
  dbmtAmount: 31197,
  currency: 'only',
  repaymentStatus: 'towards',
  repaymentDate: dayjs('2023-12-27'),
  repaymentAmount: 738,
  ftTrnxDetailsId: 'boastfully',
  collectionTrnxDetailsId: 'reason yowza knowledgeably',
  docId: 16172,
  financeRequestId: 'greedy during',
  repaymentDueDate: dayjs('2023-12-28'),
  totalRepaymentAmount: 'whose instead',
  amountRepayed: 'pony neck lessen',
  amountToBePaid: 'murmuring although aw',
  sourceAccountName: 'amidst',
  sourceAccountNumber: 'chillax',
  ifscCode: 'faraway',
  status: 'till convection slow',
  referenceNumber: 'regarding aw',
};

export const sampleWithNewData: NewRepayment = {
  acceptedOfferId: 17521,
  offerId: 1409,
  offerAcceptedDate: dayjs('2023-12-28'),
  dbmtRequestId: 'lovingly extremely',
  dbmtstatus: 'politely honestly offensively',
  dbmtDateTime: 'for once seemingly',
  dbmtId: 'blah',
  dbmtAmount: 9688,
  currency: 'apud',
  repaymentStatus: 'geez yahoo scrap',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
