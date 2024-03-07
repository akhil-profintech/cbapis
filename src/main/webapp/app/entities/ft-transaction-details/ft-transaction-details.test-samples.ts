import { IFTTransactionDetails, NewFTTransactionDetails } from './ft-transaction-details.model';

export const sampleWithRequiredData: IFTTransactionDetails = {
  id: 17707,
  transactionId: 29138,
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
  id: 26254,
  ftTrnxDetailsId: 30254,
  ftTrnxDetailsUlid: 'sans meh deduce',
  transactionId: 31011,
  debitAccountNumber: 'because',
  creditAccountNumber: 'midst',
  remitterName: 'download',
  amount: 10173,
  currency: 'down',
  transactionType: 'common mile',
  paymentDescription: 'since inasmuch',
  beneficiaryIFSC: 'bankbook drafty vaguely',
  beneficiaryName: 'continually',
  beneficiaryAddress: 'finally yuck',
  emailId: 'during',
  mobileNo: 25933,
  transactionRefNo: 'than eek ick',
  trnxResourceDataStatus: 'stupendous before',
  trnxMetaDataStatus: 'when',
  transactionDateTime: 'anoint towards quarry',
};

export const sampleWithFullData: IFTTransactionDetails = {
  id: 24399,
  ftTrnxDetailsId: 31749,
  ftTrnxDetailsUlid: 'pfft',
  transactionId: 7354,
  debitAccountNumber: 'huzzah shampoo',
  creditAccountNumber: 'brr bah meaningfully',
  remitterName: 'focus',
  amount: 8391,
  currency: 'trill noteworthy overtake',
  transactionType: 'athwart',
  paymentDescription: 'vainly vastly abandoned',
  beneficiaryIFSC: 'speedy beneath concerning',
  beneficiaryName: 'resell implore',
  beneficiaryAddress: 'indeed an',
  emailId: 'cling',
  mobileNo: 30062,
  transactionRefNo: 'wiry owlishly',
  trnxResourceDataStatus: 'until furthermore troll',
  trnxMetaDataStatus: 'trap into a',
  transactionDateTime: 'honey',
};

export const sampleWithNewData: NewFTTransactionDetails = {
  transactionId: 25278,
  debitAccountNumber: 'pretend value',
  creditAccountNumber: 'yippee',
  remitterName: 'yippee upbeat',
  amount: 22419,
  currency: 'very',
  transactionType: 'uh-huh fairly repeatedly',
  paymentDescription: 'beyond far whether',
  beneficiaryIFSC: 'formulate husky of',
  beneficiaryName: 'boohoo',
  beneficiaryAddress: 'duh rivet',
  emailId: 'abnormally poem complexity',
  mobileNo: 10670,
  transactionRefNo: 'ruck gah crayfish',
  trnxResourceDataStatus: 'an hm grade',
  trnxMetaDataStatus: 'perfectly',
  transactionDateTime: 'cheat',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
