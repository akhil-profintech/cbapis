import { ICollectionTransactionDetails, NewCollectionTransactionDetails } from './collection-transaction-details.model';

export const sampleWithRequiredData: ICollectionTransactionDetails = {
  id: 22066,
  trnxDetailsid: 8531,
  customerCode: 'before ouch',
  customerName: 'yahoo dismal',
  productCode: 'qua gadzooks',
  transactionType: 'ah phew whenever',
  batchAmt: 'neck',
  batchAmtCcd: 'scarce vaguely',
  creditDate: 'after',
  vaNumber: 'upwardly',
  utrNo: 'miracle bah',
  creditGenerationTime: 'heritage',
  remitterName: 'zowie',
  remitterAccountNumber: 'parrot judgementally infrastructure',
  ifscCode: 'unfortunately unlike below',
};

export const sampleWithPartialData: ICollectionTransactionDetails = {
  id: 32538,
  trnxDetailsid: 6587,
  customerCode: 'nor kookily',
  customerName: 'criminal vacantly',
  productCode: 'refocus',
  transactionType: 'yahoo',
  batchAmt: 'skipper fibrosis restriction',
  batchAmtCcd: 'angrily',
  creditDate: 'aha',
  vaNumber: 'oof woodwind',
  utrNo: 'portray',
  creditGenerationTime: 'correctly',
  remitterName: 'alongside',
  remitterAccountNumber: 'er ride unlucky',
  ifscCode: 'towards utterly actually',
};

export const sampleWithFullData: ICollectionTransactionDetails = {
  id: 19980,
  trnxDetailsid: 431,
  customerCode: 'likewise',
  customerName: 'volumize mix onto',
  productCode: 'stylish scaly sleuth',
  transactionType: 'workout pity gosh',
  batchAmt: 'colorfully',
  batchAmtCcd: 'which',
  creditDate: 'mmm ephemeris',
  vaNumber: 'since',
  utrNo: 'over',
  creditGenerationTime: 'initialise',
  remitterName: 'between',
  remitterAccountNumber: 'referee',
  ifscCode: 'zowie monitor',
};

export const sampleWithNewData: NewCollectionTransactionDetails = {
  trnxDetailsid: 32625,
  customerCode: 'supposing ew vapid',
  customerName: 'now',
  productCode: 'yuck even worriedly',
  transactionType: 'judicious wide-eyed geez',
  batchAmt: 'uncork artistic hunt',
  batchAmtCcd: 'who true instead',
  creditDate: 'sway',
  vaNumber: 'woot',
  utrNo: 'weakly cold',
  creditGenerationTime: 'unbearably',
  remitterName: 'boohoo fooey',
  remitterAccountNumber: 'village',
  ifscCode: 'vernacular sugary',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
