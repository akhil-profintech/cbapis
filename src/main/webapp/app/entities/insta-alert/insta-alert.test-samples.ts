import { IInstaAlert, NewInstaAlert } from './insta-alert.model';

export const sampleWithRequiredData: IInstaAlert = {
  id: 28376,
  instaAlertId: 10374,
  lastupdatedDateTime: 'indeed whether',
  lastUpdatedBy: 'really',
};

export const sampleWithPartialData: IInstaAlert = {
  id: 10853,
  instaAlertId: 21677,
  subGrpId: 'or er',
  customerCode: 'pest',
  batchAmtCcd: 'gadzooks',
  vaNumber: 'cutlet matter pfft',
  remitterName: 'staid longingly',
  remitterAccountNumber: 'between gee',
  lastupdatedDateTime: 'bulky laugh situation',
  lastUpdatedBy: 'though properly',
};

export const sampleWithFullData: IInstaAlert = {
  id: 13068,
  instaAlertId: 13806,
  finReqId: 'yowza diligently',
  subGrpId: 'solemnly',
  customerCode: 'cautiously kindly',
  customerName: 'though however',
  productCode: 'part',
  poolingAccountNumber: 'uh-huh likewise',
  transactionType: 'ferociously',
  batchAmt: 12590,
  batchAmtCcd: 'mismanage',
  creditDate: 'spectacles yippee soon',
  vaNumber: 'athwart pipe furthermore',
  utrNo: 'clean',
  creditGenerationTime: 'ark quirkily',
  remitterName: 'anti townhouse within',
  remitterAccountNumber: 'pish recklessly',
  ifscCode: 'aw snooper wherever',
  lastupdatedDateTime: 'bashfully',
  lastUpdatedBy: 'um',
  dataKey: 'anonymise',
};

export const sampleWithNewData: NewInstaAlert = {
  instaAlertId: 14231,
  lastupdatedDateTime: 'vainly on',
  lastUpdatedBy: 'monthly pierce',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
