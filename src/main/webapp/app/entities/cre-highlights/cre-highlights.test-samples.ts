import { ICREHighlights, NewCREHighlights } from './cre-highlights.model';

export const sampleWithRequiredData: ICREHighlights = {
  id: 18872,
};

export const sampleWithPartialData: ICREHighlights = {
  id: 14682,
  creHighlightsUlidId: 'regret manifestation',
  creRequestId: 'for hateful an',
};

export const sampleWithFullData: ICREHighlights = {
  id: 3942,
  creHighlightsId: 7304,
  creHighlightsUlidId: 'crayfish save monsoon',
  creRequestId: 'anenst aboard',
  highlights: 'restrain',
};

export const sampleWithNewData: NewCREHighlights = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
