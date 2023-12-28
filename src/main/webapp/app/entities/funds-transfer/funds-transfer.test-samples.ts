import { IFundsTransfer, NewFundsTransfer } from './funds-transfer.model';

export const sampleWithRequiredData: IFundsTransfer = {
  id: 17185,
  transactionId: 4464,
  lastupdatedDateTime: 'opposite why',
  lastUpdatedBy: 'rebuke honestly',
};

export const sampleWithPartialData: IFundsTransfer = {
  id: 22561,
  fundsTransferId: 'angelic curiously',
  subGrpId: 'amused subtle venerate',
  transactionId: 21340,
  debitAccountNumber: 'asterisk unibody memorialize',
  remitterName: 'esteemed',
  amount: 18719,
  beneficiaryIFSC: 'pink',
  beneficiaryName: 'at after bottleful',
  beneficiaryAddress: 'unique',
  emailId: 'noteworthy exalt playful',
  mobileNo: 9095,
  messageType: 'about questioner frigid',
  trnxMetaDataStatus: 'bulk bah stole',
  trnxMetaDataMessage: 'spring doctor incidentally',
  trnxMetaDataVersion: 'disloyal',
  trnxMetaDataTime: 'phew',
  trnxResourceDataTransactionId: 'mortally insurgence below',
  lastupdatedDateTime: 'below',
  lastUpdatedBy: 'self-assured',
};

export const sampleWithFullData: IFundsTransfer = {
  id: 28991,
  fundsTransferId: 'since',
  fundsTransferRefNo: 'even zowie brr',
  finReqId: 'wherever',
  subGrpId: 'where',
  transactionId: 25635,
  debitAccountNumber: 'helplessly',
  creditAccountNumber: 'shyly',
  remitterName: 'but',
  amount: 3476,
  currency: 'worth ah',
  transactionType: 'bowler',
  paymentDescription: 'pfft how among',
  beneficiaryIFSC: 'from',
  beneficiaryName: 'truthfully beneath',
  beneficiaryAddress: 'surprisingly usefully as',
  emailId: 'shakily pupate',
  mobileNo: 11061,
  messageType: 'yum squiggly',
  transactionDateTime: 'silently till paintwork',
  transactionRefNo: 'longitude',
  trnxMetaDataStatus: 'pfft lovingly',
  trnxMetaDataMessage: 'woot net',
  trnxMetaDataVersion: 'rapidly canine',
  trnxMetaDataTime: 'incidentally when rejoice',
  trnxResourceDataBeneficiaryName: 'meek musty',
  trnxResourceDataTransactionId: 'virtuous kindhearted boastfully',
  trnxResourceDataStatus: 'zowie well-informed',
  fundsTransferStatus: 'focused',
  lastupdatedDateTime: 'vouch butcher mirror',
  lastUpdatedBy: 'um honestly and',
};

export const sampleWithNewData: NewFundsTransfer = {
  transactionId: 18197,
  lastupdatedDateTime: 'likewise',
  lastUpdatedBy: 'astride versus out',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
