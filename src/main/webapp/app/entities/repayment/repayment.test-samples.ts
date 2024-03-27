import dayjs from 'dayjs/esm';

import { IRepayment, NewRepayment } from './repayment.model';

export const sampleWithRequiredData: IRepayment = {
  id: 14767,
  acceptedOfferUlidId: 'whoever intensely vibrate',
  placedOfferUlidId: 'instead',
  reqOffUlidId: 'eventually thank',
  offerAcceptedDate: dayjs('2024-03-27'),
  dbmtRequestId: 'until',
  dbmtStatus: 'gosh scare soulmate',
  dbmtDateTime: 'aha mechanically jovially',
  dbmtId: 19041,
  dbmtAmount: 2654,
  currency: 'yippee consequently ack',
  repaymentStatus: 'redraft hovercraft boo',
};

export const sampleWithPartialData: IRepayment = {
  id: 27673,
  repaymentId: 9823,
  acceptedOfferUlidId: 'yippee provided yum',
  placedOfferUlidId: 'lazily',
  reqOffUlidId: 'anti',
  offerAcceptedDate: dayjs('2024-03-26'),
  dbmtRequestId: 'of boldly as',
  dbmtStatus: 'regionalism bowlful',
  dbmtDateTime: 'dutiful eek above',
  dbmtId: 119,
  dbmtAmount: 14135,
  currency: 'though than',
  repaymentStatus: 'aircraft whale',
  repaymentDate: dayjs('2024-03-26'),
  totalRepaymentAmount: 'deputize court',
  amountRepayed: 'ligate',
  sourceAccountNumber: 'ha',
  ifscCode: 'likewise orderly',
  referenceNumber: 'textual to',
};

export const sampleWithFullData: IRepayment = {
  id: 31197,
  repaymentId: 6789,
  repaymentUlidId: 'however',
  repaymentRefNo: 'ack',
  acceptedOfferUlidId: 'intrepid',
  placedOfferUlidId: 'host',
  reqOffUlidId: 'yowza knowledgeably unequaled',
  offerAcceptedDate: dayjs('2024-03-26'),
  dbmtRequestId: 'during base',
  dbmtStatus: 'percent disgusting indeed',
  dbmtDateTime: 'slum lag procure',
  dbmtId: 3856,
  dbmtAmount: 1282,
  currency: 'coevolution',
  repaymentStatus: 'confer chillax with',
  repaymentDate: dayjs('2024-03-27'),
  repaymentAmount: 23051,
  financeRequestId: 18711,
  repaymentDueDate: dayjs('2024-03-27'),
  totalRepaymentAmount: 'plain',
  amountRepayed: 'voluminous terribly',
  amountToBePaid: 'besides overconfidently lovingly',
  sourceAccountName: 'which',
  sourceAccountNumber: 'ostracize unexpectedly per',
  ifscCode: 'although sequester how',
  recordStatus: 'education',
  referenceNumber: 'apud',
};

export const sampleWithNewData: NewRepayment = {
  acceptedOfferUlidId: 'geez yahoo scrap',
  placedOfferUlidId: 'livid hm wade',
  reqOffUlidId: 'whereas than ah',
  offerAcceptedDate: dayjs('2024-03-27'),
  dbmtRequestId: 'indent subdued creativity',
  dbmtStatus: 'safely silently ick',
  dbmtDateTime: 'toward unfortunately',
  dbmtId: 18482,
  dbmtAmount: 6235,
  currency: 'handover',
  repaymentStatus: 'outstanding curl woot',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
