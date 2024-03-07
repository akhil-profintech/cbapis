import { IIndividualAssessment, NewIndividualAssessment } from './individual-assessment.model';

export const sampleWithRequiredData: IIndividualAssessment = {
  id: 9257,
};

export const sampleWithPartialData: IIndividualAssessment = {
  id: 22392,
  assessmentId: 8797,
  assessmentUlidId: 'given',
  finalVerdict: 'amid interconnect',
  timestamp: 'slowly mushroom messy',
  tradePartnerId: 'unexpectedly',
  invoiceAmount: 29485,
};

export const sampleWithFullData: IIndividualAssessment = {
  id: 6692,
  assessmentId: 23026,
  assessmentUlidId: 'banking',
  creditScore: 11649.71,
  finalVerdict: 'pfft actress useless',
  creRequestId: 'after',
  timestamp: 'disembowel triumphantly acrobatic',
  tradePartnerGST: 'jealously',
  tradePartnerId: 'brr under bottom',
  invoiceAmount: 11535,
  invoiceId: 'spotted diligently',
};

export const sampleWithNewData: NewIndividualAssessment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
