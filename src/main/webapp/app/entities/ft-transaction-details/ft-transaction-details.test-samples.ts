import { IFTTransactionDetails, NewFTTransactionDetails } from './ft-transaction-details.model';

export const sampleWithRequiredData: IFTTransactionDetails = {
  id: 17986,
  trnxDetailsId: 17707,
  transactionID: 29138,
  debitAccountNumber: 'incidentally',
  creditAccountNumber: 'heartache softly theorize',
  remitterName: 'daddy although',
  amount: 31362,
  currency: 'boohoo descriptive counterpoint',
  transactionType: 'failing',
  paymentDescription: 'gummy',
  beneficiaryIFSC: 'up envision how',
  beneficiaryName: 'aside around zowie',
  beneficiaryAddress: 'coolly cut chargesheet',
  emailId: 'although ouch',
  mobileNo: 16674,
  transactionRefNo: 'uh-huh',
  trnxResourceDataStatus: 'till council descry',
  trnxMetaDataStatus: 'knowingly furthermore',
  transactionDateTime: 'looks whose',
};

export const sampleWithPartialData: IFTTransactionDetails = {
  id: 15210,
  trnxDetailsId: 11701,
  transactionID: 26254,
  debitAccountNumber: 'politely occur creaking',
  creditAccountNumber: 'meaningfully',
  remitterName: 'gadzooks',
  amount: 29370,
  currency: 'maestro maggot',
  transactionType: 'site aperitif focused',
  paymentDescription: 'abase apropos',
  beneficiaryIFSC: 'turbocharge burly shift',
  beneficiaryName: 'um',
  beneficiaryAddress: 'chaperone anxiously',
  emailId: 'annihilate yahoo',
  mobileNo: 2272,
  transactionRefNo: 'neglect surprised',
  trnxResourceDataStatus: 'phooey double',
  trnxMetaDataStatus: 'weakly',
  transactionDateTime: 'calmly meh',
};

export const sampleWithFullData: IFTTransactionDetails = {
  id: 16255,
  trnxDetailsId: 19204,
  transactionID: 22077,
  debitAccountNumber: 'to fooey latency',
  creditAccountNumber: 'given',
  remitterName: 'grove',
  amount: 30254,
  currency: 'excluding',
  transactionType: 'zowie ick',
  paymentDescription: 'linear',
  beneficiaryIFSC: 'by oof prudent',
  beneficiaryName: 'artistic',
  beneficiaryAddress: 'warmhearted',
  emailId: 'scientific',
  mobileNo: 25766,
  transactionRefNo: 'knowledgeably meh resell',
  trnxResourceDataStatus: 'midst indeed',
  trnxMetaDataStatus: 'anti cling',
  transactionDateTime: 'qua yum and',
};

export const sampleWithNewData: NewFTTransactionDetails = {
  trnxDetailsId: 4079,
  transactionID: 29593,
  debitAccountNumber: 'thoughtfully range',
  creditAccountNumber: 'who',
  remitterName: 'pickax painfully',
  amount: 15830,
  currency: 'uh-huh sight',
  transactionType: 'versus except',
  paymentDescription: 'prestigious over yahoo',
  beneficiaryIFSC: 'harmless angrily late',
  beneficiaryName: 'victoriously total',
  beneficiaryAddress: 'whether',
  emailId: 'formulate husky of',
  mobileNo: 7751,
  transactionRefNo: 'meanwhile',
  trnxResourceDataStatus: 'ace',
  trnxMetaDataStatus: 'sandy annually above',
  transactionDateTime: 'vastly ruck',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
