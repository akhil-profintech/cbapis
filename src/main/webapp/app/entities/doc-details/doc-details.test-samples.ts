import { IDocDetails, NewDocDetails } from './doc-details.model';

export const sampleWithRequiredData: IDocDetails = {
  id: 7401,
  docDetailsId: 7742,
  docRecordId: 16752,
  link: 'eyelash tremendously sunlamp',
  description: 'hm exhilarate',
  docType: 'raffle whereas attribute',
  status: 'provided but',
};

export const sampleWithPartialData: IDocDetails = {
  id: 28838,
  docDetailsId: 25039,
  docRecordId: 2379,
  link: 'after',
  description: 'besides',
  docType: 'boldly despite ick',
  status: 'lost certainly optimistically',
};

export const sampleWithFullData: IDocDetails = {
  id: 29419,
  docDetailsId: 27951,
  docRecordId: 32068,
  link: 'kooky',
  description: 'odd ha incidentally',
  docType: 'mockingly',
  status: 'viciously very',
};

export const sampleWithNewData: NewDocDetails = {
  docDetailsId: 29849,
  docRecordId: 13627,
  link: 'but blue',
  description: 'stab',
  docType: 'really crossly subroutine',
  status: 'but bah batting',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
