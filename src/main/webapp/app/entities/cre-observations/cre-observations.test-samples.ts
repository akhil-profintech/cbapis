import { ICREObservations, NewCREObservations } from './cre-observations.model';

export const sampleWithRequiredData: ICREObservations = {
  id: 1218,
};

export const sampleWithPartialData: ICREObservations = {
  id: 28639,
  creRequestId: 'violet unto dear',
};

export const sampleWithFullData: ICREObservations = {
  id: 29470,
  creObservationsId: 3312,
  creObservationsUlidId: 'yahoo huzzah',
  creRequestId: 'earnings murky embed',
  observations: 'miserably',
};

export const sampleWithNewData: NewCREObservations = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
