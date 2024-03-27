import { IAnchorTrader, NewAnchorTrader } from './anchor-trader.model';

export const sampleWithRequiredData: IAnchorTrader = {
  id: 14082,
  orgId: 'excitedly volley hmph',
  tenantId: 'sans',
  customerName: 'stammer austere since',
  orgName: 'educated glimpse astride',
  gstId: 'worth',
  phoneNumber: 21176,
};

export const sampleWithPartialData: IAnchorTrader = {
  id: 11689,
  atUlidId: 'questionable suddenly',
  orgId: 'nail crazy',
  tenantId: 'finally times',
  customerName: 'fox',
  orgName: 'finesse gently',
  gstId: 'bassoon and whoever',
  phoneNumber: 12327,
  anchorTraderName: 'awkwardly future',
  location: 'pendant instill phooey',
  anchorTraderGST: 'beside unless',
  anchorTraderPAN: 'hm carpeting dearly',
  emailId: 'and waterskiing interpose',
  personalEmailId: 'without whenever fancy',
  accountNumber: 'whoever',
  acceptDeclaration: true,
  gstRegistrationCertificateUploadStatus: true,
  udhyamRegistrationcertificateUploadStatus: true,
  udhyamRegistrationCertificateName: 'joyous',
};

export const sampleWithFullData: IAnchorTrader = {
  id: 1067,
  atId: 28428,
  atUlidId: 'truly',
  orgId: 'pelican tear',
  tenantId: 'outrun gosh',
  customerName: 'vice limply beckon',
  orgName: 'furthermore parole yuck',
  gstId: 'poppy fresh equally',
  phoneNumber: 11320,
  anchorTraderName: 'frustrate',
  location: 'monumental',
  anchorTraderGST: 'double supervise',
  anchorTraderSector: 'beyond absent',
  anchorTraderPAN: 'since',
  kycCompleted: 'since investigator worth',
  bankDetails: 'unconscious enunciate scalp',
  emailId: 'new wherever stud',
  personalEmailId: 'times from',
  accountNumber: 'however',
  ifscCode: 'lucky suburban duh',
  bankName: 'er eyestrain within',
  branchName: 'hence greatly background',
  erpAccess: false,
  typeOfFirm: 'decimal',
  panStatus: 'gladly',
  tosDocument: 'fowl ink',
  acceptTerms: false,
  acceptDeclaration: true,
  gstRegistrationCertificateUploadStatus: false,
  gstRegistrationCertificateVerificationStatus: true,
  gstRegistrationCertificateName: 'meaty televise nor',
  udhyamRegistrationcertificateUploadStatus: false,
  udhyamRegistrationCertificateName: 'brr bah wisecrack',
  udhyamRegistrationcertificateVerificationStatus: true,
  kycDeclaration: true,
};

export const sampleWithNewData: NewAnchorTrader = {
  orgId: 'next',
  tenantId: 'wherever subsidise',
  customerName: 'thoroughly listen',
  orgName: 'yawl ew united',
  gstId: 'frenetically',
  phoneNumber: 21950,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
