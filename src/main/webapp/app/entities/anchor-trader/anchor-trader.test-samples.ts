import { IAnchorTrader, NewAnchorTrader } from './anchor-trader.model';

export const sampleWithRequiredData: IAnchorTrader = {
  id: 7544,
  tenantId: 'yet',
  atId: 'forsake picturise',
  orgId: 'failing including frantically',
  customerName: 'bore besides entrench',
  orgName: 'flugelhorn emigrate',
  gstId: 'company borrower',
  phoneNumber: 19514,
};

export const sampleWithPartialData: IAnchorTrader = {
  id: 13761,
  tenantId: 'enquiry',
  atId: 'novel',
  orgId: 'ultimatum tense easily',
  customerName: 'sane wisely despite',
  orgName: 'even about',
  gstId: 'under caution',
  phoneNumber: 3573,
  anchorTraderName: 'supposing ack volunteering',
  bankDetails: 'apprehensive an',
};

export const sampleWithFullData: IAnchorTrader = {
  id: 18811,
  tenantId: 'vivaciously under eek',
  atId: 'moonscape aboard',
  orgId: 'nor',
  customerName: 'up',
  orgName: 'blah brief',
  gstId: 'acidly near absentmindedly',
  phoneNumber: 7771,
  anchorTraderName: 'whenever fancy athwart',
  location: 'flare diluted truly',
  anchorTraderGST: 'pelican tear',
  anchorTraderSector: 'outrun gosh',
  anchorTraderPAN: 'vice limply beckon',
  kycCompleted: 'furthermore parole yuck',
  bankDetails: 'poppy fresh equally',
};

export const sampleWithNewData: NewAnchorTrader = {
  tenantId: 'though failing',
  atId: 'only bilk fondly',
  orgId: 'above abaft',
  customerName: 'stockpile',
  orgName: 'possible',
  gstId: 'because',
  phoneNumber: 32691,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
