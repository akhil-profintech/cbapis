import { IAnchorTrader, NewAnchorTrader } from './anchor-trader.model';

export const sampleWithRequiredData: IAnchorTrader = {
  id: 18384,
  orgId: 'profitable mime bah',
  tenantId: 'wherever',
  customerName: 'afore',
  orgName: 'thirsty whose',
  gstId: 'woot',
  phoneNumber: 30983,
};

export const sampleWithPartialData: IAnchorTrader = {
  id: 2194,
  atId: 13761,
  atUlidId: 'enquiry',
  orgId: 'novel',
  tenantId: 'ultimatum tense easily',
  customerName: 'sane wisely despite',
  orgName: 'even about',
  gstId: 'under caution',
  phoneNumber: 3573,
  anchorTraderName: 'supposing ack volunteering',
  anchorTraderGST: 'apprehensive an',
  anchorTraderSector: 'hence yuck',
  anchorTraderPAN: 'eek toward grim',
  kycCompleted: 'launder',
  bankDetails: 'comic to',
  accountNumber: 'generally',
  erpAccess: true,
  typeOfFirm: 'acidly near absentmindedly',
  tosDocument: 'consequently',
  acceptDeclaration: false,
};

export const sampleWithFullData: IAnchorTrader = {
  id: 23878,
  atId: 23179,
  atUlidId: 'nail gondola whether',
  orgId: 'house derby though',
  tenantId: 'circa mmm drat',
  customerName: 'garage provided',
  orgName: 'bonsai daily insulate',
  gstId: 'proceed',
  phoneNumber: 24358,
  anchorTraderName: 'haunt unpleasant',
  location: 'big or disgusting',
  anchorTraderGST: 'yippee team ew',
  anchorTraderSector: 'warmly times since',
  anchorTraderPAN: 'into training',
  kycCompleted: 'dependable',
  bankDetails: 'ack bah',
  emailId: 'celebrated chrysalis',
  accountNumber: 'portly',
  ifscCode: 'manoeuvre',
  bankName: 'zowie',
  branchName: 'warm rigid victoriously',
  erpAccess: true,
  typeOfFirm: 'accomplished regal feminize',
  panStatus: 'awkwardly',
  tosDocument: 'campanile afraid',
  acceptTerms: false,
  acceptDeclaration: true,
  gstRegistrationCertificateUploadStatus: false,
  gstRegistrationCertificateVerificationStatus: true,
  udhyamRegistrationcertificateUploadStatus: false,
  udhyamRegistrationcertificateVerificationStatus: true,
  kycDeclaration: true,
};

export const sampleWithNewData: NewAnchorTrader = {
  orgId: 'mission because after',
  tenantId: 'which',
  customerName: 'scholarly',
  orgName: 'televise nor',
  gstId: 'fervently whirlpool beneath',
  phoneNumber: 32426,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
