import { IGstin, NewGstin } from './gstin.model';

export const sampleWithRequiredData: IGstin = {
  id: 489,
  companyName: 'inset unfit even',
  gstId: 'midst diligent instead',
};

export const sampleWithPartialData: IGstin = {
  id: 17603,
  companyName: 'gah hm',
  gstId: 'foolishly but harmonise',
};

export const sampleWithFullData: IGstin = {
  id: 8730,
  companyName: 'shallow',
  gstId: 'agitate diving',
};

export const sampleWithNewData: NewGstin = {
  companyName: 'where',
  gstId: 'acquiesce impeccable',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
