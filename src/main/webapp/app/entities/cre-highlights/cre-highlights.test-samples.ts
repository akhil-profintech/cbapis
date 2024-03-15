import { ICREHighlights, NewCREHighlights } from './cre-highlights.model';

export const sampleWithRequiredData: ICREHighlights = {
  id: 17782,
};

export const sampleWithPartialData: ICREHighlights = {
  id: 18792,
  creHighlightsId: 9508,
  creHighlightsUlidId: 'oh',
  highlights: 'quicksand if busy',
  assessmentId: 1040,
};

export const sampleWithFullData: ICREHighlights = {
  id: 21418,
  creHighlightsId: 6514,
  creHighlightsUlidId: 'satisfaction',
  creRequestId: 'mean',
  highlights: 'skull',
  assessmentId: 18818,
};

export const sampleWithNewData: NewCREHighlights = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
