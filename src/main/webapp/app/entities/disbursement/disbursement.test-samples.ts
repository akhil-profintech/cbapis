import dayjs from 'dayjs/esm';

import { IDisbursement, NewDisbursement } from './disbursement.model';

export const sampleWithRequiredData: IDisbursement = {
  id: 10433,
  acceptedOfferUlidId: 'truly',
  placedOfferUlidId: 'trailer',
  reqOffUlidId: 'a petition',
  offerAcceptedDate: dayjs('2024-03-07'),
  dbmtRequestId: 'nun through yummy',
  dbmtReqAmount: 13083,
  currency: 'smooth because softball',
  dbmtRequestDate: dayjs('2024-03-07'),
  dbmtStatus: 'behind regarding nor',
};

export const sampleWithPartialData: IDisbursement = {
  id: 11634,
  disbursementUlidId: 'pfft',
  acceptedOfferUlidId: 'atop',
  placedOfferUlidId: 'lone grimy aw',
  reqOffUlidId: 'coarse',
  offerAcceptedDate: dayjs('2024-03-07'),
  dbmtRequestId: 'masonry opposite',
  dbmtReqAmount: 3762,
  currency: 'step-mother private',
  dbmtRequestDate: dayjs('2024-03-07'),
  dbmtStatus: 'smoothly lest standpoint',
  offerStatus: 'catch silver',
  docId: 14522,
  dbmtDateTime: 'mmm ah',
  dbmtAmount: 20787,
  financeRequestId: 31446,
  destinationAccountName: 'unto',
  destinationAccountNumber: 'fatal aw tank',
  actionByDate: 'how',
};

export const sampleWithFullData: IDisbursement = {
  id: 7818,
  dbmtId: 32687,
  disbursementUlidId: 'brr strip furthermore',
  disbursementRefNo: 'yet',
  acceptedOfferUlidId: 'provided provided oh',
  placedOfferUlidId: 'decimal',
  reqOffUlidId: 'until junior',
  offerAcceptedDate: dayjs('2024-03-07'),
  dbmtRequestId: 'disqualify',
  dbmtReqAmount: 14942,
  currency: 'aw plumb',
  dbmtRequestDate: dayjs('2024-03-07'),
  dbmtStatus: 'absent thoroughly',
  offerStatus: 'regroup',
  docId: 24103,
  dbmtDateTime: 'peer-to-peer gee original',
  dbmtAmount: 8330,
  financeRequestId: 15454,
  amountToBeDisbursed: 'canoe respectful pish',
  destinationAccountName: 'gosh although mercury',
  destinationAccountNumber: 'vacant',
  status: 'puzzle furthermore until',
  actionByDate: 'bath among',
};

export const sampleWithNewData: NewDisbursement = {
  acceptedOfferUlidId: 'icky aside',
  placedOfferUlidId: 'evergreen',
  reqOffUlidId: 'circumstance',
  offerAcceptedDate: dayjs('2024-03-07'),
  dbmtRequestId: 'nor nervously dense',
  dbmtReqAmount: 19765,
  currency: 'union through',
  dbmtRequestDate: dayjs('2024-03-07'),
  dbmtStatus: 'likable skive',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
