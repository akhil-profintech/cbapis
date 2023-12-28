import { IClientCodes, NewClientCodes } from './client-codes.model';

export const sampleWithRequiredData: IClientCodes = {
  id: 31718,
};

export const sampleWithPartialData: IClientCodes = {
  id: 10666,
  clientCharsCode: 'and',
};

export const sampleWithFullData: IClientCodes = {
  id: 8700,
  clientCode: 20555,
  clientCharsCode: 'parch round agile',
  clientName: 'necessary apropos',
};

export const sampleWithNewData: NewClientCodes = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
