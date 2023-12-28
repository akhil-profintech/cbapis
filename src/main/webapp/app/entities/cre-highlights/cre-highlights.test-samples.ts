import { ICREHighlights, NewCREHighlights } from './cre-highlights.model';

export const sampleWithRequiredData: ICREHighlights = {
  id: 22927,
  creHighlightsId: 18872,
};

export const sampleWithPartialData: ICREHighlights = {
  id: 5836,
  creHighlightsId: 18148,
  highlights: 'troop pish',
};

export const sampleWithFullData: ICREHighlights = {
  id: 17636,
  creHighlightsId: 25162,
  creRequestId: 'yum',
  highlights: 'following extremely mend',
};

export const sampleWithNewData: NewCREHighlights = {
  creHighlightsId: 7143,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
