import { IEscrowTransactionDetails, NewEscrowTransactionDetails } from './escrow-transaction-details.model';

export const sampleWithRequiredData: IEscrowTransactionDetails = {
  id: 24632,
  customerCode: 'yowza',
  customerName: 'where',
  productCode: 'what havoc speculation',
  transactionType: 'not',
  batchAmt: 'the righteously',
  batchAmtCcd: 'punctually wholly',
  creditDate: 'mid powerful beneath',
  vaNumber: 'after amid',
  utrNo: 'under',
  creditGenerationTime: 'snap deceivingly',
  remitterName: 'nourish devoted',
  remitterAccountNumber: 'candidacy hmph between',
  ifscCode: 'semantics astride',
};

export const sampleWithPartialData: IEscrowTransactionDetails = {
  id: 10458,
  escrowTrnxDetailsId: 32684,
  customerCode: 'per',
  customerName: 'merrily',
  productCode: 'huzzah',
  transactionType: 'zowie',
  batchAmt: 'after',
  batchAmtCcd: 'rarely',
  creditDate: 'daring next',
  vaNumber: 'inflame',
  utrNo: 'searchingly',
  creditGenerationTime: 'stability',
  remitterName: 'upside-down',
  remitterAccountNumber: 'adulterate whoa',
  ifscCode: 'thrash yum rename',
};

export const sampleWithFullData: IEscrowTransactionDetails = {
  id: 5029,
  escrowTrnxDetailsId: 27199,
  escrowTrnxDetailsUlidId: 'bleak',
  customerCode: 'perfectly knotty yahoo',
  customerName: 'detonate giggle',
  productCode: 'that restfully',
  transactionType: 'daily',
  batchAmt: 'why location illustrious',
  batchAmtCcd: 'yesterday',
  creditDate: 'cast gosh vivid',
  vaNumber: 'since necktie excluding',
  utrNo: 'near alongside',
  creditGenerationTime: 'briefly big-hearted',
  remitterName: 'hm gird gifted',
  remitterAccountNumber: 'modulo',
  ifscCode: 'beyond',
};

export const sampleWithNewData: NewEscrowTransactionDetails = {
  customerCode: 'including dutiful because',
  customerName: 'proceedings ability battle',
  productCode: 'penalty ack',
  transactionType: 'preserves don',
  batchAmt: 'boldly mini plop',
  batchAmtCcd: 'blah shiver puppy',
  creditDate: 'qua sarcastic meanwhile',
  vaNumber: 'after hmph project',
  utrNo: 'gee on trustworthy',
  creditGenerationTime: 'yearningly',
  remitterName: 'wherever',
  remitterAccountNumber: 'eek chronometer',
  ifscCode: 'ballot frenetically',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
