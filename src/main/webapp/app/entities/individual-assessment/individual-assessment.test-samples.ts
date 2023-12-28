import { IIndividualAssessment, NewIndividualAssessment } from './individual-assessment.model';

export const sampleWithRequiredData: IIndividualAssessment = {
  id: 21483,
};

export const sampleWithPartialData: IIndividualAssessment = {
  id: 7645,
  assessmentId: 'inspire righteously lest',
  creditScore: 2484.12,
  finalverdict: 'normal',
  timestamp: 'caulk',
  tradepartnerId: 'stimulating barring bitterly',
  invoiceId: 'viciously pfft',
};

export const sampleWithFullData: IIndividualAssessment = {
  id: 7509,
  assessmentId: 'prepone stealthily',
  creditScore: 25152.53,
  finalverdict: 'millennium innocently',
  creRequestId: 'till where ew',
  timestamp: 'extra-small yuck plain',
  tradepartnerGST: 'tragic astride whenever',
  tradepartnerId: 'victimize remove',
  invoiceAmount: 11698,
  invoiceId: 'hmph earnest',
};

export const sampleWithNewData: NewIndividualAssessment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
