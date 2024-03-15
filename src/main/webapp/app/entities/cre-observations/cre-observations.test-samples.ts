import { ICREObservations, NewCREObservations } from './cre-observations.model';

export const sampleWithRequiredData: ICREObservations = {
  id: 22273,
};

export const sampleWithPartialData: ICREObservations = {
  id: 29971,
  creObservationsUlidId: 'naive less',
};

export const sampleWithFullData: ICREObservations = {
  id: 8972,
  creObservationsId: 17576,
  creObservationsUlidId: 'uh-huh besides',
  creRequestId: 'hmph',
  observations: 'useless faint',
  assessmentId: 3786,
};

export const sampleWithNewData: NewCREObservations = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
