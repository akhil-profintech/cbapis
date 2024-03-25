import { IAnchorTrader, NewAnchorTrader } from './anchor-trader.model';

export const sampleWithRequiredData: IAnchorTrader = {
  id: 23066,
  orgId: 'feminine forgather',
  tenantId: 'bah enlightened afore',
  customerName: 'thirsty whose',
  orgName: 'woot',
  gstId: 'meh locust midline',
  phoneNumber: 17397,
};

export const sampleWithPartialData: IAnchorTrader = {
  id: 16237,
  orgId: 'political antique',
  tenantId: 'overdue',
  customerName: 'wisely despite pace',
  orgName: 'about',
  gstId: 'under caution',
  phoneNumber: 3573,
  location: 'supposing ack volunteering',
  anchorTraderGST: 'apprehensive an',
  anchorTraderSector: 'hence yuck',
  anchorTraderPAN: 'eek toward grim',
  bankDetails: 'launder',
  accountNumber: 'comic to',
  ifscCode: 'generally',
  bankName: 'far',
  gstRegistrationCertificateVerificationStatus: true,
  udhyamRegistrationcertificateUploadStatus: false,
};

export const sampleWithFullData: IAnchorTrader = {
  id: 2630,
  atId: 26539,
  atUlidId: 'outlandish ew',
  orgId: 'trouble nail gondola',
  tenantId: 'wrathful house',
  customerName: 'rudely',
  orgName: 'arid oof',
  gstId: 'madly invade revise',
  phoneNumber: 24030,
  anchorTraderName: 'menopause',
  location: 'whoa ouch during',
  anchorTraderGST: 'excitedly unloosen shocking',
  anchorTraderSector: 'pivot modulo',
  anchorTraderPAN: 'grouch wiretap',
  kycCompleted: 'circumnavigate',
  bankDetails: 'hard-to-find',
  emailId: 'abaft',
  personalEmailId: 'stockpile',
  accountNumber: 'possible',
  ifscCode: 'because',
  bankName: 'yellowish enactment an',
  branchName: 'stale punctually um',
  erpAccess: false,
  typeOfFirm: 'times from',
  panStatus: 'however',
  tosDocument: 'lucky suburban duh',
  acceptTerms: false,
  acceptDeclaration: false,
  gstRegistrationCertificateUploadStatus: false,
  gstRegistrationCertificateVerificationStatus: false,
  udhyamRegistrationcertificateUploadStatus: true,
  udhyamRegistrationcertificateVerificationStatus: false,
  kycDeclaration: false,
};

export const sampleWithNewData: NewAnchorTrader = {
  orgId: 'eyestrain within but',
  tenantId: 'greatly',
  customerName: 'optimistically',
  orgName: 'decimal',
  gstId: 'gladly',
  phoneNumber: 19301,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
