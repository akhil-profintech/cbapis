import { IDocDetails, NewDocDetails } from './doc-details.model';

export const sampleWithRequiredData: IDocDetails = {
  id: 7742,
  docRecordId: 16752,
  link: 'eyelash tremendously sunlamp',
  description: 'hm exhilarate',
  docType: 'raffle whereas attribute',
  status: 'provided but',
};

export const sampleWithPartialData: IDocDetails = {
  id: 2379,
  docRecordId: 10845,
  link: 'yum besides elastic',
  description: 'despite',
  docType: 'over',
  status: 'monumental if',
};

export const sampleWithFullData: IDocDetails = {
  id: 27025,
  docDetailsId: 30305,
  docDetailsUlidId: 'back kooky',
  docRecordId: 31566,
  link: 'alarming',
  description: 'of duh',
  docType: 'mandate the',
  status: 'impartial',
};

export const sampleWithNewData: NewDocDetails = {
  docRecordId: 19452,
  link: 'meh certainly stab',
  description: 'really crossly subroutine',
  docType: 'but bah batting',
  status: 'ack psst stomach',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
