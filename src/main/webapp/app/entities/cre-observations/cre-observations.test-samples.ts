import { ICREObservations, NewCREObservations } from './cre-observations.model';

export const sampleWithRequiredData: ICREObservations = {
  id: 24265,
  creObservationsId: 1218,
};

export const sampleWithPartialData: ICREObservations = {
  id: 1366,
  creObservationsId: 28738,
};

export const sampleWithFullData: ICREObservations = {
  id: 28639,
  creObservationsId: 23800,
  creRequestId: 'remote cocktail telegraph',
  observations: 'or',
};

export const sampleWithNewData: NewCREObservations = {
  creObservationsId: 12476,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
