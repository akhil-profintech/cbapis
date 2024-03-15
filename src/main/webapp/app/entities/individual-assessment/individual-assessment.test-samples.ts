import { IIndividualAssessment, NewIndividualAssessment } from './individual-assessment.model';

export const sampleWithRequiredData: IIndividualAssessment = {
  id: 11263,
};

export const sampleWithPartialData: IIndividualAssessment = {
  id: 8842,
  assessmentId: 3666,
  finalVerdict: 'worth slowly',
  creRequestId: 'zowie acrobatic unexpectedly',
  tradePartnerId: 'yuck ack excluding',
  invoiceAmount: 9787,
  invoiceId: 'reload',
  ctin: 'flat phew',
  cbProcessId: 26522,
  grnPresent: false,
  tradePartnerConfirmation: false,
};

export const sampleWithFullData: IIndividualAssessment = {
  id: 8341,
  assessmentId: 1049,
  assessmentUlidId: 'shyly potentially',
  creditScore: 26325,
  finalVerdict: 'oof extra-small yuck',
  creRequestId: 'vignette tragic',
  timestamp: 'pace glance',
  tradePartnerGST: 'fiery grounded',
  tradePartnerId: 'hmph earnest',
  invoiceAmount: 19398,
  invoiceId: 'revolving coil',
  baseScore: 'breach venti',
  ctin: 'communicate indeed',
  invDate: 'expertise',
  cbProcessId: 28712,
  grnPresent: false,
  einvoicePresent: false,
  ewayBillPresent: true,
  tradePartnerConfirmation: true,
};

export const sampleWithNewData: NewIndividualAssessment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
