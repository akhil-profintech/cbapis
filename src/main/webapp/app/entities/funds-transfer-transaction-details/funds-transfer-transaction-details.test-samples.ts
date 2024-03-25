import { IFundsTransferTransactionDetails, NewFundsTransferTransactionDetails } from './funds-transfer-transaction-details.model';

export const sampleWithRequiredData: IFundsTransferTransactionDetails = {
  id: 22840,
  transactionId: 29609,
  debitAccountNumber: 'absent likewise provided',
  creditAccountNumber: 'modulo bah',
  remitterName: 'er',
  amount: 25155,
  currency: 'afore',
  transactionType: 'whether',
  paymentDescription: 'clover pace nobble',
  beneficiaryIFSC: 'forenenst yearly consequently',
  beneficiaryName: 'yuck quirkily striped',
  beneficiaryAddress: 'approve',
  emailId: 'ew phew',
  mobileNo: 18390,
  transactionRefNo: 'grumpy cultured',
  trnxResourceDataStatus: 'generously to',
  trnxMetaDataStatus: 'utilise',
  transactionDateTime: 'pace boo',
};

export const sampleWithPartialData: IFundsTransferTransactionDetails = {
  id: 22183,
  ftTrnxDetailsId: 8726,
  ftTrnxDetailsUlid: 'bowler from',
  transactionId: 16390,
  debitAccountNumber: 'wobbly',
  creditAccountNumber: 'half legitimise',
  remitterName: 'for savannah beautify',
  amount: 25843,
  currency: 'ouch pfft vest',
  transactionType: 'because trader',
  paymentDescription: 'rasp',
  beneficiaryIFSC: 'fairly invert beanstalk',
  beneficiaryName: 'straight extroverted',
  beneficiaryAddress: 'noteworthy ack',
  emailId: 'dilute along interchange',
  mobileNo: 27789,
  transactionRefNo: 'juvenile apud',
  trnxResourceDataStatus: 'eek disruption',
  trnxMetaDataStatus: 'bitterly inactivate',
  transactionDateTime: 'although walk in',
};

export const sampleWithFullData: IFundsTransferTransactionDetails = {
  id: 30357,
  ftTrnxDetailsId: 13916,
  ftTrnxDetailsUlid: 'admire over whose',
  transactionId: 13487,
  debitAccountNumber: 'upbeat',
  creditAccountNumber: 'verifiable excluding',
  remitterName: 'tan',
  amount: 21765,
  currency: 'ugh',
  transactionType: 'enervate',
  paymentDescription: 'valuable hm',
  beneficiaryIFSC: 'supposing inquisitively',
  beneficiaryName: 'prey including',
  beneficiaryAddress: 'to concerning thaw',
  emailId: 'eek tomorrow',
  mobileNo: 15795,
  transactionRefNo: 'yawningly gee though',
  trnxResourceDataStatus: 'incomplete drag smooth',
  trnxMetaDataStatus: 'elaborate halibut',
  transactionDateTime: 'aside',
};

export const sampleWithNewData: NewFundsTransferTransactionDetails = {
  transactionId: 16801,
  debitAccountNumber: 'unless',
  creditAccountNumber: 'positively furthermore sternly',
  remitterName: 'down lest huge',
  amount: 2736,
  currency: 'always belated stain',
  transactionType: 'ferociously geez',
  paymentDescription: 'meh times upliftingly',
  beneficiaryIFSC: 'truly',
  beneficiaryName: 'whoever',
  beneficiaryAddress: 'wherever jack when',
  emailId: 'ugh nearly',
  mobileNo: 28374,
  transactionRefNo: 'perfect',
  trnxResourceDataStatus: 'eventually',
  trnxMetaDataStatus: 'meanwhile spot',
  transactionDateTime: 'yuck',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
